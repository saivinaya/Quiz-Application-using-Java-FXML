/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.util.ArrayList;

/**
 *
 * @author Hari
 */
public class QuizDBImplementation implements QuizDBDAO{
    
    public void addQuestions(){};
    public void addUser(){};    
    public void addStudentResults(){};
    public ArrayList<Question> selectQuestions(int numOfQuestions,String difficultyLevel){
    ArrayList<Question> questionList=null;
    return questionList;
    }
    public ArrayList<User> selectUser(String userName,String password){
        ArrayList<User> user=null;
        return user;
    }   
    
}
