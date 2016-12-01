//QuizDBDAO.java
package quiz;

import java.util.ArrayList;
import java.util.Date;

/**
 * QuizDBDAO: This is the main interface for the
 *
 * @author Group
 */
public interface QuizDBDAO {

    /**
     * This method is used to import a CSV file to add data to Question table
     *
     * @param fileName CSV file path
     * @return String
     */
    public String addQuestions(String fileName);

    /**
     * This method is defined to add the user details to the users table
     *
     * @param user input parameter of the type StudentResults
     */
    public void addUser(User user);

    /**
     * This method is defined to add the student results details to the
     * test_results table
     *
     * @param result input parameter of the type StudentResults
     */
    public void addStudentResults(StudentResults result);

    /**
     * questionCount: This method returns the number of questions available on
     * the database.
     *
     * @param difficultyLevel A String indicating the difficulty level.
     * @return An Integer with the number of questions available.
     */
    public int questionCount(String difficultyLevel);

    /**
     * selectQuestions: This method returns an arrayList with question objects
     * for the quiz.
     *
     * @param numOfQuestions An Integer with the number of questions to fetch.
     * @param difficultyLevel A String indicating the difficulty level.
     * @return An ArrayList with the questions for the quiz.
     */
    public ArrayList<Question> selectQuestions(int numOfQuestions, String difficultyLevel);

    /**
     * This method returns a user.
     *
     * @param loginName A string with the login name of the user.
     * @param password A string with the password of the user.
     * @return An user object.
     */
    public User selectUser(String loginName, String password);

    /**
     * This method returns false if the data exists.
     *
     * @param loginName A String with the login name of the user.
     * @return A boolean returns false if the exists.
     */
    public boolean selectUser(String loginName);

    /**
     * selectQuestions: This method returns the result of a specific
     * studentResult
     *
     * @return An ArrayList with the student results of a specific user.
     */
    public ArrayList<StudentResults> getStudentResults();
}//end of QuizDBDAO
