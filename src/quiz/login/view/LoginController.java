/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.login.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author Hari
 */
public class LoginController implements Initializable {

    private QuizMain application;
    @FXML
    TextField userName;
    @FXML
    TextField password;
    Button signin;
    Label errorMessage;
    @FXML
    private Button signIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loading Login page...");
    }

    public void setApp(QuizMain application) {
        this.application = application;
    }

    @FXML
    public void onClickSignIn() {
        application.gotoAdminDashboard();
    }

    public void onClickSignUp() {
        application.gotoSignUpScreen();

    }

}
