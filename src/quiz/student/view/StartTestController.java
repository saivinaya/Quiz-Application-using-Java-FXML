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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    
    ObservableList<String> diffLevelList = FXCollections.observableArrayList("Easy","Medium","Hard","Mixed");
    ObservableList<Integer> numOfQuestionsList = FXCollections.observableArrayList();
    int selectednumOfQuestions = 0;
    String selectedDifficulty = null; 
    
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
        diffLevel.setValue("Select");
        diffLevel.setItems(diffLevelList);
        int questionsInDatabase = 20;
        for (int a = 1;a<=questionsInDatabase;a++)
        {
            numOfQuestionsList.add(a);
        }
        numOfQuestions.setItems(numOfQuestionsList);
    }    
    
    public void setApp(QuizMain application){
        this.application = application;
    }

    @FXML
    private void goBackToStudentDashboard(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } 
        else 
        {
            application.gotoStudentDashboard();
        }
    }
    
    @FXML
    private void onQuestionSelected(MouseEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } 
        else 
        {
            numOfQuestions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observableValue, Number number, Number number2) {
              selectednumOfQuestions = (Integer) number2 + 1;
              System.out.println(selectednumOfQuestions);
            }
          });
        }
    }
    
    @FXML
    private void onDiffSelected(MouseEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } 
        else 
        {
            diffLevel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observableValue, Number number, Number number2) {
               int selectedDiffIndex = (Integer) number2;
              selectedDifficulty = diffLevelList.get(selectedDiffIndex);
              System.out.println(selectedDifficulty);
            }
          });
        }
    }
    
    @FXML
    private void onBeginTest(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello");
        } 
        else 
        {
            ArrayList<Question> questionsForTest = new ArrayList<Question>();
            //QuizDBImplementation.selectQuestions(selectednumOfQuestions,selectedDifficulty);
        }
    }

}