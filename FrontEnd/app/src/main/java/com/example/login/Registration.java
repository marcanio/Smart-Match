package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.login.Logic.RegistrationLogic;
import com.example.login.Network.ServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements IView{
    private EditText firstNameView, lastNameView, emailView, passwordView, birthdayView, genderView, phoneNumberView;
    private Button btn_regist;
    private static String URL_REGIST ="http://coms-309-vb-10.cs.iastate.edu:8080/users/new";
    //private static String URL_REGIST = "https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io";

    //Postman - https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AppController();
        setContentView(R.layout.activity_registration);
        firstNameView = findViewById(R.id.first_name);
        lastNameView = findViewById(R.id.last_name);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        birthdayView = findViewById(R.id.birthday);
        phoneNumberView = findViewById(R.id.mobile);
        genderView = findViewById(R.id.gender);
        btn_regist = findViewById(R.id.submitButton);

        ServerRequest serverRequest = new ServerRequest();
        final RegistrationLogic logic = new RegistrationLogic(this,serverRequest);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String firstName = firstNameView.getText().toString();
                    String lastName = lastNameView.getText().toString();
                    String email = emailView.getText().toString();
                    String password = passwordView.getText().toString();
                    String birthday = birthdayView.getText().toString();
                    String gender = genderView.getText().toString();
                    String phoneNumber = phoneNumberView.getText().toString();
                    logic.registerUser(firstName,lastName,email,password,birthday,phoneNumber,gender);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void showText(String s) {
        
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public Context getContext() {
        return this;
    }
/*
    private void register() {
        final String firstName = this.firstName.getText().toString().trim();
        final String lastName = this.lastName.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String birthday = this.birthday.getText().toString().trim();
        final String gender = this.gender.getText().toString().trim();
        final String phoneNumber = this.phoneNumber.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String,String> params = new HashMap<>();

        if(TextUtils.isEmpty(firstName)){
            this.firstName.setError("Please Enter Username");
            this.firstName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(lastName)){
            this.lastName.setError("Please Enter Username");
            this.lastName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(email)){
            this.email.setError("Please Enter Username");
            this.email.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            this.password.setError("Please Enter Username");
            this.password.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(birthday)){
            this.birthday.setError("Please Enter Username");
            this.birthday.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(gender)){
            this.gender.setError("Please Enter Username");
            this.gender.requestFocus();
            return;
        }
        if(!TextUtils.equals(gender,"male")){
            if(!TextUtils.equals(gender,"female")){
                this.gender.setError("Please enter male or female");
                this.gender.requestFocus();
                return;
            }
        }
        if(TextUtils.isEmpty(phoneNumber)){
            this.phoneNumber.setError("Please Enter Username");
            this.phoneNumber.requestFocus();
            return;
        }
        params.put("firstName",firstName);
        params.put("lastName",lastName);
        params.put("phoneNumber",phoneNumber);
        params.put("emailaddress",email);
        params.put("gender",gender);
        params.put("userPassword",password);
        params.put("age",birthday);


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
    */

}