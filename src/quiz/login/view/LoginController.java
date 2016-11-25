//LoginController.java
package quiz.login.view;

import java.net.URL;
import java.sql.SQLNonTransientConnectionException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.derby.drda.NetworkServerControl;
import quiz.QuizDBImplementation;
import quiz.QuizMain;
import quiz.User;

/**
 * FXML Controller class LoginController: Controller class for the login page
 *
 * @author Hari
 */
public class LoginController implements Initializable {
    //instance of the main application

    private QuizMain application;
    //variables used in the fxml page
    @FXML
    private TextField loginName;
    @FXML
    private TextField password;
    @FXML
    private Button signin;
    @FXML
    private Label errorMessage;
    @FXML
    private Button signIn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * setApp: used by main application to set page
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
    }

    /**
     * onClickSignIn: onclick even
     *
     * @param ae
     */
    @FXML
    public void onClickSignIn(ActionEvent ae) {
        QuizDBImplementation impl = new QuizDBImplementation();
        try {
            if (!(loginName.getText().trim().isEmpty() || password.getText().trim().isEmpty())) {
                User user = impl.selectUser(loginName.getText().trim(), password.getText().trim());
                if (user.getUniRole().equals("Admin")) {
                    //assign loginname and role to static variables
                    QuizMain.loginName = user.getUserName().trim();
                    QuizMain.role = "Admin";
                    application.gotoAdminDashboard();
                }
                if (user.getUniRole().equals("Student")) {
                    //assign loginname and role to static variables
                    QuizMain.loginName = loginName.getText().trim();
                    QuizMain.role = "Student";
                    application.gotoStudentDashboard();
                }
            } else {
                //alert if username pwd is wrong
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect username/password");
                alert.showAndWait();
            }
        } catch (NullPointerException exp) {
            //alert the user in case of exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username/password");
            alert.showAndWait();
        } catch (Exception exp) {
            //alert the user in case of exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error with logging to the Application");
            alert.showAndWait();
        }
    }//end of onClickSignIn

    /**
     * onClickSignUp: onclick signUp screen is loaded
     */
    public void onClickSignUp() {
        //default user Student
        QuizMain.role = "Student";
        //got to signup screen
        application.gotoSignUpScreen();

    }//end of onClickSignUp

}//end of LoginController
