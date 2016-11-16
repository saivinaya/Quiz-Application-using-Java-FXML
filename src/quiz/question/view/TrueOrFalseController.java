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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;
import quiz.FillInTheBlanks;
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

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        boolean userChoice = false;
        if (!(optiontrue.isSelected()) && !(optionfalse.isSelected()))
        {
            JOptionPane.showMessageDialog(null, "You need to select an answer to proceed.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else 
        {
            if (optiontrue.isSelected())
            {   userChoice  = true;
            }
            if (optionfalse.isSelected())
            {   userChoice  = false;
            }
            ((TrueOrFalseQuestion) questionsForTest.get(questionCounter)).setUserInput(userChoice);
            
        }
    }
}
