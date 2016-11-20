/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.question.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javax.swing.JOptionPane;
import quiz.FillInTheBlanks;
import quiz.MultiChoiceQuestion;
import quiz.Question;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;
import static quiz.student.view.StartTestController.*;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class FillInTheBlanksController implements Initializable {

    private QuizMain application;

    @FXML
    private TextArea questionDescription;
    @FXML
    private TextField userAnswer;
    @FXML
    private Button backButton;
    @FXML
    private Label questionNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Entered the fill in");
        questionNumber.setText("Question " + (questionCounter + 1) + " of " + (selectednumOfQuestions));
        userAnswer.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getUserInput());
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        questionDescription.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getQuestiondesc());
        questionDescription.setTooltip(new Tooltip("Question Description"));
        userAnswer.setTooltip(new Tooltip("Your Answer"));
    }

    public void setApp(QuizMain application, FillInTheBlanks qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        String userAns = userAnswer.getText();
        if (userAns == null || userAns.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("You need to enter an answer to proceed.Or use 'Skip' to Skip the question.");
            alert.showAndWait();
        } else {
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput(userAns);
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(false);
            application.gotoNextQuestion();
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        questionCounter = questionCounter - 1;
        if (questionsForTest.get(questionCounter).getQuestionType().equals("MC")) {
            application.showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("MA")) {
            application.showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("TF")) {
            application.showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("FIB")) {
            application.showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
        }
    }

    @FXML
    private void onSkipButtonClick(ActionEvent event) {
        String userAns = userAnswer.getText();
        System.out.println(userAns);
        if (!(userAns == null)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You have given an answer.Do you wish to remove the answer and proceed.",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(true);
                ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput("");
                application.gotoNextQuestion();
            }
        } else {
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(true);
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput("");
            application.gotoNextQuestion();
        }
    }

    @FXML
    private void onTextEntered(ActionEvent event) {
        String inputGiven;
        inputGiven = userAnswer.getText();
        ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput(inputGiven);
    }
}
