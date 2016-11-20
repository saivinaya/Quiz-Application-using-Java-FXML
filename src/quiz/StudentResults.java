/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hari
 */
public class StudentResults {

    private String loginName;
    private int lodEasyQuestions;
    private int lodMediumQuestions;
    private int lodHardQuestions;
    private int totalQuestions;
    private int totalCorrect;
    private int lodEasyCorrect;
    private int lodMediumCorrect;
    private int lodHardCorrect;
    private Date testDate;
    private ArrayList<StudentResults> arr = new ArrayList<>();
    private ArrayList<StudentResults> monthly = new ArrayList<>();
    private ArrayList<StudentResults> quarterly = new ArrayList<>();
    private ArrayList<StudentResults> yearly = new ArrayList<>();
    private int[] noOfTestsTaken = new int[3];
    private double[] avgStudentScores = new double[3];
    private int easyCorrect,medCorrect,hardCorrect;
    
    public StudentResults(ArrayList<StudentResults> array){
        this.arr = array;
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getLodEasyQuestions() {
        return lodEasyQuestions;
    }

    public void setLodEasyQuestions(int lodEasyQuestions) {
        this.lodEasyQuestions = lodEasyQuestions;
    }

    public int getLodMediumQuestions() {
        return lodMediumQuestions;
    }

    public void setLodMediumQuestions(int lodMediumQuestions) {
        this.lodMediumQuestions = lodMediumQuestions;
    }

    public int getLodHardQuestions() {
        return lodHardQuestions;
    }

    public void setLodHardQuestions(int lodHardQuestions) {
        this.lodHardQuestions = lodHardQuestions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getLodEasyCorrect() {
        return lodEasyCorrect;
    }

    public void setLodEasyCorrect(int lodEasyCorrect) {
        this.lodEasyCorrect = lodEasyCorrect;
    }

    public int getLodMediumCorrect() {
        return lodMediumCorrect;
    }

    public void setLodMediumCorrect(int lodMediumCorrect) {
        this.lodMediumCorrect = lodMediumCorrect;
    }

    public int getLodHardCorrect() {
        return lodHardCorrect;
    }

    public void setLodHardCorrect(int lodHardCorrect) {
        this.lodHardCorrect = lodHardCorrect;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public int[] noOfTestsTaken() {
        getNoOfTestsTaken()[0] = getMonthly().size();
        getNoOfTestsTaken()[1] = getQuarterly().size();
        getNoOfTestsTaken()[2] = getYearly().size();
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
        for (int i = 0; i < arr.size(); i++) {
            easyCorrect += arr.get(i).getLodEasyCorrect();
            medCorrect += arr.get(i).getLodMediumCorrect();
            hardCorrect += arr.get(i).getLodHardCorrect();
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public void setArrayValues() {
        for (int i = 0; i < getArr().size(); i++) {
            Date d = getArr().get(i).getTestDate();

            Calendar c = toCalendar(d);
            int month = c.get(Calendar.MONTH);

            if (month == Calendar.getInstance().get(Calendar.MONTH)) {
                getMonthly().add(getArr().get(i));
                getQuarterly().add(getArr().get(i));
                getYearly().add(getArr().get(i));

            } else if (month < Calendar.getInstance().get(Calendar.MONTH) - 2 && month > Calendar.getInstance().get(Calendar.MONTH)) {
                getQuarterly().add(getArr().get(i));
                getYearly().add(getArr().get(i));
            } else if (month < Calendar.getInstance().get(Calendar.MONTH) - 12 && month > Calendar.getInstance().get(Calendar.MONTH) - 2) {
                getYearly().add(getArr().get(i));

            }

        }
    }

    /**
     * @return the totalCorrect
     */
    public int getTotalCorrect() {
        return totalCorrect;
    }

    /**
     * @param totalCorrect the totalCorrect to set
     */
    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
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
