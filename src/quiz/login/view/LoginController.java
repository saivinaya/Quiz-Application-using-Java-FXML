
package quiz.login.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import quiz.QuizDBImplementation;
import quiz.QuizMain;
import quiz.User;

/**
 * FXML Controller class
 *
 * @author Hari
 */
public class LoginController implements Initializable {

    private QuizMain application;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    /**
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
    }

    /**
     *
     * @param ae
     */
    @FXML
    public void onClickSignIn(ActionEvent ae) {
        QuizDBImplementation impl = new QuizDBImplementation();
     try {
            User user = impl.selectUser(loginName.getText(), password.getText());

            if (user.getUniRole().equals("Admin")) {
                QuizMain.loginName = user.getUserName();
                QuizMain.role = "Admin";
                application.gotoAdminDashboard();
            } 
             if (user.getUniRole().equals("Student")) {
                QuizMain.loginName = loginName.getText();
                QuizMain.role = "Student";
                application.gotoStudentDashboard();
            }
        } catch (NullPointerException exp) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username/password");
            alert.showAndWait();
        }

    }

    private void setGlobalEventHandler(Node root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                signin.fire();
                ev.consume();
            }
        });
    }

    /**
     *
     */
    public void onClickSignUp() {
          QuizMain.role = "Student";
        application.gotoSignUpScreen();

    }

}
