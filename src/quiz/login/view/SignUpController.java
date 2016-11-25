//SignUpController.java
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
 * SignUpController: This is the controller class for signup.fxml. This class
 * handles addition of new user to the application
 * @author Hari
 */
public class SignUpController implements Initializable {
   //create instance of application
    private QuizMain application;
   // fxml variables used 
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
     * setApp: This method used to assign this page to the application
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
    }//end of setApp

    /**
     * initialize:Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //required to initiaze the values pertaining to the combobox
        try {
            if (QuizMain.role.equals("Admin")) {
                //if admin user logs in then give the option to add student or admin
                role.getItems().removeAll(role.getItems());
                role.getItems().addAll("Student", "Admin");
                role.getSelectionModel().select("Admin");
            } else {
                role.getItems().removeAll(role.getItems());
                //for all non admin users only option to addd is student
                role.getItems().addAll("Student");
                role.getSelectionModel().select("Student");
            }
        } catch (Exception exp) {
            //catch block. only student option given in case of any errors in initialization
            role.getItems().removeAll(role.getItems());
            role.getItems().addAll("Student");
           // role.getSelectionModel().select("Student");
        }
    }//end of initialize

    @FXML
    private void onClickSignUp(ActionEvent event) {
       //create instance of quizDB implementation
        QuizDBImplementation impl = new QuizDBImplementation();
        boolean addFlag = true;
      
        try {
            //if all the fields are not empty then addd the values to db
            if (!(loginName.getText().trim().isEmpty() || UserName.getText().trim().isEmpty() || password1.getText().trim().isEmpty() || password2.getText().trim().isEmpty())) {
                if (!(role.getSelectionModel().getSelectedItem().toString().equals("Student") | role.getSelectionModel().getSelectedItem().toString().equals("Admin"))) {
                    //alert the user that role is missing in the drop down selection
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Please select value "+((QuizMain.role.equals("Admin"))?"Student or Admin":"Student") +" as the role from the dropdown list");
                    alert.showAndWait();
                } else {
                    if (password1.getText().trim().equals(password2.getText().trim())) {
                        //get the keyed in values to variables
                        String loginNameValue = loginName.getText().trim();
                        String userNameValue = UserName.getText().trim();
                        String passwordValue = password1.getText().trim();
                        String roleValue = role.getSelectionModel().getSelectedItem().toString();
                        //check if the user already exists
                        addFlag = impl.selectUser(loginNameValue);
                        //if does not exist then add
                        if (addFlag == true) {
                            application.addUser(loginNameValue, userNameValue, passwordValue, roleValue);
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setContentText("New User Successfully Added. Please select back button for the login screen");
                            alert.showAndWait();
                            //reset the text fields to null
                            loginName.setText(null);
                            UserName.setText(null);
                            password1.setText(null);
                            password2.setText(null);
                            role.setValue(null);
                        } else {
                            //if exists then alert with error stating login name already used
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Login Name already used. Please try with a new Login Name");
                            alert.showAndWait();
                        }

                    } else {
                        //if passwords entered has mismatch then alert the same to user
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("There is mismatch in password. Please ensure password in both the fields are same");
                        alert.showAndWait();
                    }
                }
            } else {
                //alert the user if all the fields are not populated
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Please ensure all the fields are populated. Try again");
                alert.showAndWait();
            }
        } catch (NullPointerException exp) {
            //incase of nullpointerexcpetion alert the user about the fields not being populated
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please ensure all the fields are populated. Try again");
            alert.showAndWait();
        }
    }//end of onClickSignUp

    @FXML
    private void onClickGotoLogin() {
        try{
        if (QuizMain.role.equals("Admin")) {
            //onclick event go to admin dashboard if the user is admin
            application.gotoAdminDashboard();
        } else {
            //on click go to login page if the user is student
            application.gotoLogin();
        }}catch(Exception exp){
         application.gotoLogin();
        }
    }//end of onClickGotoLogin

}
