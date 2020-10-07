package com.example.login;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class Profile extends AppCompatActivity {
    private static String URL_REGIST = "https://e88bf2da-6812-4702-8725-be192a447d6d.mock.pstmn.io";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView bio = findViewById(R.id.User_bio);
        final EditText edittext = findViewById(R.id.addBio);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    final String newBio = String.valueOf(edittext.getText());
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    Map<String, String> params = new HashMap<>();
                    params.put("bio", newBio);
                    JSONObject parameters = new JSONObject(params);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_REGIST, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("Response", "" + response);
                            bio.setText(newBio);
                            edittext.setText("");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                    return true;
                }
                return false;
            }
        });
        final TextView score = findViewById(R.id.quizScore);
        int scoreI = getIntent().getExtras().getInt("quizScore",0);
        score.setText("Quiz score: " + scoreI);
    }

    public void onButtonClick(View view) {
    }
}