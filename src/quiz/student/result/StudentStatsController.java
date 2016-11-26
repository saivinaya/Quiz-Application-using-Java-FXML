/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.result;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import quiz.Question;
import quiz.QuizMain;
import quiz.QuizDBImplementation;
import quiz.StudentResults;
import static quiz.student.view.StartTestController.selectedDifficulty;

/**
 * FXML Controller class
 *
 * @author Kuhu
 */
public class StudentStatsController implements Initializable {

    private QuizMain application;
    public QuizDBImplementation fetchQuestions;
    public static ArrayList<Question> questionsForTest = new ArrayList<Question>();
    private ArrayList<StudentResults> arr = new ArrayList<>();
    private ArrayList<StudentResults> monthly = new ArrayList<>();
    private ArrayList<StudentResults> quarterly = new ArrayList<>();
    private ArrayList<StudentResults> yearly = new ArrayList<>();
    static int[] noOfTestsTaken = new int[3];
    static double[] avgStudentScores = new double[3];
    static double[] avgEasyCorrect = new double[3];
    static double[] avgMedCorrect = new double[3];
    static double[] avgHardCorrect = new double[3];

    ObservableList<String> reportType = FXCollections.observableArrayList("No Of Tests Taken", "Average Student Scores", "Pass/Fail Stats", "Scores by LOD");
    ObservableList<String> periodList = FXCollections.observableArrayList("Last Month", "Last Quarter", "Last Year");
    static String selectedReport = null;
    static String selectedPeriod = null;
    static int[] scoresByLOD = new int[3];
    static int[] passing = new int[3];
    static int[] failing = new int[3];

    Label errorMessage;

    @FXML
    private ChoiceBox report;

    @FXML
    private ChoiceBox period;
    @FXML
    private Pane Statistics;

    @FXML
    private Button viewStats;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("Before fetch");
        report.setItems(reportType);
        period.setItems(periodList);
        period.setDisable(true);
        viewStats.setDisable(true);

    }

    public void setApp(QuizMain application) {
        this.application = application;

    }

    @FXML
    public void reportSelect(MouseEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            errorMessage.setText("Hello");
        } else {

            report.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                    period.getSelectionModel().clearSelection();
                    selectedReport = report.getSelectionModel().getSelectedItem().toString();

                    period.setDisable(false);
                }
            });
        }
    }

    @FXML
    public void onPeriodSelected(MouseEvent event) {
        period.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                selectedPeriod = period.getSelectionModel().getSelectedItem().toString();
                viewStats.setDisable(false);
                
            }
        });

    }

    @FXML
    public void viewStats(ActionEvent event) {
        QuizDBImplementation quiz = new QuizDBImplementation();
        
        this.arr = quiz.getStudentResults();
        System.out.println(arr.size());
        System.out.println(arr.toString());
        setArrayValues();

        application.noOfTestsTaken();

    }

    @FXML
    public void goBack(ActionEvent event) {
        application.gotoAdminDashboard();
    }

    public int[] noOfTestsTaken() {
        getNoOfTestsTaken()[0] = monthly.size();
        getNoOfTestsTaken()[1] = quarterly.size();
        getNoOfTestsTaken()[2] = yearly.size();
        return noOfTestsTaken;
    }

    public double[] avgStudentScore() {
        for (int i = 0; i < monthly.size(); i++) {
            avgStudentScores[0] += monthly.get(i).getTotalCorrect();

        }
        avgStudentScores[0] = avgStudentScores[0] / monthly.size();
        for (int i = 0; i < quarterly.size(); i++) {
            avgStudentScores[1] = quarterly.get(i).getTotalCorrect();

        }
        avgStudentScores[1] = avgStudentScores[1] / quarterly.size();
        for (int i = 0; i < yearly.size(); i++) {
            avgStudentScores[2] = yearly.get(i).getTotalCorrect();

        }
        avgStudentScores[2] = avgStudentScores[2] / yearly.size();
        return avgStudentScores;
    }

    public void scoresByLOD() {
        
        for (int i = 0; i < monthly.size(); i++) {

            avgEasyCorrect[0] += monthly.get(i).getLodEasyCorrect();
            avgMedCorrect[0] += monthly.get(i).getLodMediumCorrect();
            avgHardCorrect[0] += monthly.get(i).getLodHardCorrect();
        }
        
        avgEasyCorrect[0] = avgEasyCorrect[0] / monthly.size();
        avgMedCorrect[0] = avgMedCorrect[0] / monthly.size();
        avgHardCorrect[0] = avgMedCorrect[0] / monthly.size();
        
        for (int i = 0; i < quarterly.size(); i++) {

            avgEasyCorrect[1] += quarterly.get(i).getLodEasyCorrect();
            avgMedCorrect[1] += quarterly.get(i).getLodMediumCorrect();
            avgHardCorrect[1] += quarterly.get(i).getLodHardCorrect();
        }
        
        avgEasyCorrect[1] = avgEasyCorrect[1] / quarterly.size();
        avgMedCorrect[1] = avgMedCorrect[1] / quarterly.size();
        avgHardCorrect[1] = avgMedCorrect[1] / quarterly.size();
        
        for (int i = 0; i < yearly.size(); i++) {

            avgEasyCorrect[2] += yearly.get(i).getLodEasyCorrect();
            avgMedCorrect[2] += yearly.get(i).getLodMediumCorrect();
            avgHardCorrect[2] += yearly.get(i).getLodHardCorrect();
        }
        
        avgEasyCorrect[2] = avgEasyCorrect[2] / yearly.size();
        avgMedCorrect[2] = avgMedCorrect[2] / yearly.size();
        avgHardCorrect[2] = avgMedCorrect[2] / yearly.size();
        

    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(cal.getWeekYear());
        return cal;
    }

    public void setArrayValues() {
        for (int i = 0; i < arr.size(); i++) {
            Date d = arr.get(i).getTestDate();

            Calendar c = toCalendar(d);
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int testMonth = Calendar.getInstance().get(c.MONTH);
            System.out.println(currentMonth);
            System.out.println(testMonth);

            if (testMonth == Calendar.getInstance().get(c.MONTH)) {
                monthly.add(arr.get(i));
                quarterly.add(arr.get(i));
                yearly.add(arr.get(i));

            } else if (testMonth < Calendar.getInstance().get(c.MONTH) - 2 && testMonth > Calendar.getInstance().get(c.MONTH)) {
                quarterly.add(arr.get(i));
                yearly.add(arr.get(i));
            } else if (testMonth < Calendar.getInstance().get(c.MONTH) - 12 && testMonth > Calendar.getInstance().get(c.MONTH) - 2) {
                yearly.add(arr.get(i));

            }

        }
        noOfTestsTaken();
        avgStudentScore();
        scoresByLOD();
        studentPassOrFail();
    }

    public void studentPassOrFail(){
        int monthPass = 0, quarterPass = 0, yearPass = 0;
        for(int i = 0; i < monthly.size(); i++){
            if((monthly.get(i).getTotalCorrect() * 10.0)/(monthly.get(i).getTotalQuestions() * 10.0) > 0.5){
                monthPass++;
            }
        }
        passing[0] = monthPass;
        failing[0] = monthly.size() - passing[0];
        
        for(int i = 0; i < quarterly.size(); i++){
            if((quarterly.get(i).getTotalCorrect() * 10.0)/(quarterly.get(i).getTotalQuestions() * 10.0) > 0.5){
                quarterPass++;
            }
        }
        passing[1] = quarterPass;
        failing[1] = quarterly.size() - passing[1];
        
        for(int i = 0; i < yearly.size(); i++){
            if((yearly.get(i).getTotalCorrect() * 10.0)/(yearly.get(i).getTotalQuestions() * 10.0) > 0.5){
                yearPass++;
            }
        }
        passing[2] = yearPass;
        failing[2] = yearly.size() - passing[2];
    }
    
    
    
    /**
     * @return the arr
     */
    public ArrayList<StudentResults> getArr() {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(ArrayList<StudentResults> arr) {
        this.arr = arr;
    }

    /**
     * @return the monthly
     */
    public ArrayList<StudentResults> getMonthly() {
        return monthly;
    }

    /**
     * @param monthly the monthly to set
     */
    public void setMonthly(ArrayList<StudentResults> monthly) {
        this.monthly = monthly;
    }

    /**
     * @return the quarterly
     */
    public ArrayList<StudentResults> getQuarterly() {
        return quarterly;
    }

    /**
     * @param quarterly the quarterly to set
     */
    public void setQuarterly(ArrayList<StudentResults> quarterly) {
        this.quarterly = quarterly;
    }

    /**
     * @return the yearly
     */
    public ArrayList<StudentResults> getYearly() {
        return yearly;
    }

    /**
     * @param yearly the yearly to set
     */
    public void setYearly(ArrayList<StudentResults> yearly) {
        this.yearly = yearly;
    }

    /**
     * @return the noOfTestsTaken
     */
    public int[] getNoOfTestsTaken() {
        return noOfTestsTaken;
    }

    /**
     * @param noOfTestsTaken the noOfTestsTaken to set
     */
    public void setNoOfTestsTaken(int[] noOfTestsTaken) {
        this.noOfTestsTaken = noOfTestsTaken;
    }

    /**
     * @return the avgStudentScores
     */
    public double[] getAvgStudentScores() {
        return avgStudentScores;
    }

    /**
     * @param avgStudentScores the avgStudentScores to set
     */
    public void setAvgStudentScores(double[] avgStudentScores) {
        this.avgStudentScores = avgStudentScores;
    }

}
