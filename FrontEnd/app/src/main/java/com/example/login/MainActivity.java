package com.example.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText editName;
    private EditText editPassword;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private static String URL_VERIFY = "http://coms-309-vb-10.cs.iastate.edu:8080/users/verifies";
    //for postman test
    //private static String URL_VERIFY = "https://postman-echo.com/post";

    /**
     * Saves the value in the input boxes and sets them to global variables.
     * Uses setOnClickListeners() to call methods or new classes when the login or register button is clicked
     *
     * @param savedInstanceState - Saves the current instance of the app
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AppController();
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
                validate(editName.getText().toString().trim(), editPassword.getText().toString().trim());
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

    /**
     * Validates a users login attempt. Check to make sure username and password at least contain something
     * After that is preforms a volley POST request to identify if the user is in the system.
     * It grabs the users information and stores it so the app is aware what user is currently logged in
     *
     * @param username - The current username inputted
     * @param password - The current password inputted
     * @return - Returns if the validation was successful or failed
     */
    @SuppressLint("SetTextI18n")
    public String validate(String username, String password) {

        final String[] outcome = {""};
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject params = new JSONObject();
        if (TextUtils.isEmpty(username)) {
            editName.setError("Please Enter Username");
            editName.requestFocus();
            return "Name Error";
        }
        if (TextUtils.isEmpty(password)) {
            editPassword.setError("Please Enter Password");
            editPassword.requestFocus();
            return "Password Error";
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
                    outcome[0] = "Success";
                    Log.e("Response", "" + response);

                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    try {
                        intent.putExtra("quizScore", Integer.parseInt(response.getString("message")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                outcome[0] = "Error";
                editPassword.setError("Wrong email or password");
                error.printStackTrace();

            }
        });
        requestQueue.add(jsonObjectRequest);
        return outcome[0];
    }
}
