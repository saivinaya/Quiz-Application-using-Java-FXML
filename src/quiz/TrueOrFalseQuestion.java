/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

/**
 *
 * @author Hari
 */
public class TrueOrFalseQuestion extends Question {
    //fields: ans (boolean),userInput
           //method validateAnswer

    private boolean ans;
    private boolean userInput;

    public TrueOrFalseQuestion()
    {
    }
    
    public TrueOrFalseQuestion(String QuestionType, String LevelOfDifficulty, String Questiondesc,boolean ans) 
    {
        super(QuestionType,LevelOfDifficulty,Questiondesc);
        this.ans = ans;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }

    public boolean isUserInput() {
        return userInput;
    }

    public void setUserInput(boolean userInput) {
        this.userInput = userInput;
    }
}
