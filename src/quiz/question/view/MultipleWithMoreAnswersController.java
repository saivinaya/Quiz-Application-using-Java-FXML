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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class MultipleWithMoreAnswersController implements Initializable {

    private QuizMain application;
    @FXML
    private TextArea questionDescription;
    @FXML
    private CheckBox choice1;
    @FXML
    private CheckBox choice2;
    @FXML
    private CheckBox choice3;
    @FXML
    private CheckBox choice4;
    @FXML
    private Button backButton;
    @FXML
    private Label questionNumber;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionNumber.setText("Question " + (questionCounter+1) + " of " + (selectednumOfQuestions));
        choice1.setSelected(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isUserInput1());
        choice2.setSelected(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isUserInput2());
        choice3.setSelected(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isUserInput3());
        choice4.setSelected(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).isUserInput4());
        if (questionCounter == 0) {
            backButton.setVisible(false);
        }
        questionDescription.setText(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).getQuestiondesc());
        choice1.setText(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).getChoice1());
        choice2.setText(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).getChoice2());
        choice3.setText(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).getChoice3());
        choice4.setText(((MultiChoiceQuestion) questionsForTest.get(questionCounter)).getChoice4());
    }

    public void setApp(QuizMain application, MultiChoiceQuestion qust) {
        this.application = application;
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        if (!(choice1.isSelected()) && !(choice2.isSelected()) && !(choice3.isSelected()) && !(choice4.isSelected())) {
            JOptionPane.showMessageDialog(null, "You need to select an answer to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput1(choice1.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput2(choice2.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput3(choice3.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput4(choice4.isSelected());
        }
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
        } else if (questionCounter == selectednumOfQuestions){
            application.gotoSubmitPage();
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
