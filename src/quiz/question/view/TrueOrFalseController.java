/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.question.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import quiz.FillInTheBlanks;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;

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
    private Label questionDescription;
    @FXML
    private RadioButton optiontrue;
    @FXML
    private RadioButton optionfalse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, TrueOrFalseQuestion qust){
        this.application = application;
        setup(qust);
    }

    private void setup(TrueOrFalseQuestion qust) {
        questionDescription.setText(qust.getQuestiondesc());
    }
}
