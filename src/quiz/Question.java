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

    /**
     *
     */
    protected String QuestionType;

    /**
     *
     */
    protected String LevelOfDifficulty;

    /**
     *
     */
    protected String Questiondesc;

    /**
     *
     */
    protected boolean skipQuestion;

    //default constructor

    /**
     *
     */
    public Question() {
    }

    /**
     *
     * @param QuestionType the type of question
     * @param LevelOfDifficulty the difficulty level of the question
     * @param Questiondesc the question description
     */
    public Question(String QuestionType, String LevelOfDifficulty, String Questiondesc) {
        this.QuestionType = QuestionType;
        this.LevelOfDifficulty = LevelOfDifficulty;
        this.Questiondesc = Questiondesc;
    }

    /**
     *
     * @return the question type
     */
    public String getQuestionType() {
        return QuestionType;
    }

    /**
     *
     * @param QuestionType type of question
     */
    public void setQuestionType(String QuestionType) {
        this.QuestionType = QuestionType;
    }

    /**
     *
     * @return the difficulty level of the question
     */
    public String getLevelOfDifficulty() {
        return LevelOfDifficulty;
    }

    /**
     *
     * @param LevelOfDifficulty the difficulty level of the question
     */
    public void setLevelOfDifficulty(String LevelOfDifficulty) {
        this.LevelOfDifficulty = LevelOfDifficulty;
    }

    /**
     *
     * @return the question description
     */
    public String getQuestiondesc() {
        return Questiondesc;
    }

    /**
     *
     * @param Questiondesc the question description
     */
    public void setQuestiondesc(String Questiondesc) {
        this.Questiondesc = Questiondesc;
    }

    /**
     *
     * @return is to know if question has been skipped
     */
    public boolean isSkipQuestion() {
        return skipQuestion;
    }

    /**
     *
     * @param skipQuestion question skipped or not
     */
    public void setSkipQuestion(boolean skipQuestion) {
        this.skipQuestion = skipQuestion;
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionType=" + QuestionType + ", LevelOfDifficulty=" + LevelOfDifficulty + ", Questiondesc=" + Questiondesc + ", skipQuestion=" + skipQuestion + '}';
    }

    /**
     *
     * @return
     */
    public abstract boolean validateAnswer();

}
