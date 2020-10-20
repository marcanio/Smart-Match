package com.example.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "LoginPrefs";
    private EditText editName;
    private EditText editPassword;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    //for postman test
    private static String URL_VERIFY = "https://postman-echo.com/post";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.LoginUsername);
        editPassword = (EditText) findViewById(R.id.LoginPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnReg);
        Info.setText("Number of attempts remaining: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    private void profile(){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }
    private void logOut(){

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout:

                return true;
            case R.id.profile:
                profile();
                return true;
            default:  return super.onOptionsItemSelected(item);
        }



    }

    @SuppressLint("SetTextI18n")
    private void validate() {
        final String username = this.editName.getText().toString().trim();
        final String password = this.editPassword.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject params = new JSONObject();
        if (TextUtils.isEmpty(username)) {
            editName.setError("Please Enter Username");
            editName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editPassword.setError("Please Enter Password");
            editPassword.requestFocus();
            return;
        }
        try {
            params.put("emailaddress", username);
            params.put("userPassword", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject verified = new JSONObject();
        JSONObject notVerified = new JSONObject();
        try {
            verified.put("message", "Verified");
            notVerified.put("message", "Not Verified");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_VERIFY, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println();
                if (response.has("message")) {
                    Log.e("Response", "" + response);
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                editPassword.setError("Wrong email or password");
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
