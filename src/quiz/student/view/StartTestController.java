/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import quiz.FillInTheBlanks;
import quiz.MultiChoiceQuestion;
import quiz.Question;
import quiz.QuizMain;
import quiz.QuizDBImplementation;
import quiz.TrueOrFalseQuestion;
import quiz.question.view.FillInTheBlanksController;
import quiz.question.view.MultipleWithMoreAnswersController;
import quiz.question.view.MultipleWithOneAnswerController;
import quiz.question.view.TrueOrFalseController;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class StartTestController implements Initializable {
    private QuizMain application;
    public QuizDBImplementation fetchQuestions;
    public static ArrayList<Question> questionsForTest = new ArrayList<Question>();
    
    ObservableList<String> diffLevelList = FXCollections.observableArrayList("Easy","Medium","Hard","Mixed");
    ObservableList<Integer> numOfQuestionsList = FXCollections.observableArrayList();
    public static int selectednumOfQuestions = 0;
    public String selectedDifficulty = null; 
    public static int questionCounter = 0;
    
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
        System.out.println("In the test");
        diffLevel.setValue("Select");
        diffLevel.setItems(diffLevelList);
        int questionsInDatabase = 20;
        for (int a = 3;a<=questionsInDatabase;a++)
        {
            numOfQuestionsList.add(a);
        }
        numOfQuestions.setItems(numOfQuestionsList);
    }    
    
    public void setApp(QuizMain application) {
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
              selectednumOfQuestions = (Integer) number2 + 3;
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
            int arrayqQuestionType[] = new int[4];
            System.out.println("before invoking sql1" +selectednumOfQuestions+"difficulty"+selectedDifficulty);
            questionsForTest=application.getQuestions(selectednumOfQuestions,selectedDifficulty);
         //   questionsForTest = fetchQuestions.selectQuestions(selectednumOfQuestions,selectedDifficulty);
          //  arrayqQuestionType = fetchQuestions.getQuestionTypes(questionsForTest);
            System.out.println("Questions for test"+questionsForTest);
            // iterate through the questions
//            for (questionCounter=0; questionCounter<questionsForTest.size(); questionCounter++)
                
                for (Question question:questionsForTest)
            {
                System.out.println("in for loop");
                if (question.getQuestionType().equals("MC"))
                {
                    System.out.println("1"+question.getQuestiondesc());
                    application.showMCScreen((MultiChoiceQuestion) question);
                }
                else if (question.getQuestionType().equals("MA"))
                {
                       System.out.println("2"+question.getQuestiondesc());
                    application.showMAScreen((MultiChoiceQuestion) question);
                }
                else if (question.getQuestionType().equals("TF"))
                {
                       System.out.println("3"+question.getQuestiondesc());
                    application.showTFScreen((TrueOrFalseQuestion) question);
                }
                else if (question.getQuestionType().equals("FIB")) 
                {
                       System.out.println("4"+question.getQuestiondesc());
                    application.showFIBScreen((FillInTheBlanks) question);
                }
            }
        }
    }



}
