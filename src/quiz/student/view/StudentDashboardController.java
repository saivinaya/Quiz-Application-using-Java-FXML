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
 * This class is the controller class for StudentDashboard fxml page; it has
 * initialize(), setApp() as base methods; startTestWindow() to start the test
 * studentResultDashboard() to go to the student view of the last 7 tests taken
 * and logout() to logout of the account
 *
 * @author VinayaSaiD
 */
public class StudentDashboardController implements Initializable {

    private QuizMain application;

    Label errorMessage;
    @FXML
    private Label loginName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void startTestWindow(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            // go to the start test page
            application.gotoStartTest();
        }
    }

    public void setApp(QuizMain application) {
        this.application = application;
        loginName.setText(QuizMain.loginName);
    }

    @FXML
    private void studentResultDashboard(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            // go to the student results dashboard
            application.gotoStudentResultDashboard();
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        // call the logoutAccount() to logout of the application
        application.logoutAccount();
    }

    @FXML
    private void ResultDashboard(ActionEvent event) {
        application.goTOStudentStats();
    }

}
