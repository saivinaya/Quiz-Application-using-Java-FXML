/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.admin.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author Hari
 */
public class AdminDashboardController implements Initializable {
   private QuizMain application;
    @FXML
    private Label userLabel;
    @FXML
    private Label loginName;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginName.setText(QuizMain.loginName);
        System.out.println("Loading Admin");
    }    
        public void setApp(QuizMain application){
        this.application = application;
    }

    @FXML
    private void onClickUploadFile(ActionEvent event) {
        application.gotoUploadFile();
    }

    @FXML
    private void onClickSignUpUser(ActionEvent event) {
        application.gotoSignUpScreen();
    }

    @FXML
    private void onClickViewStudentPerformance(ActionEvent event) {
        application.goTOStudentStats();
    }

    @FXML
    private void logout(ActionEvent event) {
        application.logoutAccount();
    }
    
    
}
