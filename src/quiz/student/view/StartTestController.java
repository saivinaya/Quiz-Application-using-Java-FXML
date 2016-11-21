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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import quiz.Question;
import quiz.QuizMain;
import quiz.QuizDBImplementation;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class StartTestController implements Initializable {

    private QuizMain application;
    public QuizDBImplementation fetchQuestions;
    public static ArrayList<Question> questionsForTest = new ArrayList<Question>();

    ObservableList<String> diffLevelList = FXCollections.observableArrayList("Easy", "Medium", "Hard", "Mixed");
    ObservableList<Integer> numOfQuestionsList = FXCollections.observableArrayList();
    public static int selectednumOfQuestions = 0;
    public static String selectedDifficulty = null;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diffLevel.setItems(diffLevelList);
        numOfQuestions.setDisable(true);
        System.out.println("Before fetch");
        
    }

    public void setApp(QuizMain application) {
        this.application = application;
        noOfEasy = application.getNoofQuestions("Easy");
        noOfMedium = application.getNoofQuestions("Medium");
        noOfHard = application.getNoofQuestions("Hard");
        noOfMixed = application.getNoofQuestions("Mixed");
    }

    @FXML
    private void goBackToStudentDashboard(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } else {
            application.gotoStudentDashboard();
        }
    }

    @FXML
    private void onQuestionSelected(MouseEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } else {
            numOfQuestions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue observableValue, Number number, Number number2) {
                    selectednumOfQuestions = (Integer) number2 + 3;
                    //System.out.println(selectednumOfQuestions);
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
                    for (int a = 3; a <= questionsInDatabase; a++) {
                        numOfQuestionsList.add(a);
                    }
                    numOfQuestions.setItems(numOfQuestionsList);
                    numOfQuestions.getSelectionModel();
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
            if (selectedDifficulty!=null && selectednumOfQuestions!=0)
            {   application.gotoInstrctions();
            }
            else if (selectedDifficulty!=null)
            {   // give a pop up saying "Difficulty Level needs to be selected"
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Difficulty Level needs to be selected.");
                alert.showAndWait();
            }
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
