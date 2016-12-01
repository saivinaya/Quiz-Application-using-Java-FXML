/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.result;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import quiz.QuizMain;

/**
 * This is controller class for NoData.fxml file
 *
 * @author Kuhu
 */
public class NoData implements Initializable {

    private QuizMain application;
    StudentStatsController stud = new StudentStatsController();
    /**
     * Initializes the controller class.
     */
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Pane Statistics;
    @FXML
    private Pane Display;
    @FXML
    private Button exportAsPDF;
    @FXML
    private Button back;
    @FXML
    private Label statsLabel;
    @FXML
    private Label statsMainLabel;

    /**
     * This method initializes the class
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * This method goes back to previous page
     */
    @FXML
    public void goBack() {
        application.goTOStudentStats();
    }

    /**
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;

    }

    /**
     * User goes to login page
     *
     * @param event
     */
    @FXML
    private void logout(ActionEvent event) {
        // call the logoutAccount() to logout of the application
        application.logoutAccount();
    }

}
