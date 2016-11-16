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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;
import quiz.MultiChoiceQuestion;
import quiz.QuizMain;
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
    /**
     * Initializes the controller class.
     */
    
    
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

    @FXML
    private void onNextButtonClick(ActionEvent event) {
        if (!(choice1.isSelected()) && !(choice2.isSelected()) && !(choice3.isSelected()) && !(choice4.isSelected()))
        {
            JOptionPane.showMessageDialog(null, "You need to select an answer to proceed.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else 
        {
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput1(choice1.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput2(choice2.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput3(choice3.isSelected());
            ((MultiChoiceQuestion) questionsForTest.get(questionCounter)).setUserInput4(choice4.isSelected());
        }
    }
}
