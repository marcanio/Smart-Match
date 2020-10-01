package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final String userLogin = getIntent().getExtras().getString("login");
        final DatabaseHelper db = new DatabaseHelper(this);
        String[] user = db.getUserData(userLogin);
        TextView name = findViewById(R.id.textView);
        name.setText(user[0] + " " + user[1]);
        TextView age = findViewById(R.id.age);
        final TextView bio = findViewById(R.id.User_bio);
        bio.setText(user[3]);
        try {
            Date userDob = new SimpleDateFormat("dd/M/yyyy").parse(user[2]);
            Date today = new Date();
            long different = today.getTime() - userDob.getTime();
            long elapsedDays = different / 31557600000L;
            age.setText(String.valueOf(elapsedDays));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final EditText edittext = findViewById(R.id.addBio);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    bio.setText(String.valueOf(edittext.getText()));
                    db.updateBio(userLogin, String.valueOf(edittext.getText()));
                    edittext.setText("");
                    return true;
                }
                return false;
            }
        });
    }
}