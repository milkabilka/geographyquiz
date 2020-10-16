package com.mg.konzorciatest;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class QuizQuestion {

    private String question;
    private String rightAnswer;
    private String answer1;
    private String answer2;
    private String answer3;

    public QuizQuestion(String question, String rightAnswer, String answer1, String answer2, String answer3) {
        this.question=question;
        this.rightAnswer=rightAnswer;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3=answer3;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String[] getAnswers() {
        return new String[]{rightAnswer, answer1, answer2, answer3};
    }
}
