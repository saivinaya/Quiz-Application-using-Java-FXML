/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.login.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import quiz.QuizDBImplementation;
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
    private ComboBox<String> role;

    /**
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            if (QuizMain.role.equals("Admin")) {
                role.getItems().removeAll(role.getItems());
                role.getItems().addAll("Student", "Admin");
                role.getSelectionModel().select("Admin");
            } else {
                role.getItems().removeAll(role.getItems());
                role.getItems().addAll("Student");
                role.getSelectionModel().select("Student");
            }
        } catch (Exception exp) {
            role.getItems().removeAll(role.getItems());
            role.getItems().addAll("Student");
           // role.getSelectionModel().select("Student");
        }
    }

    @FXML
    private void onClickSignUp(ActionEvent event) {

        QuizDBImplementation impl = new QuizDBImplementation();
        boolean addFlag = true;

        try {
            if (!(loginName.getText().equals("") && UserName.getText().equals("") && password1.getText().equals("") && password2.getText().equals(""))) {
                if (!(role.getSelectionModel().getSelectedItem().toString().equals("Student") | role.getSelectionModel().getSelectedItem().toString().equals("Admin"))) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please select value Student or Admin as the role from the dropdown list");
                    alert.showAndWait();
                } else {
                    if (password1.getText().equals(password2.getText())) {
                        String loginNameValue = loginName.getText();
                        String userNameValue = UserName.getText();
                        String passwordValue = password1.getText();
                        String roleValue = role.getSelectionModel().getSelectedItem().toString();

                        addFlag = impl.selectUser(loginNameValue);

                        if (addFlag == true) {
                            application.addUser(loginNameValue, userNameValue, passwordValue, roleValue);
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("New User Successfully Added. Please select back button for the login screen");
                            alert.showAndWait();
                            loginName.setText(null);
                            UserName.setText(null);
                            password1.setText(null);
                            password2.setText(null);
                            role.setValue(null);
                        } else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Login Name already used. Please try with a new Login Name");
                            alert.showAndWait();
                        }

                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("There is mismatch in password. Please ensure password in both the fields are same");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Please ensure all the fields are populated. Try again");
                alert.showAndWait();
            }
        } catch (NullPointerException exp) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please ensure all the fields are populated. Try again");
            alert.showAndWait();
        }
    }

    @FXML
    private void onClickGotoLogin() {
        try{
        if (QuizMain.role.equals("Admin")) {
            application.gotoAdminDashboard();
        } else {
            application.gotoLogin();
        }}catch(Exception exp){
         application.gotoLogin();
        }
    }

}
