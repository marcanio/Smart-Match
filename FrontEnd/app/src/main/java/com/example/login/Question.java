package com.example.login;

public class Question {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String optionE;
    int correct;

    //Constructor
    public Question(String question, String optionA,String optionB,String optionC,String optionD,String optionE,int correct){
        this.question = question;
        this.optionA = optionA;
        this.optionA = optionB;
        this.optionA = optionC;
        this.optionA = optionD;
        this.optionA = optionE;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
