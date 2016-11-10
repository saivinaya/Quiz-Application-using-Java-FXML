/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import quiz.QuizMain;


/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class StudentDashboardController implements Initializable {
    private QuizMain application;
    
    @FXML
    Label errorMessage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startTestWindow(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } 
        else 
        {
            application.gotoStartTest();
        }
    }
    public void setApp(QuizMain application){
        this.application = application;
    }
    
}
