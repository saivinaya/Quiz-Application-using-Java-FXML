
package quiz;

/**
 *
 * @author Group
 */
public class Question {
   //Fields: QuestionType,LevelOfDifficulty, Questiondesc, 
             //method abstract: validateAnswer
    protected String QuestionType;
    protected String LevelOfDifficulty;
    protected String Questiondesc;
    protected boolean skipQuestion;

    public Question()
    {
    }

    public Question(String QuestionType, String LevelOfDifficulty, String Questiondesc) {
        this.QuestionType = QuestionType;
        this.LevelOfDifficulty = LevelOfDifficulty;
        this.Questiondesc = Questiondesc;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String QuestionType) {
        this.QuestionType = QuestionType;
    }

    public String getLevelOfDifficulty() {
        return LevelOfDifficulty;
    }

    public void setLevelOfDifficulty(String LevelOfDifficulty) {
        this.LevelOfDifficulty = LevelOfDifficulty;
    }

    public String getQuestiondesc() {
        return Questiondesc;
    }

    public void setQuestiondesc(String Questiondesc) {
        this.Questiondesc = Questiondesc;
    }

    public boolean isSkipQuestion() {
        return skipQuestion;
    }

    public void setSkipQuestion(boolean skipQuestion) {
        this.skipQuestion = skipQuestion;
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionType=" + QuestionType + ", LevelOfDifficulty=" + LevelOfDifficulty + ", Questiondesc=" + Questiondesc + ", skipQuestion=" + skipQuestion + '}';
    }
    
}
