/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.question.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javax.swing.JOptionPane;
import quiz.FillInTheBlanks;
import quiz.MultiChoiceQuestion;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;
import static quiz.student.view.StartTestController.*;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class TrueOrFalseController implements Initializable {

    private QuizMain application;
    @FXML
    private ToggleGroup Choices;
    @FXML
    private TextArea questionDescription;
    @FXML
    private RadioButton optiontrue;
    @FXML
    private RadioButton optionfalse;
    @FXML
    private Button backButton;
    @FXML
    private Label questionNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionNumber.setText("Question " + (questionCounter + 1) + " of " + (selectednumOfQuestions));
        optiontrue.setSelected(((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isUserInput());
        optiontrue.setSelected(((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isUserInput());
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        questionDescription.setText(((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).getQuestiondesc());
        questionDescription.setTooltip(new Tooltip("Question Description"));
        optiontrue.setTooltip(new Tooltip("True"));
        optionfalse.setTooltip(new Tooltip("False"));
    }

    public void setApp(QuizMain application, TrueOrFalseQuestion qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        boolean userChoice = false;
        if (!(optiontrue.isSelected()) && !(optionfalse.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You need to select an answer to proceed.Or use 'Skip' to Skip the question");
            alert.showAndWait();
        } else {
            if (optiontrue.isSelected()) {
                userChoice = true;
            }
            if (optionfalse.isSelected()) {
                userChoice = false;
            }
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInput(userChoice);
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(false);
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
        if ((optiontrue.isSelected()) || (optionfalse.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You selected an answer. Do you wish to remove the selection and proceed.",ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(true);
                ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInput(false);
                application.gotoNextQuestion();
            }
        } else {
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(true);
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInput(false);
            application.gotoNextQuestion();
        }
    }
}
