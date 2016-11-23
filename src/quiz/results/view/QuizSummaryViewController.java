/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.results.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import quiz.QuizMain;

/**
 * This class is the controller class for the QuizSummaryView fxml page; it has
 * initialize() method, setApp() is used to set the values to the labels and
 * also to build the pie chart to represent the data
 *
 * @author VinayaSaiD
 */
public class QuizSummaryViewController implements Initializable {

    private QuizMain application;

    @FXML
    private Label totalQuestions;
    @FXML
    private Label easyCorrect;
    @FXML
    private Label mediumCorrect;
    @FXML
    private Label hardCorrect;
    @FXML
    private Label totalScore;
    @FXML
    private Label testResult;
    @FXML
    private Label skipped;
    @FXML
    private Label wrongAnswer;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setApp(QuizMain application, int[] rltArray) {
        this.application = application;
        // to set the values from the array to labels to display the results on the screen
        totalQuestions.setText(Integer.toString(rltArray[0]));
        easyCorrect.setText(Integer.toString(rltArray[1]));
        mediumCorrect.setText(Integer.toString(rltArray[2]));
        hardCorrect.setText(Integer.toString(rltArray[3]));
        skipped.setText(Integer.toString(rltArray[4]));
        // coputes the score based on correct answers 
        double score = rltArray[1] + rltArray[2] + rltArray[3];
        double wrong = rltArray[0] - (score + rltArray[4]);
        totalScore.setText(Integer.toString((int) score));
        wrongAnswer.setText(Integer.toString((int) wrong));
        // passed if the student gets 40% marks
        if ((double) (score / rltArray[0]) > 0.4) {
            testResult.setText("Passed!");
        } else if ((double) (score / rltArray[0]) < 0.4) {
            testResult.setText("Failed!");
        } else {
            testResult.setText("Cannot Compute!");
        }
        // this is used to depict the previous data in a pie chart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Easy Questions Answered Correctly", rltArray[1]),
                        new PieChart.Data("Medium Questions Answered Correctly", rltArray[2]),
                        new PieChart.Data("Hard Questions Answered Correctly", rltArray[3]),
                        new PieChart.Data("Questions Skipped", rltArray[4]),
                        new PieChart.Data("Wrong Answers", (int) wrong));
        pieChart.setData(pieChartData);
    }

    @FXML
    private void goBackDashboard(ActionEvent event) {
        application.gotoStudentDashboard();
    }
}
