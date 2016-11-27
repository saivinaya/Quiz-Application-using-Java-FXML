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
import quiz.FillInTheBlanks;
import quiz.MultiChoiceQuestion;
import quiz.QuizHelper;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;
import static quiz.student.view.StartTestController.*;

/**
 * This class is the controller class for SubmitPage fxml page; it has 
 * initialize(), setApp() as base methods; onSubmitButtonClick() to submit the test and get the summary of the test
 * onBackButtonClick() to go back to the last question screen
 * @author VinayaSaiD
 */
public class SubmitPageController implements Initializable {
    private QuizMain application;
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
    private void onSubmitButtonClick(ActionEvent event) {
        QuizHelper quizHp = new QuizHelper();
        // evaluate the answers for all the questions using evaluateQuizResult()
        int[] resultArray = application.evaluateTest(questionsForTest);
        // go to the result summary page to show the result
        application.gotoQuizSummaryPage(resultArray, false);
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        // decrement the counter by 1 to go to previous question and based on question type go to that page view
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
