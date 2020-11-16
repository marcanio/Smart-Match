package com.example.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    Button submit;
    EditText name;
    EditText feedback;
    EditText severity;


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
                
            }
        });


    }
}