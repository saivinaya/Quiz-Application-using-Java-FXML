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
    private boolean userInputTrue;
    private boolean userInputFalse;

    public TrueOrFalseQuestion() {
    }

    public TrueOrFalseQuestion(String QuestionType, String LevelOfDifficulty, String Questiondesc, boolean ans) {
        super(QuestionType, LevelOfDifficulty, Questiondesc);
        this.ans = ans;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }

    public boolean isUserInputTrue() {
        return userInputTrue;
    }

    public void setUserInputTrue(boolean userInputTrue) {
        this.userInputTrue = userInputTrue;
    }

    public boolean isUserInputFalse() {
        return userInputFalse;
    }

    public void setUserInputFalse(boolean userInputFalse) {
        this.userInputFalse = userInputFalse;
    }

    public boolean validateAnswer(TrueOrFalseQuestion qObject) {
        if (qObject.isAns() == true) 
        {   if (qObject.isUserInputTrue() == true)
            {   return true;
            }
            else
            {   return false;
            }
        } 
        else if (qObject.isAns() == false) 
        {   if (qObject.isUserInputFalse() == true)
            {   return true;
            }
            else
            {   return false;
            }
        }
        else
        {   return false;
        }
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionType=" + QuestionType + ", LevelOfDifficulty=" + LevelOfDifficulty + ", Questiondesc=" + Questiondesc + ", skipQuestion=" + skipQuestion + '}' + "TrueOrFalseQuestion{" + "ans=" + ans + ", userInputTrue=" + userInputTrue + ", userInputFalse=" + userInputFalse + '}';
    }
}
