package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
    private static String URL_VERIFY ="http://coms-309-vb-10.cs.iastate.edu:8080/users/verifies";



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
                //validate();
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
    //@SuppressLint("SetTextI18n")

}