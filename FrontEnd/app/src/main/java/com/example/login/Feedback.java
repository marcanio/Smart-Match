package com.example.login;


import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

public class Feedback extends AppCompatActivity {
    Button submit;
    EditText name;
    EditText feedback;
    EditText severity;
    String URL_VERIFY = "http://coms-309-vb-10.cs.iastate.edu:8080/feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        submit = (Button)findViewById(R.id.button9);
        name = (EditText)findViewById(R.id.Username);
        feedback = (EditText)findViewById(R.id.edit_text);
        severity = (EditText)findViewById(R.id.Severity);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postResults();
            }
        });


    }
    public void postResults() {
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject params = new JSONObject();

        try {
            params.put("email_address", email);
            params.put("severity", severity.getText().toString().trim());
            params.put("feedback", feedback.getText().toString().trim());
            params.put("first_name", name.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_VERIFY, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println();
                Log.e("Response", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}