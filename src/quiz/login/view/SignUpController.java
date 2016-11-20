/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.login.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author Hari
 */
public class SignUpController implements Initializable {
    
 private QuizMain application;
    @FXML
    private TextField UserName;
    @FXML
    private TextField loginName;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private ComboBox<?> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setApp(QuizMain application) {
        this.application = application;
    }

    @FXML
    private void onClickGotoLogin(ActionEvent event) {
        System.out.println("in goto login");
        application.gotoLogin();
    }

    @FXML
    private void onClickSignUp(ActionEvent event) {
    }
    
}
