package com.example.login;

import android.content.Context;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LoginTest {
    @Mock
    Context mMockContext;

    @Test
    public void testLogin(){
        MainActivity myObjectUnderTest = new MainActivity();
    }

    @Test
    public void testQuizScore(){
        QuizResults myObjectUnderTest = new QuizResults();
        String testScore = "1/2/1/2/3/2/3/4/2/4/2";
        final String[] realAnswers = {"Disagree strongly", "Disagree a little", "Disagree strongly", "Disagree a little","Neutral", "Disagree a little", "Neutral", "Agree a little", "Disagree a little","Agree a little", "Disagree a little" };

        String[] result = myObjectUnderTest.fillAnswers(testScore);

        assertThat(result, is(realAnswers));

    }
}

/**
 *  if(tokens[i].equals("1")){
 *                 realAnswers[i]= "Disagree strongly";
 *                 quizScore+=1;
 *             }else if(tokens[i].equals("2")){
 *                 realAnswers[i]= "Disagree a little";
 *                 quizScore+=2;
 *             }else if(tokens[i].equals("3")){
 *                 realAnswers[i]= "Neutral";
 *                 quizScore+=3;
 *             }else if(tokens[i].equals("4")){
 *                 realAnswers[i]= "Agree strongly";
 *                 quizScore+=4;
 *             }else{
 *                 realAnswers[i]= "Agree strongly";
 *                 quizScore+=5;
 *             }
 */
