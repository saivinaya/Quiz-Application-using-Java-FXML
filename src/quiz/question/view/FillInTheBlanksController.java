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
import quiz.Question;
import quiz.QuizMain;
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
    }
}
