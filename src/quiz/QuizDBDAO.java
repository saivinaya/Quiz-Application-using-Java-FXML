
package quiz;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Group
 */
public interface QuizDBDAO {
    
    public void addQuestions();
    public void addUser();
    public void addStudentResults(StudentResults result);    
    public ArrayList<Question> selectQuestions(int numOfQuestions,String difficultyLevel);
    public ArrayList<User> selectUser(String userName,String password);   
    public ArrayList<StudentResults> getStudentResults(Date date);
}
