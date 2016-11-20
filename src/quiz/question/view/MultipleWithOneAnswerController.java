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
import quiz.MultiChoiceQuestion;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;
import static quiz.student.view.StartTestController.*;

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
    private TextArea questionDescription;
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
