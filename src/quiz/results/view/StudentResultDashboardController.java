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
import javafx.scene.control.Hyperlink;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class StudentResultDashboardController implements Initializable {

    private QuizMain application;
    
    @FXML
    private Hyperlink testResult1;
    @FXML
    private Hyperlink testResult2;
    @FXML
    private Hyperlink testResult3;
    @FXML
    private Hyperlink testResult4;
    @FXML
    private Hyperlink testResult5;
    @FXML
    private Hyperlink testResult6;
    @FXML
    private Hyperlink testResult7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setApp(QuizMain application) {
        this.application = application;
        testResult1.setText("Test 1");
        testResult2.setText("Test 2");
        testResult3.setText("Test 3");
        testResult4.setText("Test 4");
        testResult5.setText("Test 5");
        testResult6.setText("Test 6");
        testResult7.setText("Test 7");
    }
    
}
