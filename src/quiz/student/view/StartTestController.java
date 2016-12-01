/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import quiz.Question;
import quiz.QuizMain;
import quiz.QuizDBImplementation;

/**
 * This class is the controller class for StartTest fxml page; it has 
 * initialize(), setApp() as base methods; goBackToStudentDashboard() to go to the student dashboard screen
 * onQuestionSelected() to save the input to selectednumOfQuestions
 * onDiffSelected() to save the input to selectedDifficulty and get the number of that type questions and display it
 * and onBeginTest() to go to the instructions page
 * @author VinayaSaiD
 */
public class StartTestController implements Initializable {

    private QuizMain application;

    /**
     *
     */
    public QuizDBImplementation fetchQuestions;

    /**
     *
     */
    public static ArrayList<Question> questionsForTest = new ArrayList<Question>();

    /**
     *
     */
    public static int maxSkip = 0;

    /**
     *
     */
    public static int numSkip = 0;

    ObservableList<String> diffLevelList = FXCollections.observableArrayList();
    ObservableList<Integer> numOfQuestionsList = FXCollections.observableArrayList();

    /**
     *
     */
    public static int selectednumOfQuestions = 0;

    /**
     *
     */
    public static String selectedDifficulty = null;

    /**
     *
     */
    public static int questionCounter = 0;
    int noOfEasy;
    int noOfMedium;
    int noOfHard;
    int noOfMixed;
    
    Label errorMessage;

    @FXML
    private ChoiceBox diffLevel;

    @FXML
    private ChoiceBox numOfQuestions;
    @FXML
    private Pane allPane;
    @FXML
    private HBox buttonPane;
    @FXML
    private Pane optionsPane;
    @FXML
    private Label noQuestions;
    @FXML
    private Button beginTest;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set the list for difficulty level
        diffLevel.setItems(diffLevelList);
        // only once user selects the difficulty level, the no. of questions will be enabled
        numOfQuestions.setDisable(true);
    }

    /**
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
        // getting how many questions are avaiable in each difficulty level
        noOfEasy = application.getNoofQuestions("Easy");
        noOfMedium = application.getNoofQuestions("Medium");
        noOfHard = application.getNoofQuestions("Hard");
        noOfMixed = application.getNoofQuestions("Mixed");
        if (noOfEasy > 3)
        {   diffLevelList.add("Easy");
        }
        if (noOfMedium > 3)
        {   diffLevelList.add("Medium");
        }
        if (noOfHard > 3)
        {   diffLevelList.add("Hard");
        }
        if (noOfMixed > 3)
        {   diffLevelList.add("Mixed");
        }
        if (noOfEasy < 3 && noOfMedium < 3 && noOfHard < 3 && noOfMixed < 3)
        {   beginTest.setVisible(false);
            optionsPane.setVisible(false);
            noQuestions.setVisible(true);
        }
    }

    @FXML
    private void goBackToStudentDashboard(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            // need to go to student dashboard
            application.gotoStudentDashboard();
        }
    }

    @FXML
    private void onQuestionSelected(MouseEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            numOfQuestions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue observableValue, Number number, Number number2) {
                    // need to set the value of selectednumOfQuestions  from user input
                    // number2 is the index and as my list starts from 3, need to add 3 to get the actual number of questions
                    selectednumOfQuestions = (Integer) number2 + 3;
                }
            });
        }
    }

    @FXML
    private void onDiffSelected(MouseEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            diffLevel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue observableValue, Number number, Number number2) {
                    numOfQuestions.getSelectionModel().clearSelection();
                    int selectedDiffIndex = (Integer) number2;
                    selectedDifficulty = diffLevelList.get(selectedDiffIndex);
                    // based on the difficulty level, need to give the number of questions
                    int questionsInDatabase = 3;
                    if (selectedDifficulty.equalsIgnoreCase("Easy"))
                    {   questionsInDatabase = noOfEasy;
                    }
                    else if (selectedDifficulty.equalsIgnoreCase("Medium"))
                    {   questionsInDatabase = noOfMedium;
                    }
                    else if (selectedDifficulty.equalsIgnoreCase("Hard"))
                    {   questionsInDatabase = noOfHard;
                    }
                    else if (selectedDifficulty.equalsIgnoreCase("Mixed"))
                    {   questionsInDatabase = noOfMixed;
                    }
                    selectednumOfQuestions = 0;
                    numOfQuestionsList.clear();
                    //minimum of 3 questions need to be answered to start a test
                    for (int a = 3; a <= questionsInDatabase; a++) {
                        numOfQuestionsList.add(a);
                    }
                    numOfQuestions.setItems(numOfQuestionsList);
                    numOfQuestions.getSelectionModel();
                    // activate the number of questions menu
                    numOfQuestions.setDisable(false);
                }
            });
        }
    }

    @FXML
    private void onBeginTest(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {
            // if the difficulty and no. of questions are choosen then only proceed
            if (selectedDifficulty!=null && selectednumOfQuestions!=0)
            {   maxSkip = (int) Math.ceil(selectednumOfQuestions * 0.2);
                application.gotoInstrctions();
            }
            // if the difficulty is not choosen then give a warning to choose it
            else if (selectedDifficulty!=null)
            {   // give a pop up saying "Difficulty Level needs to be selected"
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Difficulty Level needs to be selected.");
                alert.showAndWait();
            }
            // if the no. of questions are not choosen then give a warning to choose it
            else if (selectednumOfQuestions!=0)
            {   // give a pop up saying "Please select No.of Questions"
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please select No.of Questions");
                alert.showAndWait();
            }
            else
            {   // give a pop up saying "Both fields need to be selected before proceeding."
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Both fields need to be selected before proceeding.");
                alert.showAndWait();
            }
        }
    }
}
