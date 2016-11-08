
package quiz;

import java.util.ArrayList;

/**
 *
 * @author Group
 */
public interface QuizDBDAO {
    
    public void addQuestions();
    public void addUser();
    public void addStudentResults();    
    public ArrayList<Question> selectQuestions(int numOfQuestions,String difficultyLevel);
    public ArrayList<User> selectUser(String userName,String password);    
}
