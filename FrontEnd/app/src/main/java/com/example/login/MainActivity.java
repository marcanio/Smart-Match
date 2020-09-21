package com.example.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private DatabaseHelper db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.LoginUsername);
        Password = findViewById(R.id.LoginPassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnReg);
        Info.setText("Number of attempts remaining: 5");
        db = new DatabaseHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
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

    @SuppressLint("SetTextI18n")
    private void validate(String userName, String userPassword) {
       /* debug only!
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("login", "333");
        startActivity(intent);*/
        if (db.checkUser(userName, userPassword)) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("login", userName);
            startActivity(intent);
        } else {
            counter--;
            Info.setText("Number of attempts remaining: " + counter);
            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }
}