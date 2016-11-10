package quiz;


import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import quiz.student.view.StartTestController;
import quiz.student.view.StudentDashboardController;

/**
 *
 * @author Group 
 */
public class QuizMain extends Application {
    
    public Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 700.0;
    private final double MINIMUM_WINDOW_HEIGHT = 700.0;
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("FXML Login Sample");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoStudentDashboard();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoStudentDashboard() {
        try {
            StudentDashboardController profile = (StudentDashboardController) replaceSceneContent("student/view/StudentDashboard.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoStartTest() {
        try {
            StartTestController profile = (StartTestController) replaceSceneContent("student/view/StartTest.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = QuizMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(QuizMain.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
        
    }
    
}
