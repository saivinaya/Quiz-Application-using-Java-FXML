//SignUpController.java
package quiz.login.view;
//import required packagess

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quiz.QuizDBImplementation;
import quiz.QuizMain;

/**
 * SignUpController: Controller class for SigUp page. This class has the methods
 * that adds new user to database. When the class is initialized the drop down
 * list is initialized based on the role of the user
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
     * setApp: used to set the fxml
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ;
        //IF the user is admin then the drop down options are Student and Admin
        try {
            if (QuizMain.role.equals("Admin")) {
                role.getItems().removeAll(role.getItems());
                role.getItems().addAll("Student", "Admin");
                role.getSelectionModel().select("Admin");
            } else {//if not Admin then the dropdown would only have Student entry
                role.getItems().removeAll(role.getItems());
                role.getItems().addAll("Student");
                role.getSelectionModel().select("Student");
            }
        } catch (Exception exp) {
            role.getItems().removeAll(role.getItems());
            role.getItems().addAll("Student");
        }
    }//end of initialize

    @FXML
    private void onClickSignUp(ActionEvent event) {

        QuizDBImplementation impl = new QuizDBImplementation();
        boolean addFlag = true;

        try {//check if all teh fields are populated
            if (!(loginName.getText().isEmpty() || UserName.getText().isEmpty() || password1.getText().isEmpty() || password2.getText().isEmpty())) {
                //validate the selection from drop down list
                if (!(role.getSelectionModel().getSelectedItem().toString().equals("Student") | role.getSelectionModel().getSelectedItem().toString().equals("Admin"))) {
                    //pop up alert if the selections are not valid
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please select value Student or Admin as the role from the dropdown list");
                    alert.showAndWait();
                } else {//check if the passwords are matching
                    if (password1.getText().equals(password2.getText())) {
                        String loginNameValue = loginName.getText();
                        String userNameValue = UserName.getText();
                        String passwordValue = password1.getText();
                        String roleValue = role.getSelectionModel().getSelectedItem().toString();
                        //check if the user is already present in the database
                        addFlag = impl.selectUser(loginNameValue);
                        //execute following code if not already present
                        if (addFlag == true) {
                            application.addUser(loginNameValue, userNameValue, passwordValue, roleValue);
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("New User Successfully Added. Please select back button for the login screen");
                            alert.showAndWait();
                            //reset the values after addition
                            loginName.setText(null);
                            UserName.setText(null);
                            password1.setText(null);
                            password2.setText(null);
                            role.setValue(null);
                        } else {
                            //if already present then alert the user that the login name is already used
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Login Name already used. Please try with a new Login Name");
                            alert.showAndWait();
                        }

                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        //alert if there is mismatch in password
                        alert.setContentText("There is mismatch in password. Please ensure password in both the fields are same");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                //alert if all the field are not populated
                alert.setContentText("Please ensure all the fields are populated. Try again");
                alert.showAndWait();
            }
        } catch (NullPointerException exp) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please ensure all the fields are populated. Try again");
            alert.showAndWait();
        }
    }//end of onClickSignUp

    @FXML
    private void onClickGotoLogin() {
        try {
            if (QuizMain.role.equals("Admin")) {
                //goto admin dashboard if the user is Admin
                application.gotoAdminDashboard();
            } else {
                //for all other cases go to login page
                application.gotoLogin();
            }
        } catch (Exception exp) {
            application.gotoLogin();
        }
    }//end of onClickGotoLogin

}//end of class SignUpController
