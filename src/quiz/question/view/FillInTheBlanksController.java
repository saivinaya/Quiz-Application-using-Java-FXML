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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        questionNumber.setText("Question " + (questionCounter+1) + " of " + (selectednumOfQuestions));
        userAnswer.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getUserInput());
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        questionDescription.setText(((FillInTheBlanks) questionsForTest.get(questionCounter)).getQuestiondesc());
    }

    public void setApp(QuizMain application, FillInTheBlanks qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        String userAns = userAnswer.getText();
        System.out.println(userAns);
        if (userAns.equals(null)) {
            JOptionPane.showMessageDialog(null, "You need to enter an answer to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ((FillInTheBlanks) questionsForTest.get(questionCounter)).setAns(userAns);
            questionCounter = questionCounter + 1;
            if (questionCounter < selectednumOfQuestions) {
                if (questionsForTest.get(questionCounter).getQuestionType().equals("MC")) {
                    application.showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
                } else if (questionsForTest.get(questionCounter).getQuestionType().equals("MA")) {
                    application.showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
                } else if (questionsForTest.get(questionCounter).getQuestionType().equals("TF")) {
                    application.showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
                } else if (questionsForTest.get(questionCounter).getQuestionType().equals("FIB")) {
                    application.showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
                }
            } else {
                application.gotoSubmitPage();
            }
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        if (questionCounter == 0) {
            JOptionPane.showMessageDialog(null, "First Question. Cannot go back.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
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
    }
}
