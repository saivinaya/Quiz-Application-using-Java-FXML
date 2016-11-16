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
    private QuizDBImplementation fetchQuestions;
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
        diffLevel.setValue("Select");
        diffLevel.setItems(diffLevelList);
        int questionsInDatabase = 20;
        for (int a = 3;a<=questionsInDatabase;a++)
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
            int arrayqQuestionType[] = new int[4];
            questionsForTest = fetchQuestions.selectQuestions(selectednumOfQuestions,selectedDifficulty);
            arrayqQuestionType = fetchQuestions.getQuestionTypes(questionsForTest);
            
            // iterate through the questions
            for (questionCounter=0; questionCounter<questionsForTest.size(); questionCounter++)
            {
                if (questionsForTest.get(questionCounter).getQuestionType() == "MC")
                {
                    showMCScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
                }
                else if (questionsForTest.get(questionCounter).getQuestionType() == "MA")
                {
                    showMAScreen((MultiChoiceQuestion) questionsForTest.get(questionCounter));
                }
                else if (questionsForTest.get(questionCounter).getQuestionType() == "TF")
                {
                    showTFScreen((TrueOrFalseQuestion) questionsForTest.get(questionCounter));
                }
                else if (questionsForTest.get(questionCounter).getQuestionType() == "FIB") 
                {
                    showFIBScreen((FillInTheBlanks) questionsForTest.get(questionCounter));
                }
            }
        }
    }

    private void showMCScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithOneAnswerController profile = (MultipleWithOneAnswerController) application.replaceSceneContent("student/view/MultipleWithOneAnswer.fxml");
            profile.setApp(application,qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMAScreen(MultiChoiceQuestion qust) {
        try {
            MultipleWithMoreAnswersController profile = (MultipleWithMoreAnswersController) application.replaceSceneContent("student/view/MultipleWithMoreAnswers.fxml");
            profile.setApp(application,qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showTFScreen(TrueOrFalseQuestion qust) {
        try {
            TrueOrFalseController profile = (TrueOrFalseController) application.replaceSceneContent("student/view/TrueOrFalse.fxml");
            profile.setApp(application,qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showFIBScreen(FillInTheBlanks qust) {
        try {
            FillInTheBlanksController profile = (FillInTheBlanksController) application.replaceSceneContent("student/view/FillInTheBlanks.fxml");
            profile.setApp(application,qust);
        } catch (Exception ex) {
            Logger.getLogger(QuizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
