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
import quiz.MultiChoiceQuestion;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class MultipleWithOneAnswerController implements Initializable {
     private QuizMain application;
    /**
     * Initializes the controller class.
     */
     
    @FXML
    private ToggleGroup Choices;
    @FXML
    private Label questionDescription;
    @FXML
    private RadioButton choice1;
    @FXML
    private RadioButton choice2;
    @FXML
    private RadioButton choice3;
    @FXML
    private RadioButton choice4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, MultiChoiceQuestion qust){
        this.application = application;
        setup(qust);
    }

    private void setup(MultiChoiceQuestion qust) {
        questionDescription.setText(qust.getQuestiondesc());
        choice1.setText(qust.getChoice1());
        choice2.setText(qust.getChoice2());
        choice3.setText(qust.getChoice3());
        choice4.setText(qust.getChoice4());
    }
}
