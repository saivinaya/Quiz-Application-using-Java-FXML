/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.results.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class QuizSummaryViewController implements Initializable {

    private QuizMain application;
    
    @FXML
    private Label totalQuestions;
    @FXML
    private Label easyCorrect;
    @FXML
    private Label mediumCorrect;
    @FXML
    private Label hardCorrect;
    @FXML
    private Label totalScore;
    @FXML
    private Label testResult;
    @FXML
    private Label skipped;
    @FXML
    private Label wrongAnswer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, int[] rltArray) {
        this.application = application;
        totalQuestions.setText(Integer.toString(rltArray[0]));
        easyCorrect.setText(Integer.toString(rltArray[1]));
        mediumCorrect.setText(Integer.toString(rltArray[2]));
        hardCorrect.setText(Integer.toString(rltArray[3]));
        skipped.setText(Integer.toString(rltArray[4]));
        double score = rltArray[1] + rltArray[2] + rltArray[3];
        double wrong = rltArray[0] - (score + rltArray[4]);
        totalScore.setText(Integer.toString((int) score));
        wrongAnswer.setText(Integer.toString((int) wrong));
        if ((double) (score/rltArray[0]) > 0.4)
        {   testResult.setText("Passed!");
        }
        else if ((double) (score/rltArray[0]) < 0.4)
        {   testResult.setText("Failed!");
        }
        }

    @FXML
    private void goBackDashboard(ActionEvent event) {
        application.gotoStudentDashboard();
    }
    }
    
