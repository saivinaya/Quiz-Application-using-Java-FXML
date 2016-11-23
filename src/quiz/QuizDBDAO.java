
package quiz;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Group
 */
public interface QuizDBDAO {
    
    public String addQuestions(String fileName);
    public void addUser(User user);
    public void addStudentResults(StudentResults result);  
    public int questionCount(String difficultyLevel);
    public ArrayList<Question> selectQuestions(int numOfQuestions,String difficultyLevel);
    public User selectUser(String loginName,String password);   
    public User selectUser(String loginName);   
    public ArrayList<StudentResults> getStudentResults();
}
