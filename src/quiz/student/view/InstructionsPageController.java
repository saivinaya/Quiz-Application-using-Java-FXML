/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class InstructionsPageController implements Initializable {
    private QuizMain application;
    Label errorMessage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setApp(QuizMain application) {
        this.application = application;
    }
    
    @FXML
    private void onStartTest(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            questionsForTest = application.getQuestions(selectednumOfQuestions, selectedDifficulty);
            // iterate through the questions
            questionCounter = 0;
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

    @FXML
    private void onCancel(ActionEvent event) {
        application.gotoStartTest();
    }
    
}
