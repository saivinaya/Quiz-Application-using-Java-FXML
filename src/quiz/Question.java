package quiz;

/**
 * This class is used to define the Question class, it is a super class for all
 * the questions it has 4 fields "QuestionType", "LevelOfDifficulty",
 * "Questiondesc" and "skipQuestion" which are protected and common to all
 * questions, it has getters and setters for all these variables; it has
 * toString() method which prints the entire Object as a string
 *
 * @author Group
 */
public abstract class Question {

    protected String QuestionType;
    protected String LevelOfDifficulty;
    protected String Questiondesc;
    protected boolean skipQuestion;

    //default constructor
    public Question() {
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
    public abstract boolean validateAnswer();

}
