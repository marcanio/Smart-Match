package com.example.login;

public class Question {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String optionE;
    int correct;

    /**
     * Constructor for the quiz
     * @param question - Current question of the quiz
     * @param optionA - Option A for the current quiz question
     * @param optionB - Option B for the current quiz question
     * @param optionC - Option C for the current quiz question
     * @param optionD - Option D for the current quiz question
     * @param optionE - Option E for the current quiz question
     * @param correct - The correct answer for the quiz (No longer used since there is no correct answers)
     */
    public Question(String question, String optionA, String optionB, String optionC, String optionD, String optionE, int correct) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
        this.correct = correct;
    }

    /**
     * Getter for the question
     * @return - Current question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter for the question
     * @param question - Current question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter for option A on the quiz
     * @return - Option A
     */
    public String getOptionA() {
        return optionA;
    }

    /**
     * Setter for option A on the quiz
     * @param optionA - Option A on the quiz
     */
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    /**
     * Getter for option B on the quiz
     * @return - Option B
     */
    public String getOptionB() {
        return optionB;
    }
    /**
     * Setter for option B on the quiz
     * @param optionB - Option B on the quiz
     */
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    /**
     * Getter for option C on the quiz
     * @return - Option C
     */
    public String getOptionC() {
        return optionC;
    }
    /**
     * Setter for option C on the quiz
     * @param optionC - Option C on the quiz
     */
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    /**
     * Getter for option D on the quiz
     * @return - Option D
     */
    public String getOptionD() {
        return optionD;
    }
    /**
     * Setter for option D on the quiz
     * @param optionD - Option D on the quiz
     */
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    /**
     * Getter for option E on the quiz
     * @return - Option E
     */
    public String getOptionE() {
        return optionE;
    }
    /**
     * Setter for option E on the quiz
     * @param optionE - Option E on the quiz
     */
    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    /**
     * Get the correct answer to a question
     * @return - Correct answer
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * Setter for the correct answer
     * @param correct - Correct answer
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
