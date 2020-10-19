package com.example.login;

import android.content.Context;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class QuizTests {

    /**
     * Test quiz score tests the method on the quiz results page that displays the users answers to the questions
     */
    @Test
    public void testQuizScore(){
        QuizResults myObjectUnderTest = new QuizResults();
        String testScore = "1/2/1/2/3/2/3/4/2/4/2";
        String testScore1 = "3/2/1/2/3/5/3/4/5/4/1";
        String testScore2 = "1/2/1/2/3/2/3/4/2/4/2";

        final String[] realAnswers = {"Disagree strongly", "Disagree a little", "Disagree strongly", "Disagree a little","Neutral", "Disagree a little", "Neutral", "Agree a little", "Disagree a little","Agree a little", "Disagree a little" };
        final String[] realAnswers1 = {"Neutral", "Disagree a little", "Disagree strongly", "Disagree a little","Neutral", "Agree strongly", "Neutral", "Agree a little", "Agree strongly","Agree a little", "Disagree strongly" };
        final String[] realAnswers2 = {"Disagree strongly", "Disagree a little", "Disagree strongly", "Disagree a little","Neutral", "Disagree a little", "Neutral", "Agree a little", "Disagree a little","Agree a little", "Disagree a little" };

        String[] result = myObjectUnderTest.fillAnswers(testScore);
        String[] result1 = myObjectUnderTest.fillAnswers(testScore1);
        String[] result2 = myObjectUnderTest.fillAnswers(testScore2);


        assertThat(result, is(realAnswers));
        assertThat(result1, is(realAnswers1));
        assertThat(result2, is(realAnswers2));
    }

    /**
     * Tests the getter methods for the question class
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void questionGetter() throws NoSuchFieldException, IllegalAccessException {
        String question = "Question";
        String optionA = "optionA";
        String optionB= "optionB";
        String optionC= "optionC";
        String optionD= "optionD";
        String optionE= "optionE";
        int correct= 2;

        //Given
        Question myObjectUnderTest = new Question(question,optionA,optionB,optionC,optionD,optionE,correct);
        //Question
        final Field field = myObjectUnderTest.getClass().getDeclaredField("question");
        field.setAccessible(true);
        field.set(myObjectUnderTest,"Question");
        //Options
        final Field fieldA = myObjectUnderTest.getClass().getDeclaredField("optionA");
        fieldA.setAccessible(true);
        fieldA.set(myObjectUnderTest,"optionA");

        final Field fieldB = myObjectUnderTest.getClass().getDeclaredField("optionB");
        fieldB.setAccessible(true);
        fieldB.set(myObjectUnderTest,"optionB");

        final Field fieldC = myObjectUnderTest.getClass().getDeclaredField("optionC");
        fieldC.setAccessible(true);
        fieldC.set(myObjectUnderTest,"optionC");

        final Field fieldD = myObjectUnderTest.getClass().getDeclaredField("optionD");
        fieldD.setAccessible(true);
        fieldD.set(myObjectUnderTest,"optionD");

        final Field fieldE = myObjectUnderTest.getClass().getDeclaredField("optionE");
        fieldE.setAccessible(true);
        fieldE.set(myObjectUnderTest,"optionE");


        //When
        final String result = myObjectUnderTest.getQuestion();
        final String resultA = myObjectUnderTest.getOptionA();
        final String resultB = myObjectUnderTest.getOptionB();
        final String resultC = myObjectUnderTest.getOptionC();
        final String resultD = myObjectUnderTest.getOptionD();
        final String resultE = myObjectUnderTest.getOptionE();

        //then
        assertThat(result, is("Question"));
        assertThat(resultA, is("optionA"));
        assertThat(resultB, is("optionB"));
        assertThat(resultC, is("optionC"));
        assertThat(resultD, is("optionD"));
        assertThat(resultE, is("optionE"));

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
