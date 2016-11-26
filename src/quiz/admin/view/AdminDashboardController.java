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
 * AdminDashboardController: This class is the controller for AdminDashboard.
 * @author Hari
 */
public class AdminDashboardController implements Initializable {
   private QuizMain application;
    @FXML
    private Label userLabel;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText("Welcome "+QuizMain.loginName);
    }    

    /**
     *
     * @param application
     */
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
