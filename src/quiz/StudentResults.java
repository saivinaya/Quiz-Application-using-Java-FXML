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
    
    
    public StudentResults(){
        
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


}
