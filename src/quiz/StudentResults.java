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
 * This is student Results class
 * @author Kuhu
 */
public class StudentResults implements Comparable<StudentResults> {

    private String loginName;
    private int lodEasyQuestions;
    private int lodMediumQuestions;
    private int lodHardQuestions;
    private int totalQuestions;
    private int totalCorrect;
    private int lodEasyCorrect;
    private int lodMediumCorrect;
    private int lodHardCorrect;
    private int skippedQuestions;
    private Date testDate;
/**
 * Constructor that set StudentResults values
 * @param loginName name
 * @param lodEasyQuestions number of easy questions
 * @param lodMediumQuestions number of medium questions
 * @param lodHardQuestions number of hard questions
 * @param totalQuestions number of total questions
 * @param totalCorrect number of total correct questions
 * @param lodEasyCorrect number of total easy correct questions
 * @param lodMediumCorrect number of total medium correct questions
 * @param lodHardCorrect number of total hard correct questions
 * @param skippedQuestions number of total skipped questions
 * @param testDate 
 */
    public StudentResults(String loginName, int lodEasyQuestions, int lodMediumQuestions, int lodHardQuestions, int totalQuestions, int totalCorrect, int lodEasyCorrect, int lodMediumCorrect, int lodHardCorrect, int skippedQuestions, Date testDate) {
        this.loginName = loginName;
        this.lodEasyQuestions = lodEasyQuestions;
        this.lodMediumQuestions = lodMediumQuestions;
        this.lodHardQuestions = lodHardQuestions;
        this.totalQuestions = totalQuestions;
        this.totalCorrect = totalCorrect;
        this.lodEasyCorrect = lodEasyCorrect;
        this.lodMediumCorrect = lodMediumCorrect;
        this.lodHardCorrect = lodHardCorrect;
        this.skippedQuestions = skippedQuestions;
        this.testDate = testDate;
    }

    
/**
 * No-arg constructor
 */
    public StudentResults() {

    }
    
    /**
     * get login name
     * @return login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * set login name
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * get  lod easy
     * @return lod easy
     */
    public int getLodEasyQuestions() {
        return lodEasyQuestions;
    }

    /**
     * set  lod easy
     * @param lodEasyQuestions
     */
    public void setLodEasyQuestions(int lodEasyQuestions) {
        this.lodEasyQuestions = lodEasyQuestions;
    }

    /**
     * get  lod medium
     * @return lod easy
     */
    public int getLodMediumQuestions() {
        return lodMediumQuestions;
    }

    /**
     * set  lod medium
     * @param lodMediumQuestions
     */
    public void setLodMediumQuestions(int lodMediumQuestions) {
        this.lodMediumQuestions = lodMediumQuestions;
    }

    /**
     * get  lod hard
     * @return lod hard
     */
    public int getLodHardQuestions() {
        return lodHardQuestions;
    }

    /**
     * set  lod hard
     * @param lodHardQuestions
     */
    public void setLodHardQuestions(int lodHardQuestions) {
        this.lodHardQuestions = lodHardQuestions;
    }

    /**
     * get  total questions
     * @return total questions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }
    
    /**
     * set  total questions
     * @param totalQuestions
     */

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * get  total correct questions
     * @return total correct questions
     */
    public int getTotalCorrect() {
        return totalCorrect;
    }

    /**
     * set  total correct questions
     * @param totalCorrect
     */
    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    /**
     * get  easy correct questions
     * @return easy correct questions
     */
    public int getLodEasyCorrect() {
        return lodEasyCorrect;
    }

    /**
     * set  easy correct questions
     * @param lodEasyCorrect
     */
    public void setLodEasyCorrect(int lodEasyCorrect) {
        this.lodEasyCorrect = lodEasyCorrect;
    }

/**
     * get  medium correct questions
     * @return medium correct questions
     */
    public int getLodMediumCorrect() {
        return lodMediumCorrect;
    }
/**
     * set  medium correct questions
     * @param lodMediumCorrect
     */
    public void setLodMediumCorrect(int lodMediumCorrect) {
        this.lodMediumCorrect = lodMediumCorrect;
    }
/**
     * get  hard correct questions
     * @return hard correct questions
     */
    public int getLodHardCorrect() {
        return lodHardCorrect;
    }
/**
     * set  hard correct questions
     * @param lodHardCorrect
     */
    public void setLodHardCorrect(int lodHardCorrect) {
        this.lodHardCorrect = lodHardCorrect;
    }
/**
     * get  skipped questions
     * @return skipped questions
     */
    public int getSkippedQuestions() {
        return skippedQuestions;
    }

    /**
     * set  skipped questions
     * @param skippedQuestions
     */
    public void setSkippedQuestions(int skippedQuestions) {
        this.skippedQuestions = skippedQuestions;
    }
/**
     * get  test date
     * @return test date
     */
    public Date getTestDate() {
        return testDate;
    }
/**
     * set  test date
     * @param testDate
     */
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    
    /**
     * compares two objects with respect to date
     * @param o
     * @return 
     */
    @Override
    public int compareTo(StudentResults o) {
        if (getTestDate() == null || o.getTestDate() == null) {
            return 0;
        }
        int compareValue = getTestDate().compareTo(o.getTestDate());
        if (compareValue == 1) {
            return -1;
        } else if (compareValue == -1) {
            return 1;
        } else {
            return 0;
        }
    }
}
