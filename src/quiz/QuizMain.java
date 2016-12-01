//QuizMain.java
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
import quiz.results.view.StudentResultDashboardController;
import quiz.student.result.NoData;
import quiz.student.result.NoOfTestTakenController;
import quiz.student.result.StudentStatsController;
import quiz.student.view.InstructionsPageController;
import static quiz.student.view.StartTestController.questionCounter;
import static quiz.student.view.StartTestController.*;
import static quiz.student.view.StartTestController.selectednumOfQuestions;
import quiz.student.view.SubmitPageController;

/**
 *
 * @author Group
 */
public class QuizMain extends Application {

    /**
     * Initial frame parameters
     */
    public Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 1000.0;
    private final double MINIMUM_WINDOW_HEIGHT = 850.0;

    /**
     * Login Name which would be used across the application
     */
    public static String loginName;

    /**
     * Role of the user, used across the application
     */
    public static String role;

    /**
     * Message conveyed to the user upon file load
     */
    public static String fileLoadMessage;

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
            stage.setTitle("Quiz");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.centerOnScreen();
            //disabling the option to maximize screen
            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                stage.setMaximized(false);
        });
            //call the login page
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gotoLogin: This is the initializes the loginController and the login page
     * is displayed to the user using Login.fxml
     */
    public void gotoLogin() {
        try {
            LoginController profile = (LoginController) replaceSceneContent("login/view/Login.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is for getting the number of questions for each difficulty
     * level
     *
     * @param difficultyLevel
     * @return
     */
    public int getNoofQuestions(String difficultyLevel) {
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        return qzImpl.questionCount(difficultyLevel);

    }

    /**
     * gotoUploadFile: This method creates the instance of UploadFileController
     * and loads the UploadFile.fxml
     */
    public void gotoUploadFile() {
        try {
            UploadFileController profile = (UploadFileController) replaceSceneContent("admin/view/UploadFile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigates the user to StudentStats fxml page
     */
    public void goTOStudentStats() {
        try {
            StudentStatsController profile = (StudentStatsController) replaceSceneContent("student/result/StudentStats.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigates the user to NoOfTestTaken fxml page
     */
    public void noOfTestsTaken() {
        try {

            NoOfTestTakenController profile = (NoOfTestTakenController) replaceSceneContent("student/result/NoOfTestTaken.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Navigates the user to NoData fxml page
     */
    public void noData() {
        try {

            NoData profile = (NoData) replaceSceneContent("student/result/NoData.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gotoAdminDashboard: This method creates instance of
     * AdminDashboardController and loads the AdminDashboard.fxml page
     */
    public void gotoAdminDashboard() {
        try {
            AdminDashboardController profile = (AdminDashboardController) replaceSceneContent("admin/view/AdminDashboard.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gotoSignUpScreen: This method loads the SignUp.fxml
     */
    public void gotoSignUpScreen() {
        try {
            SignUpController profile = (SignUpController) replaceSceneContent("login/view/SignUp.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of StudentDashboardController and loads
     * the StudentDashboard.fxml
     */
    public void gotoStudentDashboard() {
        try {
            System.out.println("in main student dashboard");
            StudentDashboardController profile = (StudentDashboardController) replaceSceneContent("student/view/StudentDashboard.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of StartTestController and loads the
     * StartTest.fxml
     */
    public void gotoStartTest() {
        try {
            StartTestController profile = (StartTestController) replaceSceneContent("student/view/StartTest.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of InstructionsPageController and loads
     * the InstructionsPage.fxml
     */
    public void gotoInstrctions() {
        try {
            InstructionsPageController profile = (InstructionsPageController) replaceSceneContent("student/view/InstructionsPage.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of SubmitPageController and loads the
     * SubmitPage.fxml
     */
    public void gotoSubmitPage() {
        try {
            SubmitPageController profile = (SubmitPageController) replaceSceneContent("student/view/SubmitPage.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of QuizSummaryViewController and loads
     * the QuizSummaryView.fxml
     *
     * @param resultArray is the result set for the test
     * @param flag is to identify where the request is from Student Result
     * dashboard or after the test is taken
     */
    public void gotoQuizSummaryPage(int[] resultArray, boolean flag) {
        try {
            QuizSummaryViewController profile = (QuizSummaryViewController) replaceSceneContent("results/view/QuizSummaryView.fxml");
            profile.setApp(this, resultArray, flag);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of StudentResultDashboardController and
     * loads the StudentResultDashboard.fxml
     */
    public void gotoStudentResultDashboard() {
        try {
            StudentResultDashboardController profile = (StudentResultDashboardController) replaceSceneContent("results/view/StudentResultDashboard.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates is used to logout of the account and clear all the
     * static variables
     */
    public void logoutAccount() {
        try {
            questionsForTest.clear();
            selectednumOfQuestions = 0;
            selectedDifficulty = null;
            questionCounter = 0;
            numSkip = 0;
            maxSkip = 0;
            loginName = null;
            role = null;
            LoginController profile = (LoginController) replaceSceneContent("login/view/Login.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is used to get the set of questions after selecting the
     * number of questions and difficulty level
     *
     * @param numOfQuestions
     * @param difficultyLevel
     * @return
     */
    public ArrayList<Question> getQuestions(int numOfQuestions, String difficultyLevel) {
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        return qzImpl.selectQuestions(numOfQuestions, difficultyLevel);
    }

    /**
     * This method is to evaluate the answers given and the input is the
     * ArrayList of questions
     *
     * @param questions is an ArrayList of questions along with user inputs
     * @return
     */
    public int[] evaluateTest(ArrayList<Question> questions) {
        QuizHelper qzHelp = new QuizHelper();
        return qzHelp.evaluateQuizResult(questions);
    }

    /**
     *
     * @param fileName
     */
    public void uploadQuestions(String fileName) {
        System.out.println("Inside upload q to db");
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        qzImpl.addQuestions(fileName);
        //qzImpl.addQuestions("test-sample.csv");
    }

    /**
     * This method creates the instance of MultipleWithOneAnswerController and
     * loads the MultipleWithOneAnswer.fxml
     *
     * @param qust
     */
    public void showMCScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithOneAnswerController profile = (MultipleWithOneAnswerController) replaceSceneContent("question/view/MultipleWithOneAnswer.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of MultipleWithMoreAnswersController and
     * loads the MultipleWithMoreAnswers.fxml
     *
     * @param qust
     */
    public void showMAScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithMoreAnswersController profile = (MultipleWithMoreAnswersController) replaceSceneContent("question/view/MultipleWithMoreAnswers.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of TrueOrFalseController and loads the
     * TrueOrFalse.fxml
     *
     * @param qust
     */
    public void showTFScreen(TrueOrFalseQuestion qust) {
        try {
            TrueOrFalseController profile = (TrueOrFalseController) replaceSceneContent("question/view/TrueOrFalse.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates the instance of FillInTheBlanksController and loads
     * the FillInTheBlanks.fxml
     *
     * @param qust
     */
    public void showFIBScreen(FillInTheBlanks qust) {
        try {
            FillInTheBlanksController profile = (FillInTheBlanksController) replaceSceneContent("question/view/FillInTheBlanks.fxml");
            profile.setApp(this, qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param fxml
     * @return
     * @throws Exception
     */
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
        Scene scene = new Scene(page, MINIMUM_WINDOW_WIDTH, MINIMUM_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        return (Initializable) loader.getController();
    }

    /**
     *
     * @param loginName
     * @param userName
     * @param password
     * @param uniRole
     */
    public void addUser(String loginName, String userName, String password, String uniRole) {
        System.out.println("in addUser");
        User usr = new User(loginName, userName, password, uniRole);
        System.out.println("AddUser");
        QuizDBImplementation qzImpl = new QuizDBImplementation();
        qzImpl.addUser(usr);
    }

    /**
     * This method is used to go to the next question and load the appropriate
     * page according to the question type
     */
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
}//end of QuizMain
