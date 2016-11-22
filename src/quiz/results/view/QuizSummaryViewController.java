/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.results.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class QuizSummaryViewController implements Initializable {

    private QuizMain application;
    private int[] resultArray;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, int[] rltArray) {
        this.application = application;
    }
    
}
