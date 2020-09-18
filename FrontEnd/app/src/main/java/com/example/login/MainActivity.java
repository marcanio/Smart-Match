package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name= (EditText) findViewById(R.id.LoginUsername);
        Password = (EditText) findViewById(R.id.LoginPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnReg);

        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                validate(Name.getText().toString(), Password.getText().toString());
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
    private void validate(String userName, String userPassword){
        //ToDO- Attach this to a database to check if the user is in the system

        //Below is hardcoded as a test
        if((userName.equals("Admin")) && (userPassword.equals("password"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);


        }else{
            counter--;
            Info.setText("Number of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }
}