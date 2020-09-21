package com.example.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizResults<button> extends AppCompatActivity {
    private TextView Q1A, Q2A, Q3A, Q4A, Q5A, Q6A, Q7A, Q8A, Q9A, Q10A, Q11A;
    private Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        Q1A = findViewById(R.id.Q1A);
        Q2A = findViewById(R.id.Q2A);
        Q3A = findViewById(R.id.Q3A);
        Q4A = findViewById(R.id.Q4A);
        Q5A = findViewById(R.id.Q5A);
        Q6A = findViewById(R.id.Q6A);
        Q7A = findViewById(R.id.Q7A);
        Q8A = findViewById(R.id.Q8A);
        Q9A = findViewById(R.id.Q9A);
        Q10A = findViewById(R.id.Q10A);
        Q11A = findViewById(R.id.Q11A);
        //Button to change pages
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizResults.this, Profile.class);
                intent.putExtra("login", getIntent().getExtras().getString("login"));
                startActivity(intent);
            }
        });
        //Get data from last page
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String results = bundle.getString("quizResults");
        assert results != null;
        String[] realAnswers = fillAnswers(results);
        displayAnswers(realAnswers);
    }

    private void displayAnswers(String[] realAnswers) {
        Q1A.setText(realAnswers[0]);
        Q2A.setText(realAnswers[1]);
        Q3A.setText(realAnswers[2]);
        Q4A.setText(realAnswers[3]);
        Q5A.setText(realAnswers[4]);
        Q6A.setText(realAnswers[5]);
        Q7A.setText(realAnswers[6]);
        Q8A.setText(realAnswers[7]);
        Q9A.setText(realAnswers[8]);
        Q10A.setText(realAnswers[9]);
        Q11A.setText(realAnswers[10]);
    }

    public String[] fillAnswers(String results) {
        String[] tokens = results.split("/");
        String[] realAnswers = new String[tokens.length];
        for (int i = 0; i < realAnswers.length; i++) {
            switch (tokens[i]) {
                case "1":
                    realAnswers[i] = "Disagree strongly";
                    break;
                case "2":
                    realAnswers[i] = "Disagree a little";
                    break;
                case "3":
                    realAnswers[i] = "Neutral";
                    break;
                case "4":
                    realAnswers[i] = "Agree strongly";
                    break;
                default:
                    realAnswers[i] = "Agree strongly";
                    break;
            }
        }
        return realAnswers;
    }
}