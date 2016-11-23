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
 * This class is the controller class for the FillInTheBlanks fxml page; it has
 * initialize(), setApp() as base methods; onNextButtonClick(),
 * onBackButtonClick(), onSkipButtonClick() and onTextEntered() event related
 * methods.
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
        // setting the question number label
        questionNumber.setText("Question " + (questionCounter + 1) + " of " + (selectednumOfQuestions));
        // these are used when user presses back button,used to remember the previous user answers
        userAnswer.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getUserInput());
        // back button should not be visible for the first question
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        //set the Question description in the text field
        questionDescription.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getQuestiondesc());
        //setup tool tips
        questionDescription.setTooltip(new Tooltip("Question Description"));
        userAnswer.setTooltip(new Tooltip("Your Answer"));
    }

    public void setApp(QuizMain application, FillInTheBlanks qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        String userAns = userAnswer.getText();
        // if the answer is no given need to throw a warning, skip can be used to not answer the question
        if (userAns == null || userAns.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("You need to enter an answer to proceed.Or use 'Skip' to Skip the question.");
            alert.showAndWait();
        } // else need to save the user input into the Question object
        else {
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput(userAns);
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(false);
            // go to the next question using gotoNextQuestion() method
            application.gotoNextQuestion();
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        // reduce the counter by one and send it to the screen based on type of question
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
        // if the user gives an answer then need to throw a confirmation asking if he want to remove the answer and proceed or do not remove
        if (!(userAns == null) || !(userAns.equals(""))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have given an answer.Do you wish to remove the answer and proceed.", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(true);
                ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput("");
                // go to the next question using gotoNextQuestion() method
                application.gotoNextQuestion();
            }
        } // else make the tag SkipQuestion to true and proceed
        else {
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setSkipQuestion(true);
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setUserInput("");
            // go to the next question using gotoNextQuestion() method
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
