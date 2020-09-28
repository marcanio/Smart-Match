package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class  MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPassword;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private static String URL_VERIFY ="http://coms-309-vb-10.cs.iastate.edu:8080/users/verify";



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName= (EditText) findViewById(R.id.LoginUsername);
        editPassword = (EditText) findViewById(R.id.LoginPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnReg);

        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                validate();
            }
        });
        register.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void validate(){

        final String username = editName.getText().toString();
        final String password = editPassword.getText().toString();

        if(TextUtils.isEmpty(username)){
            editName.setError("Please Enter Username");
            editName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            editPassword.setError("Please Enter Password");
            editPassword.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VERIFY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                JSONObject userJson = obj.getJSONObject("user");
                                finish();
                                startActivity(new Intent(getApplication(), Profile.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("emailaddress",username);
                params.put("userPassword", password);
                return params;
            }
        };
        
    }
}