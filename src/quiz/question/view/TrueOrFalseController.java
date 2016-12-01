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
 * This class is the controller class for the TrueOrFalse fxml page; it has
 * initialize(), setApp() as base methods; onNextButtonClick() to go to
 * next question, onBackButtonClick() to go to previous question and
 * onSkipButtonClick() to skip the question event related methods.
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
    @FXML
    private Button nextButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // setting the question number label
        questionNumber.setText("Question " + (questionCounter + 1) + " of " + (selectednumOfQuestions));
        // these are used when user presses back button,used to remember the previous user answers
        optiontrue.setSelected((((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isUserInputTrue()));
        optionfalse.setSelected((((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isUserInputFalse()));
        // back button should not be visible for the first question
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        if (!(optiontrue.isSelected()) && !(optionfalse.isSelected())){
            nextButton.setDisable(true);
        }
        //set the Question description in the text field
        questionDescription.setText(((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).getQuestiondesc());
        //setup tool tips
        questionDescription.setTooltip(new Tooltip("Question Description"));
        optiontrue.setTooltip(new Tooltip("True"));
        optionfalse.setTooltip(new Tooltip("False"));
    }

    public void setApp(QuizMain application, TrueOrFalseQuestion qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        // if the answer is no given need to throw a warning, skip can be used to not answer the question
        if (!(optiontrue.isSelected()) && !(optionfalse.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You need to select an answer to proceed.Or use 'Skip' to Skip the question");
            alert.showAndWait();
        } // else need to save the user input into the Question object
        else {
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputTrue(optiontrue.isSelected());
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputFalse(optionfalse.isSelected());
            // to decrease the  skip counter as it is answered
            if (((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isSkipQuestion())
            {   if (numSkip > 0) {
                    numSkip -= 1;
                }
            }
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(false);
            // go to the next question using gotoNextQuestion() method
            application.gotoNextQuestion();
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        // reduce the counter by one and send it to the screen based on type of question
        questionCounter = questionCounter - 1;
        if (questionsForTest.get(questionCounter).getQuestionType().equals("MC")) {
            // to decrease the  skip counter as it is going back and not to recount
            if (((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isSkipQuestion())
            {   if (numSkip > 0) {
                    numSkip -= 1;
                }
            }
            application.showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("MA")) {
            // to decrease the  skip counter as it is going back and not to recount
            if (((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isSkipQuestion())
            {   if (numSkip > 0) {
                    numSkip -= 1;
                }
            }
            application.showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("TF")) {
            // to decrease the  skip counter as it is going back and not to recount
            if (((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).isSkipQuestion())
            {   if (numSkip > 0) {
                    numSkip -= 1;
                }
            }
            application.showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
        } else if (questionsForTest.get(questionCounter).getQuestionType().equals("FIB")) {
            // to decrease the  skip counter as it is going back and not to recount
            if (((FillInTheBlanks) questionsForTest.get(questionCounter)).isSkipQuestion())
            {   if (numSkip > 0) {
                    numSkip -= 1;
                }
            }
            application.showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
        }
    }

    @FXML
    private void onSkipButtonClick(ActionEvent event) {
        // warning if more than 20% are skipped
        if (numSkip >= maxSkip)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You cannot skip any more questions. You can skip maximum of " + maxSkip + " questions only.", ButtonType.OK);
            alert.showAndWait();
        }
        // if the user gives an answer then need to throw a confirmation asking if he want to remove the answer and proceed or do not remove
        else if ((optiontrue.isSelected()) || (optionfalse.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You selected an answer. Do you wish to remove the selection and proceed.", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(true);
                ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputTrue(false);
                ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputFalse(false);
                numSkip += 1;
                // go to the next question using gotoNextQuestion() method
                application.gotoNextQuestion();
            }
        } // else make the tag SkipQuestion to true and proceed
        else {
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setSkipQuestion(true);
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputTrue(false);
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInputFalse(false);
            numSkip += 1;
            // go to the next question using gotoNextQuestion() method
            application.gotoNextQuestion();
        }
    }

    @FXML
    private void onAnswerSelected(ActionEvent event) {
        if (!(optiontrue.isSelected()) && !(optionfalse.isSelected())){
            nextButton.setDisable(true);
        }
        else{
            nextButton.setDisable(false);
        }
    }
}
