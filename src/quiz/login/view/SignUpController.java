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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import quiz.QuizDBImplementation;
import quiz.QuizMain;
import quiz.User;
import static sun.security.krb5.KrbException.errorMessage;

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

    public void setApp(QuizMain application) {
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onClickSignUp(ActionEvent event) {

        if (!(role.getSelectionModel().getSelectedItem().toString().equals("Student") | role.getSelectionModel().getSelectedItem().toString().equals("Admin")| role.getSelectionModel().getSelectedItem().toString().equals(null))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please select value Student or Admin as the role from the dropdown list");
            alert.showAndWait();
        } else {
            if (password1.getText().equals(password2.getText())) {
                String loginNameValue = loginName.getText();
                String userNameValue = UserName.getText();
                String passwordValue = password1.getText();
                String roleValue = role.getSelectionModel().getSelectedItem().toString();
                addUser(loginNameValue, userNameValue, passwordValue, roleValue);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("New User Successfully Added. Please select back button for the login screen");
                alert.showAndWait();
                loginName.setText(null);
                UserName.setText(null);
                password1.setText(null);
                password1.setText(null);
                role.setValue(null);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("There is mismatch in password. Please ensure password in both the fields are same");
                alert.showAndWait();
            }
        }
    }

    private void addUser(String loginName, String userName, String password, String uniRole) {
        System.out.println("in addUser");
        User usr = new User(loginName, userName, password, uniRole);
        System.out.println("AddUser");
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        qzImpl.addUser(usr);
    }

}
