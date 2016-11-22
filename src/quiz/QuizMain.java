package quiz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import quiz.student.view.StartTestController;
import quiz.student.view.StudentDashboardController;
import quiz.login.view.LoginController;
import quiz.admin.view.AdminDashboardController;
import quiz.admin.view.UploadFileController;
import quiz.login.view.SignUpController;
import quiz.question.view.FillInTheBlanksController;
import quiz.question.view.MultipleWithMoreAnswersController;
import quiz.question.view.MultipleWithOneAnswerController;
import quiz.question.view.TrueOrFalseController;
import quiz.results.view.QuizSummaryViewController;
import quiz.security.Authenticator;
import quiz.student.result.NoOfTestTakenController;
import quiz.student.result.StudentStatsController;
import quiz.student.view.InstructionsPageController;
import static quiz.student.view.StartTestController.questionCounter;
import static quiz.student.view.StartTestController.questionsForTest;
import static quiz.student.view.StartTestController.selectednumOfQuestions;
import quiz.student.view.SubmitPageController;

/**
 *
 * @author Group
 */
public class QuizMain extends Application {

    public Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 700.0;
    private final double MINIMUM_WINDOW_HEIGHT = 700.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Java Quiz");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            //uploadQuestions();
            gotoLogin();
            //gotoStudentDashboard();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            System.out.println("test");
            LoginController profile = (LoginController) replaceSceneContent("login/view/Login.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getNoofQuestions(String difficultyLevel) {
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        return qzImpl.questionCount(difficultyLevel);

    }
        public void gotoUploadFile() {
        try {
            System.out.println("upload file");
            UploadFileController profile = (UploadFileController) replaceSceneContent("admin/view/UploadFile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void goTOStudentStats(){
        try {
            System.out.println("Inside goto");
            StudentStatsController profile = (StudentStatsController) replaceSceneContent("student/result/StudentStats.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public void noOfTestsTaken(){
        try {
            System.out.println("Kuhu");
            NoOfTestTakenController profile = (NoOfTestTakenController) replaceSceneContent("student/result/NoOfTestTaken.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userLogging(String loginName, String password) {
        if (Authenticator.validate(loginName, password)) {
            gotoAdminDashboard();
            return true;
        } else {
            return false;
        }
    }

    public boolean userLogging(String loginName, String userName, String password1, String password2, String role) {
        if (loginName == null | userName == null | password1 == null | password2 == null | role == null) {
            return false;
        } else {
            return true;
        }
    }

    public void gotoAdminDashboard() {
        try {
            System.out.println("in adminDashboard");
            AdminDashboardController profile = (AdminDashboardController) replaceSceneContent("admin/view/AdminDashboard.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSignUpScreen() {
        try {
            System.out.println("in SignUpScreen");
            SignUpController profile = (SignUpController) replaceSceneContent("login/view/SignUp.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoStudentDashboard() {
        try {
            System.out.println("in main student dashboard");
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

    public void gotoInstrctions() {
        try {
            InstructionsPageController profile = (InstructionsPageController) replaceSceneContent("student/view/InstructionsPage.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSubmitPage() {
        try {
            SubmitPageController profile = (SubmitPageController) replaceSceneContent("student/view/SubmitPage.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gotoQuizSummaryPage(int[] resultArray) {
        try {
            QuizSummaryViewController profile = (QuizSummaryViewController) replaceSceneContent("results/view/QuizSummaryView.fxml");
            profile.setApp(this,resultArray);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void gotoTestPage() {
//        try {
//            StartTestController profile = (StartTestController) replaceSceneContent("student/view/StartTest.fxml");
//            profile.setApp(this);
//        } catch (Exception ex) {
//            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public ArrayList<Question> getQuestions(int numOfQuestions, String difficultyLevel) {
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        return qzImpl.selectQuestions(numOfQuestions, difficultyLevel);

    }

    public void uploadQuestions(String fileName) {
        System.out.println("Inside upload q to db");
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        qzImpl.addQuestions(fileName);
        //qzImpl.addQuestions("test-sample.csv");
    }

    public void showMCScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithOneAnswerController profile = (MultipleWithOneAnswerController) replaceSceneContent("question/view/MultipleWithOneAnswer.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showMAScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithMoreAnswersController profile = (MultipleWithMoreAnswersController) replaceSceneContent("question/view/MultipleWithMoreAnswers.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showTFScreen(TrueOrFalseQuestion qust) {
        try {
            TrueOrFalseController profile = (TrueOrFalseController) replaceSceneContent("question/view/TrueOrFalse.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showFIBScreen(FillInTheBlanks qust) {
        try {
            FillInTheBlanksController profile = (FillInTheBlanksController) replaceSceneContent("question/view/FillInTheBlanks.fxml");
            profile.setApp(this, qust);
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

    public void addUser(String loginName, String userName, String password, String uniRole) {
        System.out.println("in addUser");
        User usr = new User(loginName, userName, password, uniRole);
        System.out.println("AddUser");
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        qzImpl.addUser(usr);
    }

    public void gotoNextQuestion() {
        questionCounter = questionCounter + 1;
        if (questionCounter < selectednumOfQuestions) {
            if (questionsForTest.get(questionCounter).getQuestionType().equals("MC")) {
                showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
            } else if (questionsForTest.get(questionCounter).getQuestionType().equals("MA")) {
                showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
            } else if (questionsForTest.get(questionCounter).getQuestionType().equals("TF")) {
                showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
            } else if (questionsForTest.get(questionCounter).getQuestionType().equals("FIB")) {
                showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
            }
        } else if (questionCounter == selectednumOfQuestions) {
            gotoSubmitPage();
        }
    }
}
