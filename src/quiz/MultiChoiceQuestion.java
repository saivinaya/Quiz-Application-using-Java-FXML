/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 * This class is used to define the Multiple Choice Questions, it extends
 * Question class; it has 12 fields "choice1", "choice2", "choice3", "choice4",
 * "ans1", "ans2", "ans3", "ans4", "userInput1", "userInput2", "userInput3",
 * "userInput4" which are all private and hence has getters and setters; it has
 * validateAnswer() method which validates the answers given by the user during
 * the quiz; it has toString() method which prints the entire Object as a string
 *
 * @author Group
 */
public class MultiChoiceQuestion extends Question {

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private boolean ans1;
    private boolean ans2;
    private boolean ans3;
    private boolean ans4;
    private boolean userInput1;
    private boolean userInput2;
    private boolean userInput3;
    private boolean userInput4;

    //default constructor

    /**
     *
     */
    public MultiChoiceQuestion() {
    }

    /**
     *
     * @param QuestionType the type of question
     * @param LevelOfDifficulty difficulty level of the question
     * @param Questiondesc description of the question
     * @param choice1 choice 1 for the question
     * @param choice2 choice 2 for the question
     * @param choice3 choice 3 for the question
     * @param choice4 choice 4 for the question
     * @param ans1 answer 1 correct or not for the question
     * @param ans2 answer 2 correct or not for the question
     * @param ans3 answer 3 correct or not for the question
     * @param ans4 answer 4 correct or not for the question
     */
    public MultiChoiceQuestion(String QuestionType, String LevelOfDifficulty, String Questiondesc, String choice1, String choice2, String choice3, String choice4, boolean ans1, boolean ans2, boolean ans3, boolean ans4) {
        super(QuestionType, LevelOfDifficulty, Questiondesc);
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    /**
     *
     * @return choice 1 for the question
     */
    public String getChoice1() {
        return choice1;
    }

    /**
     *
     * @param choice1 choice 1 for the question
     */
    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    /**
     *
     * @return choice 2 for the question
     */
    public String getChoice2() {
        return choice2;
    }

    /**
     *
     * @param choice2 choice 2 for the question
     */
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    /**
     *
     * @return choice 3 for the question
     */
    public String getChoice3() {
        return choice3;
    }

    /**
     *
     * @param choice3 choice 3 for the question
     */
    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    /**
     *
     * @return choice 4 for the question
     */
    public String getChoice4() {
        return choice4;
    }

    /**
     *
     * @param choice4 choice 4 for the question
     */
    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    /**
     *
     * @return answer 1 correct or not for the question
     */
    public boolean isAns1() {
        return ans1;
    }

    /**
     *
     * @param ans1 answer 1 correct or not for the question
     */
    public void setAns1(boolean ans1) {
        this.ans1 = ans1;
    }

    /**
     *
     * @return answer 2 correct or not for the question
     */
    public boolean isAns2() {
        return ans2;
    }

    /**
     *
     * @param ans2 answer 2 correct or not for the question
     */
    public void setAns2(boolean ans2) {
        this.ans2 = ans2;
    }

    /**
     *
     * @return answer 3 correct or not for the question
     */
    public boolean isAns3() {
        return ans3;
    }

    /**
     *
     * @param ans3 answer 3 correct or not for the question
     */
    public void setAns3(boolean ans3) {
        this.ans3 = ans3;
    }

    /**
     *
     * @return answer 4 correct or not for the question
     */
    public boolean isAns4() {
        return ans4;
    }

    /**
     *
     * @param ans4 answer 4 correct or not for the question
     */
    public void setAns4(boolean ans4) {
        this.ans4 = ans4;
    }

    /**
     *
     * @return the user input for choice 1
     */
    public boolean isUserInput1() {
        return userInput1;
    }

    /**
     *
     * @param userInput1 the user input for choice 1
     */
    public void setUserInput1(boolean userInput1) {
        this.userInput1 = userInput1;
    }

    /**
     *
     * @return the user input for choice 2
     */
    public boolean isUserInput2() {
        return userInput2;
    }

    /**
     *
     * @param userInput2 the user input for choice 2
     */
    public void setUserInput2(boolean userInput2) {
        this.userInput2 = userInput2;
    }

    /**
     *
     * @return the user input for choice 3
     */
    public boolean isUserInput3() {
        return userInput3;
    }

    /**
     *
     * @param userInput3 the user input for choice 3
     */
    public void setUserInput3(boolean userInput3) {
        this.userInput3 = userInput3;
    }

    /**
     *
     * @return the user input for choice 4
     */
    public boolean isUserInput4() {
        return userInput4;
    }

    /**
     *
     * @param userInput4 the user input for choice 4
     */
    public void setUserInput4(boolean userInput4) {
        this.userInput4 = userInput4;
    }

    public boolean validateAnswer() {
        // if only al the options are matched the answer is correct
        if (this.isAns1() == this.isUserInput1() && this.isAns2() == this.isUserInput2() && this.isAns3() == this.isUserInput3() && this.isAns4() == this.isUserInput4()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionType=" + QuestionType + ", LevelOfDifficulty=" + LevelOfDifficulty + ", Questiondesc=" + Questiondesc + ", skipQuestion=" + skipQuestion + '}' + "MultiChoiceQuestion{" + "choice1=" + choice1 + ", choice2=" + choice2 + ", choice3=" + choice3 + ", choice4=" + choice4 + ", ans1=" + ans1 + ", ans2=" + ans2 + ", ans3=" + ans3 + ", ans4=" + ans4 + ", userInput1=" + userInput1 + ", userInput2=" + userInput2 + ", userInput3=" + userInput3 + ", userInput4=" + userInput4 + '}';
    }
}
