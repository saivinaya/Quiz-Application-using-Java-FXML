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
import java.util.GregorianCalendar;
import java.util.HashMap;
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

/**
 *
 * This is controller class for StudentStats.fxml file
 *
 * @author Kuhu
 */
public class StudentStatsController implements Initializable {

    private QuizMain application;
    private QuizDBImplementation fetchQuestions;
    private static ArrayList<Question> questionsForTest = new ArrayList<Question>();
    private ArrayList<StudentResults> arr = new ArrayList<>();
    private ArrayList<StudentResults> monthly = new ArrayList<>();
    private ArrayList<StudentResults> quarterly = new ArrayList<>();
    private ArrayList<StudentResults> yearly = new ArrayList<>();
    private static int[] noOfTestsTaken = new int[3];
    private static double[] avgStudentScores = new double[3];
    private static double[] avgEasyCorrect = new double[3];
    private static double[] avgMedCorrect = new double[3];
    private static double[] avgHardCorrect = new double[3];
    private static int[] numberOfSkippedQuestion = new int[3];

    private ObservableList<String> reportType = FXCollections.observableArrayList("No Of Tests Taken", "Average Student Scores", "Pass/Fail Stats", "Average scores by LOD", "No of Skipped Questions");
    private ObservableList<String> periodList = FXCollections.observableArrayList("Last Month", "Last Quarter", "Last Year");
    private ObservableList<String> studList = FXCollections.observableArrayList();
    private static String selectedReport = null;
    private static String selectedPeriod = null;
    private static String selectedStudent = null;
    private static int[] scoresByLOD = new int[3];
    private static int[] passing = new int[3];
    private static int[] failing = new int[3];
    private static int i = 0;

    private Label errorMessage;

    @FXML
    private ChoiceBox report;

    @FXML
    private ChoiceBox period;

    @FXML
    private ChoiceBox studDrop;

    @FXML
    private Pane Statistics;

    @FXML
    private Button viewStats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getReport().setItems(getReportType());
        getPeriod().setItems(getPeriodList());
        report.setDisable(true);
        getPeriod().setDisable(true);
        getViewStats().setDisable(true);

        QuizDBImplementation quiz = new QuizDBImplementation();
        ArrayList<StudentResults> tempArr = new ArrayList<>();
        tempArr = quiz.getStudentResults();
        HashMap<String, Integer> student = new HashMap<>();
        for (int i = 0; i < tempArr.size(); i++) {
            if (student.containsKey(tempArr.get(i).getLoginName())) {
                student.put(tempArr.get(i).getLoginName(), student.get(tempArr.get(i).getLoginName()) + 1);
            } else {
                student.put(tempArr.get(i).getLoginName(), 1);
            }
        }

        studList.add(0, "All");
        for (String s : student.keySet()) {
            studList.add(s);
        }

        studDrop.setItems(studList);

    }

    public void setApp(QuizMain application) {
        this.setApplication(application);

    }

    /**
     * This method populates value for report drop down
     *
     * @param event This event reacts to mouse click on drop down
     */
    @FXML
    public void reportSelect(MouseEvent event) {
        if (getApplication() == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            getErrorMessage().setText("Hello");
        } else {

            getReport().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                    getPeriod().getSelectionModel().clearSelection();
                    setSelectedReport(getReport().getSelectionModel().getSelectedItem().toString());

                    getPeriod().setDisable(false);
                }
            });
        }
    }

    /**
     * This method populates value for studDrop down
     *
     * @param event This event reacts to mouse click on drop down
     */
    @FXML
    public void studentSelect(MouseEvent event) {
        if (getApplication() == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            getErrorMessage().setText("Hello");
        } else {

            studDrop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                    selectedStudent = studDrop.getSelectionModel().getSelectedItem().toString();
                    report.setDisable(false);
                }
            });
        }
    }

    /**
     * This method populates value for period selection drop down
     *
     * @param event This event reacts to mouse click on drop down
     */
    @FXML
    public void onPeriodSelected(MouseEvent event) {
        getPeriod().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                setSelectedPeriod(getPeriod().getSelectionModel().getSelectedItem().toString());
                getViewStats().setDisable(false);

            }
        });

    }

    /**
     * This method navigates to Statistics page when you click on "View Stats"
     * button
     *
     * @param event This event reacts to click on "view stats" button
     */
    @FXML
    public void viewStats(ActionEvent event) {

        QuizDBImplementation quiz = new QuizDBImplementation();
        ArrayList<StudentResults> tempArr = new ArrayList<>();
        tempArr = quiz.getStudentResults();

        if (selectedStudent.equals("All")) {
            this.setArr(quiz.getStudentResults());
        } else {
            for (int i = 0; i < tempArr.size(); i++) {
                if (tempArr.get(i).getLoginName().equals(selectedStudent)) {
                    arr.add(tempArr.get(i));
                }
            }
        }
        setArrayValues();
        noOfSkippedQuestions();
        noOfTestsTaken();
        avgStudentScore();
        scoresByLOD();
        studentPassOrFail();

        application.noOfTestsTaken();

    }

    /**
     * This method navigates to previous page when you click on "back" button
     *
     * @param event This event reacts to click on "back" button
     */
    @FXML
    public void goBack(ActionEvent event) {
        getApplication().gotoAdminDashboard();
    }

    /**
     * This method calculates number of tests taken over several periods
     *
     * @return number of tests taken array
     */
    public int[] noOfTestsTaken() {
        for (int i = 0; i < noOfTestsTaken.length; i++) {
            noOfTestsTaken[i] = 0;
        }

        getNoOfTestsTaken()[0] = getMonthly().size();
        getNoOfTestsTaken()[1] = getQuarterly().size();
        getNoOfTestsTaken()[2] = getYearly().size();
        return getNoOfTestsTaken();
    }

    /**
     * This method calculates average student scores over several periods
     *
     * @return average student scores array
     */
    public double[] avgStudentScore() {
        for (int i = 0; i < avgStudentScores.length; i++) {
            avgStudentScores[i] = 0;
        }
        for (int i = 0; i < getMonthly().size(); i++) {
            getAvgStudentScores()[0] += getMonthly().get(i).getTotalCorrect();

        }
        getAvgStudentScores()[0] = getAvgStudentScores()[0] / getMonthly().size();
        for (int i = 0; i < getQuarterly().size(); i++) {
            getAvgStudentScores()[1] = getQuarterly().get(i).getTotalCorrect();

        }
        getAvgStudentScores()[1] = getAvgStudentScores()[1] / getQuarterly().size();
        for (int i = 0; i < getYearly().size(); i++) {
            getAvgStudentScores()[2] = getYearly().get(i).getTotalCorrect();

        }
        getAvgStudentScores()[2] = getAvgStudentScores()[2] / getYearly().size();
        return getAvgStudentScores();
    }

    /**
     * This method calculates number of skipped questions over several periods
     *
     * @return number of skipped questions array
     */
    public int[] noOfSkippedQuestions() {
        for (int i = 0; i < numberOfSkippedQuestion.length; i++) {
            numberOfSkippedQuestion[i] = 0;
        }
        for (int i = 0; i < getMonthly().size(); i++) {

            getNumberOfSkippedQuestion()[0] += getMonthly().get(i).getSkippedQuestions();
        }
        for (int i = 0; i < getQuarterly().size(); i++) {
            getNumberOfSkippedQuestion()[1] += getQuarterly().get(i).getSkippedQuestions();
        }
        for (int i = 0; i < getYearly().size(); i++) {
            getNumberOfSkippedQuestion()[2] += getYearly().get(i).getSkippedQuestions();
        }
        return getNumberOfSkippedQuestion();

    }

    /**
     * This method calculates scores by LOD over several periods
     */
    public void scoresByLOD() {
        for (int i = 0; i < avgEasyCorrect.length; i++) {
            getAvgEasyCorrect()[i] = 0;
            getAvgMedCorrect()[i] = 0;
            getAvgHardCorrect()[i] = 0;
        }
        for (int i = 0; i < getMonthly().size(); i++) {

            getAvgEasyCorrect()[0] += getMonthly().get(i).getLodEasyCorrect();
            getAvgMedCorrect()[0] += getMonthly().get(i).getLodMediumCorrect();
            getAvgHardCorrect()[0] += getMonthly().get(i).getLodHardCorrect();
        }

        getAvgEasyCorrect()[0] = getAvgEasyCorrect()[0] / getMonthly().size();
        getAvgMedCorrect()[0] = getAvgMedCorrect()[0] / getMonthly().size();
        getAvgHardCorrect()[0] = getAvgMedCorrect()[0] / getMonthly().size();

        for (int i = 0; i < getQuarterly().size(); i++) {

            getAvgEasyCorrect()[1] += getQuarterly().get(i).getLodEasyCorrect();
            getAvgMedCorrect()[1] += getQuarterly().get(i).getLodMediumCorrect();
            getAvgHardCorrect()[1] += getQuarterly().get(i).getLodHardCorrect();
        }

        getAvgEasyCorrect()[1] = getAvgEasyCorrect()[1] / getQuarterly().size();
        getAvgMedCorrect()[1] = getAvgMedCorrect()[1] / getQuarterly().size();
        getAvgHardCorrect()[1] = getAvgMedCorrect()[1] / getQuarterly().size();

        for (int i = 0; i < getYearly().size(); i++) {

            getAvgEasyCorrect()[2] += getYearly().get(i).getLodEasyCorrect();
            getAvgMedCorrect()[2] += getYearly().get(i).getLodMediumCorrect();
            getAvgHardCorrect()[2] += getYearly().get(i).getLodHardCorrect();
        }

        getAvgEasyCorrect()[2] = getAvgEasyCorrect()[2] / getYearly().size();
        getAvgMedCorrect()[2] = getAvgMedCorrect()[2] / getYearly().size();
        getAvgHardCorrect()[2] = getAvgMedCorrect()[2] / getYearly().size();

    }

    /**
     * This method sets all required arrays value that holds calculated results
     */
    public void setArrayValues() {

        for (int i = 0; i < getArr().size(); i++) {
            Date d = getArr().get(i).getTestDate();

            Date today = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DAY_OF_MONTH, -30);
            Date today30 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -60);
            Date today60 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -365);
            Date today365 = cal.getTime();

            if (d.compareTo(today30) > 0) {
                getMonthly().add(getArr().get(i));
                getQuarterly().add(getArr().get(i));
                getYearly().add(getArr().get(i));

            } else if (d.compareTo(today30) < 0 && d.compareTo(today60) > 0) {
                getQuarterly().add(getArr().get(i));
                getYearly().add(getArr().get(i));
            } else if (d.compareTo(today60) < 0 && d.compareTo(today365) > 0) {
                getYearly().add(getArr().get(i));

            }

        }

    }

    /**
     * This method calculates number of Pass/Fail students over several periods
     */
    public void studentPassOrFail() {
        for (int i = 0; i < passing.length; i++) {
            passing[i] = 0;
            failing[i] = 0;
        }
        int monthPass = 0, quarterPass = 0, yearPass = 0;
        for (int i = 0; i < getMonthly().size(); i++) {
            if (((getMonthly().get(i).getTotalCorrect() * 10.0) / (getMonthly().get(i).getTotalQuestions() * 10.0)) > 0.4) {
                monthPass++;
            }
        }
        passing[0] = monthPass;
        failing[0] = monthly.size() - passing[0];

        for (int i = 0; i < getQuarterly().size(); i++) {
            if (((getQuarterly().get(i).getTotalCorrect() * 10.0) / (getQuarterly().get(i).getTotalQuestions() * 10.0)) > 0.4) {
                quarterPass++;
            }
        }
        passing[1] = quarterPass;
        failing[1] = quarterly.size() - passing[1];

        for (int i = 0; i < getYearly().size(); i++) {
            if (((getYearly().get(i).getTotalCorrect() * 10.0) / (getYearly().get(i).getTotalQuestions() * 10.0)) > 0.4) {
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
     * @return the avgStudentScores
     */
    public double[] getAvgStudentScores() {
        return avgStudentScores;
    }

    /**
     * @param avgStudentScores the avgStudentScores to set
     */
    public void setAvgStudentScores(double[] avgStudentScores) {
        this.setAvgStudentScores(avgStudentScores);
    }

    /**
     * @return the application
     */
    public QuizMain getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(QuizMain application) {
        this.application = application;
    }

    /**
     * @return the fetchQuestions
     */
    public QuizDBImplementation getFetchQuestions() {
        return fetchQuestions;
    }

    /**
     * @param fetchQuestions the fetchQuestions to set
     */
    public void setFetchQuestions(QuizDBImplementation fetchQuestions) {
        this.fetchQuestions = fetchQuestions;
    }

    /**
     * @return the avgEasyCorrect
     */
    public double[] getAvgEasyCorrect() {
        return avgEasyCorrect;
    }

    /**
     * @param avgEasyCorrect the avgEasyCorrect to set
     */
    public void setAvgEasyCorrect(double[] avgEasyCorrect) {
        this.avgEasyCorrect = avgEasyCorrect;
    }

    /**
     * @return the avgMedCorrect
     */
    public double[] getAvgMedCorrect() {
        return avgMedCorrect;
    }

    /**
     * @param avgMedCorrect the avgMedCorrect to set
     */
    public void setAvgMedCorrect(double[] avgMedCorrect) {
        this.avgMedCorrect = avgMedCorrect;
    }

    /**
     * @return the avgHardCorrect
     */
    public double[] getAvgHardCorrect() {
        return avgHardCorrect;
    }

    /**
     * @param avgHardCorrect the avgHardCorrect to set
     */
    public void setAvgHardCorrect(double[] avgHardCorrect) {
        this.avgHardCorrect = avgHardCorrect;
    }

    /**
     * @return the numberOfSkippedQuestion
     */
    public int[] getNumberOfSkippedQuestion() {
        return numberOfSkippedQuestion;
    }

    /**
     * @param numberOfSkippedQuestion the numberOfSkippedQuestion to set
     */
    public void setNumberOfSkippedQuestion(int[] numberOfSkippedQuestion) {
        this.setNumberOfSkippedQuestion(numberOfSkippedQuestion);
    }

    /**
     * @return the reportType
     */
    public ObservableList<String> getReportType() {
        return reportType;
    }

    /**
     * @param reportType the reportType to set
     */
    public void setReportType(ObservableList<String> reportType) {
        this.reportType = reportType;
    }

    /**
     * @return the periodList
     */
    public ObservableList<String> getPeriodList() {
        return periodList;
    }

    /**
     * @param periodList the periodList to set
     */
    public void setPeriodList(ObservableList<String> periodList) {
        this.periodList = periodList;
    }

    /**
     * @return the scoresByLOD
     */
    public int[] getScoresByLOD() {
        return scoresByLOD;
    }

    /**
     * @param scoresByLOD the scoresByLOD to set
     */
    public void setScoresByLOD(int[] scoresByLOD) {
        this.scoresByLOD = scoresByLOD;
    }

    /**
     * @return the passing
     */
    public int[] getPassing() {
        return passing;
    }

    /**
     * @param passing the passing to set
     */
    public void setPassing(int[] passing) {
        this.passing = passing;
    }

    /**
     * @return the failing
     */
    public int[] getFailing() {
        return failing;
    }

    /**
     * @param failing the failing to set
     */
    public void setFailing(int[] failing) {
        this.failing = failing;
    }

    /**
     * @return the errorMessage
     */
    public Label getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(Label errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the report
     */
    public ChoiceBox getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(ChoiceBox report) {
        this.report = report;
    }

    /**
     * @return the period
     */
    public ChoiceBox getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(ChoiceBox period) {
        this.period = period;
    }

    /**
     * @return the Statistics
     */
    public Pane getStatistics() {
        return Statistics;
    }

    /**
     * @param Statistics the Statistics to set
     */
    public void setStatistics(Pane Statistics) {
        this.Statistics = Statistics;
    }

    /**
     * @return the viewStats
     */
    public Button getViewStats() {
        return viewStats;
    }

    /**
     * @param viewStats the viewStats to set
     */
    public void setViewStats(Button viewStats) {
        this.viewStats = viewStats;
    }

    /**
     * @return the questionsForTest
     */
    public static ArrayList<Question> getQuestionsForTest() {
        return questionsForTest;
    }

    /**
     * @param aQuestionsForTest the questionsForTest to set
     */
    public static void setQuestionsForTest(ArrayList<Question> aQuestionsForTest) {
        questionsForTest = aQuestionsForTest;
    }

    /**
     * @param aNoOfTestsTaken the noOfTestsTaken to set
     */
    public void setNoOfTestsTaken(int[] aNoOfTestsTaken) {
        noOfTestsTaken = aNoOfTestsTaken;
    }

    /**
     * @return the selectedReport
     */
    public static String getSelectedReport() {
        return selectedReport;
    }

    /**
     * @param aSelectedReport the selectedReport to set
     */
    public static void setSelectedReport(String aSelectedReport) {
        selectedReport = aSelectedReport;
    }

    /**
     * @return the selectedPeriod
     */
    public static String getSelectedPeriod() {
        return selectedPeriod;
    }

    /**
     * @param aSelectedPeriod the selectedPeriod to set
     */
    public static void setSelectedPeriod(String aSelectedPeriod) {
        selectedPeriod = aSelectedPeriod;
    }
    
    /**
     * User goes to login page
     * @param event 
     */
    @FXML
    private void logout(ActionEvent event) {
        // call the logoutAccount() to logout of the application
        application.logoutAccount();
    }

}
