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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea questionDescription;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, FillInTheBlanks qust){
        this.application = application;
        setup(qust);
    }

    private void setup(FillInTheBlanks qust) {
        questionDescription.setText(qust.getQuestiondesc());
    }

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        String userAns = null;
        ((FillInTheBlanks) questionsForTest.get(questionCounter)).setAns(userAns);
        questionCounter = questionCounter +1;
        if (questionCounter < selectednumOfQuestions)
        {
        if (questionsForTest.get(questionCounter).getQuestionType().equals("MC"))
        {
            //System.out.println("1"+question.getQuestiondesc());
            application.showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        }
        else if (questionsForTest.get(questionCounter).getQuestionType().equals("MA"))
        {
            //System.out.println("2"+question.getQuestiondesc());
            application.showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
        }
        else if (questionsForTest.get(questionCounter).getQuestionType().equals("TF"))
        {
            //System.out.println("3"+question.getQuestiondesc());
            application.showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
        }
        else if (questionsForTest.get(questionCounter).getQuestionType().equals("FIB")) 
        {
            //System.out.println("4"+question.getQuestiondesc());
            application.showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
        }
        }
        else
        {
            application.gotoStartTest();
        }
    }
}
