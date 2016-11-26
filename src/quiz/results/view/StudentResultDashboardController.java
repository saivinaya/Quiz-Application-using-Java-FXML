/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.results.view;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import quiz.QuizMain;
import quiz.StudentResults;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class StudentResultDashboardController implements Initializable {

    private QuizMain application;
    public static ArrayList<StudentResults> testTaken = new ArrayList<>();
    @FXML
    private Hyperlink testResult1;
    @FXML
    private Hyperlink testResult2;
    @FXML
    private Hyperlink testResult3;
    @FXML
    private Hyperlink testResult4;
    @FXML
    private Hyperlink testResult5;
    @FXML
    private Hyperlink testResult6;
    @FXML
    private Hyperlink testResult7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setApp(QuizMain application) {
        this.application = application;
        Collections.sort(testTaken);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        testResult1.setText("Test 1: "+ df.format(testTaken.get(0)));
        testResult2.setText("Test 2: "+ df.format(testTaken.get(1)));
        testResult3.setText("Test 3: "+ df.format(testTaken.get(2)));
        testResult4.setText("Test 4: "+ df.format(testTaken.get(3)));
        testResult5.setText("Test 5: "+ df.format(testTaken.get(4)));
        testResult6.setText("Test 6: "+ df.format(testTaken.get(5)));
        testResult7.setText("Test 7: "+ df.format(testTaken.get(6)));
        Collections.sort(testTaken);
    }

    @FXML
    private void logout(ActionEvent event) {
        application.logoutAccount();
    }

    @FXML
    private void test1Selected(ActionEvent event) {
        int[] resultTest1 = new int[5];
        resultTest1 = testResultArray(testTaken.get(0));
        application.gotoQuizSummaryPage(resultTest1);
    }

    @FXML
    private void test2Selected(ActionEvent event) {
        int[] resultTest2 = new int[5];
        resultTest2 = testResultArray(testTaken.get(1));
        application.gotoQuizSummaryPage(resultTest2);
    }

    @FXML
    private void test3Selected(ActionEvent event) {
        int[] resultTest3 = new int[5];
        resultTest3 = testResultArray(testTaken.get(2));
        application.gotoQuizSummaryPage(resultTest3);
    }

    @FXML
    private void test4Selected(ActionEvent event) {
        int[] resultTest4 = new int[5];
        resultTest4 = testResultArray(testTaken.get(3));
        application.gotoQuizSummaryPage(resultTest4);
    }

    @FXML
    private void test5Selected(ActionEvent event) {
        int[] resultTest5 = new int[5];
        resultTest5 = testResultArray(testTaken.get(4));
        application.gotoQuizSummaryPage(resultTest5);
    }

    @FXML
    private void test6Selected(ActionEvent event) {
        int[] resultTest6 = new int[5];
        resultTest6 = testResultArray(testTaken.get(5));
        application.gotoQuizSummaryPage(resultTest6);
    }

    @FXML
    private void test7Selected(ActionEvent event) {
        int[] resultTest7 = new int[5];
        resultTest7 = testResultArray(testTaken.get(6));
        application.gotoQuizSummaryPage(resultTest7);
    }
    
    public int[] testResultArray(StudentResults temp)
    {   int[] resultTest = new int[5];
        resultTest[0]=temp.getTotalQuestions();
        resultTest[1]=temp.getLodEasyCorrect();
        resultTest[2]=temp.getLodMediumCorrect();
        resultTest[3]=temp.getLodHardCorrect();
        resultTest[4]=temp.getSkippedQuestions();
        return resultTest;
    }
}
