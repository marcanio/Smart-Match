package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount;
    private Button option1,option2,option3,option4,option5;
    private List<Question> questionList;
    int questNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Map varibles to there objects in the xml
        question =findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);

        getQuestionList();


    }

    private void getQuestionList() {
    questionList = new ArrayList<>();
    //todo - Input actual questions when it starts working
    questionList.add(new Question("This is question one","A1","B","C","D","E",3));
        questionList.add(new Question("This is question 1","A2","B","C","D","E",3));
        questionList.add(new Question("This is question 2","A3","B","C","D","E",3));
        questionList.add(new Question("This is question 3","A4","B","C","D","E",3));
        questionList.add(new Question("This is question 4","A5","B","C","D","E",3));
        questionList.add(new Question("This is question 5","A6","B","C","D","E",3));

        setQuestion();
    }

    private void setQuestion() {
        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());
        option5.setText(questionList.get(0).getOptionE());

        qCount.setText(String.valueOf(1)+ "/"+String.valueOf(questionList.size()));

        questNum = 0;
    }

    @Override
    public void onClick(View view) {
        int selectedOption =0;

        switch(view.getId()){

            case R.id.option1:
                selectedOption=1;
                break;
            case R.id.option2:
                selectedOption =2;
                break;
            case R.id.option3:
                selectedOption=3;
                break;
            case R.id.option4:
                selectedOption=4;
                break;
            case R.id.option5:
                selectedOption=5;
                break;
            default:
        }
        scoreAnswer(selectedOption);

    }

    private void scoreAnswer(int selectedOption) {
        //todo - Look at answer and score accordingly
        changeQuestion();
    }

    private void changeQuestion() {
        if(questNum < questionList.size() -1){
            playAnim(option1,0);
        }else{
            //Display score- Last question
            Intent intent = new Intent(SecondActivity.this, ProfileActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
    }

    private void playAnim(View view,final int value) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}