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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import quiz.QuizDBImplementation;
import quiz.QuizMain;
import quiz.User;

/**
 * LoginController: This is the controller class for the login page.
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
    @FXML
    ImageView image;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image im = new Image(getClass().getResource("CMU_Hamerschlag_Hall.jpg")
                    .toString());
          image.setImage(im);
          image.setStyle("-fx-background-color: BLACK");
          image.setFitWidth(100);
         image.setPreserveRatio(true);
         image.setSmooth(true);
         image.setCache(true);
         image.setFitHeight(850);
image.setFitWidth(850);
         
    }

    /**
     * setApp: This method sets the application
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
    }

    /**
     * onClickSignIn: This methods gets the login name and password from the
     * user and checks if the entry is already present in database. IF present
     * then directs the user to corresponding page based on the role
     *
     * @param ae
     */
    @FXML
    public void onClickSignIn(ActionEvent ae) {
        QuizDBImplementation impl = new QuizDBImplementation();
        try {
            User user = impl.selectUser(loginName.getText(), password.getText());
            //load admin dashboard if role is that of admin
            if (user.getUniRole().equals("Admin")) {
                QuizMain.loginName = user.getUserName();
                QuizMain.role = "Admin";
                application.gotoAdminDashboard();
            }
            //load student dashboard if the role is that of student
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

    }//end of onClickSignIn

    /**
     * onClickSignUp: loads the signup page
     */
    public void onClickSignUp() {
        QuizMain.role = "Student";
        application.gotoSignUpScreen();
    }//end of onClickSignUp

}//end of LoginController
