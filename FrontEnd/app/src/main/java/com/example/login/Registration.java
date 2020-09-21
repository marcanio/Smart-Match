package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    private EditText firstName, lastName, email, password, birthday, gender, phoneNumber;
    private static String URL_REGIST = "https://postman-echo.com/post";
    DatePickerDialog picker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        birthday = findViewById(R.id.birthday);
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Registration.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthday.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear + 1, year));
                    }
                }, year-16, month, day);
                picker.show();
            }
        });
        phoneNumber = findViewById(R.id.mobile);
        Button btn_regist = findViewById(R.id.submitButton);
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
        DatabaseHelper db = new DatabaseHelper(this);
        db.register(firstName, lastName, email, password, birthday, gender, phoneNumber);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstName);
                params.put("lastname", lastName);
                params.put("email", email);
                params.put("password", password);
                params.put("birthday", birthday);
                params.put("gender", gender);
                params.put("phonenumber", phoneNumber);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}