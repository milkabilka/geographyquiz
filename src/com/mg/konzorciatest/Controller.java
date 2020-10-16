package com.mg.konzorciatest;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private BorderPane base;
    @FXML
    private Label counterLabel;

    List<QuizQuestion> quizQuestions=Quiz.getQuizQuestions();
    GridPane questionPane=new GridPane();
    Label questionLabel=new Label();
    List<RadioButton> answerRBs=new ArrayList<>();
    ToggleGroup toggleGroup=new ToggleGroup();
    Button nextButton=new Button();
    private int questionCounter=0;
    private int rightAnswerCounter=0;


    public void initialize() {
        questionPane.add(questionLabel, 0, 0, 2, 1);
        for (int i=0; i < 4; i++) {
            RadioButton rb=new RadioButton();
            rb.setToggleGroup(toggleGroup);
            answerRBs.add(rb);
            questionPane.add(rb, 0, i + 1);
        }
        questionPane.add(nextButton, 1, 5);
        questionPane.setVgap(15);
        questionPane.setAlignment(Pos.CENTER);

        questionLabel.setWrapText(true);

        nextButton.setText("Next");
        nextButton.setDisable(true);
        nextButton.setOnAction(event -> processAnswer((RadioButton) toggleGroup.getSelectedToggle()));

        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> nextButton.setDisable(false));

    }

    @FXML
    public void handleStartButton() {
        base.setCenter(questionPane);
        counterLabel.setText(questionCounter + " / 10 questions");
        fillLabels(quizQuestions.get(questionCounter));
    }

    private void processAnswer(RadioButton selectedToggle) {
        if (selectedToggle.getText().equals(quizQuestions.get(questionCounter).getRightAnswer())) {
            rightAnswerCounter++;
        }
        questionCounter++;
        if (questionCounter == 10) getResults();
        else {
            fillLabels(quizQuestions.get(questionCounter));
            counterLabel.setText(questionCounter + " / 10 questions");
            if (questionCounter == 10) nextButton.setText("Finish Quiz");
            selectedToggle.setSelected(false);
            nextButton.setDisable(true);
        }
    }

    private void fillLabels(QuizQuestion quizQuestion) {
        questionLabel.setText(quizQuestion.getQuestion());
        Collections.shuffle(answerRBs);
        for (int i=0; i < answerRBs.size(); i++) {
            String s=quizQuestion.getAnswers()[i];
            answerRBs.get(i).setText(s);
        }
    }

    private void getResults() {
        questionPane.getChildren().clear();
        counterLabel.setVisible(false);
        Label end=new Label();
        end.setText("You finished the game. You have " + rightAnswerCounter + " right answers out of " + questionCounter+".");
        questionPane.add(end, 0, 0);
    }

}
