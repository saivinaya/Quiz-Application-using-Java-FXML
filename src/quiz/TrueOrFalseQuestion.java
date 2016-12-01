/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 * This class is used to define the Fill in the blanks Questions, it extends
 * Question class; it has 3 fields "ans", "userInputTrue" and "userInputFalse"
 * which are private and hence has getters and setters; it has validateAnswer()
 * method which validates the answers given by the user during the quiz; it has
 * toString() method which prints the entire Object as a string
 *
 * @author Group
 */
public class TrueOrFalseQuestion extends Question {

    private boolean ans;
    private boolean userInputTrue;
    private boolean userInputFalse;

    /**
     * //default constructor
     */
    public TrueOrFalseQuestion() {
    }

    /**
     *
     * @param QuestionType type of the question
     * @param LevelOfDifficulty difficulty level of the question
     * @param Questiondesc question description
     * @param ans answer for the question
     */
    public TrueOrFalseQuestion(String QuestionType, String LevelOfDifficulty, String Questiondesc, boolean ans) {
        super(QuestionType, LevelOfDifficulty, Questiondesc);
        this.ans = ans;
    }

    /**
     *
     * @return answer for the question
     */
    public boolean isAns() {
        return ans;
    }

    /**
     *
     * @param ans answer for the question
     */
    public void setAns(boolean ans) {
        this.ans = ans;
    }

    /**
     *
     * @return the user input for true choice
     */
    public boolean isUserInputTrue() {
        return userInputTrue;
    }

    /**
     *
     * @param userInputTrue the user input for true choice
     */
    public void setUserInputTrue(boolean userInputTrue) {
        this.userInputTrue = userInputTrue;
    }

    /**
     *
     * @return the user input for false choice
     */
    public boolean isUserInputFalse() {
        return userInputFalse;
    }

    /**
     *
     * @param userInputFalse the user input for false choice
     */
    public void setUserInputFalse(boolean userInputFalse) {
        this.userInputFalse = userInputFalse;
    }

    public boolean validateAnswer() {
        // if the answer is true then userInputTrue must be true and if answer is false then userInputFalse must be true
        if (this.isAns() == true) {
            if (this.isUserInputTrue() == true) {
                return true;
            } else {
                return false;
            }
        } else if (this.isAns() == false) {
            if (this.isUserInputFalse() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionType=" + QuestionType + ", LevelOfDifficulty=" + LevelOfDifficulty + ", Questiondesc=" + Questiondesc + ", skipQuestion=" + skipQuestion + '}' + "TrueOrFalseQuestion{" + "ans=" + ans + ", userInputTrue=" + userInputTrue + ", userInputFalse=" + userInputFalse + '}';
    }
}
