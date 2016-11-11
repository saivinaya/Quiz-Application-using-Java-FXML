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
public class FillInTheBlanks extends Question{
    //fields: ans(String),userInput
           //method validateAnswer

    private String ans;
    private String userInput;

    public FillInTheBlanks()
    {
    }
    
    public FillInTheBlanks(String QuestionType, String LevelOfDifficulty, String Questiondesc,String ans) 
    {
        super(QuestionType,LevelOfDifficulty,Questiondesc);
        this.ans = ans;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    
}
