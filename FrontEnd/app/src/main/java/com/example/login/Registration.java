package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    private EditText firstName, lastName, email, password, birthday, gender, phoneNumber;
    private Button btn_regist;
    private static String URL_REGIST = "https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io";
    //Postman - https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        birthday = (EditText) findViewById(R.id.birthday);
        phoneNumber = (EditText) findViewById(R.id.mobile);
        btn_regist = findViewById(R.id.submitButton);
        gender = (EditText) findViewById(R.id.gender);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        final String firstName = this.firstName.getText().toString().trim();
        final String lastName = this.lastName.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String birthday = this.birthday.getText().toString().trim();
        final String gender = this.gender.getText().toString().trim();
        final String phoneNumber = this.phoneNumber.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("phoneNumber", phoneNumber);
        params.put("emailaddress", email);
        params.put("gender", gender);
        params.put("userPassword", password);
        params.put("age", birthday);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_REGIST, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", "" + response);
                Intent intent = new Intent(Registration.this, SecondActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
