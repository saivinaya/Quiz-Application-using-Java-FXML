/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import au.com.bytecode.opencsv.CSVReader;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hari
 */
public class QuizDBImplementation implements QuizDBDAO {

    public void addQuestions(String fileName) {
        try {
            Connection con = QuizHelper.setConnection();
            CSVReader csvReader = QuizHelper.loadCSV(fileName);
            String[] nextLine;
            PreparedStatement ps = null;
            try {
                con.setAutoCommit(false);
                //final int batchSize = 1000;
                //int count = 0;
                char doubleQuotes = '"';
                while ((nextLine = csvReader.readNext()) != null) {

                    if (null != nextLine) {
                        String questionType = nextLine[0];
                        String query = "";
                        if (questionType.equalsIgnoreCase("FIB")) {
                            query = "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,FIB_ANSWER) values(?,?,?,?)";
                            ps = con.prepareStatement(query);

                        } else if (questionType.equalsIgnoreCase("TF")) {
                            query = "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,T_OR_F_ANSWER) values(?,?,?,?)";
                            ps = con.prepareStatement(query);

                        } else {
                            query = "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,CHOICE_1,ANSWER_1,CHOICE_2,ANSWER_2,CHOICE_3,ANSWER_3,CHOICE_4,ANSWER_4) values(?,?,?,?,?,?,?,?,?,?,?)";
                            ps = con.prepareStatement(query);
                        }
                        for (int i = 0; i < nextLine.length; i++) {
                            String trimmedString = nextLine[i].trim();
                            if (trimmedString.equalsIgnoreCase("correct") || trimmedString.equalsIgnoreCase("true")) {
                                ps.setInt(i + 1, 1);
                            } else if (trimmedString.equalsIgnoreCase("incorrect") || trimmedString.equalsIgnoreCase("false")) {
                                ps.setInt(i + 1, 0);
                            } else {
                                if (trimmedString.charAt(trimmedString.length() - 1) == doubleQuotes) {
                                    int doubleQuotesCount = 0;
                                    for (int ref = 0; ref < trimmedString.length(); ref++) {
                                        if (trimmedString.charAt(ref) == doubleQuotes) {
                                            doubleQuotesCount++;
                                        }
                                    }

                                    if (doubleQuotesCount % 2 != 0) {
                                        trimmedString = trimmedString.substring(0, trimmedString.length() - 1);
                                    }
                                }
                                ps.setString(i + 1, trimmedString.trim());
                            }
                        }
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                ps.executeBatch(); // insert remaining records
                System.out.println("In Commit");
                con.commit();
            } catch (Exception e) {
                con.rollback();
                e.printStackTrace();
                throw new Exception(
                        "Error occured while loading data from file to database."
                        + e.getMessage());
            } finally {
                if (null != ps) {
                    System.out.println("In finally 1");
                    ps.close();
                }
                if (null != con) {
                    System.out.println("In finally 2");
                    con.close();
                }

                csvReader.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(QuizDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUser(User user) {
        String insertQuery = "insert into users values(?,?,?,?)";
        try {
            Connection conn = QuizHelper.setConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getLoginName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUniRole());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int questionCount(String difficultyLevel) {
        int countRows = 0;
        return countRows;
    }

    public void addStudentResults(StudentResults result) {
        String insertQuery="insert into test_results values(?,?,?,?,?,?,?,?,?)";
	    	try {
                    Connection conn = QuizHelper.setConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(insertQuery);
				preparedStatement.setString(1, result.getLoginName());
				preparedStatement.setInt(2, result.getLodEasyQuestions());
				preparedStatement.setInt(3, result.getLodMediumQuestions());
				preparedStatement.setInt(4,result.getLodHardQuestions());
				preparedStatement.setInt(5,result.getTotalQuestions());
				preparedStatement.setInt(6,result.getLodEasyCorrect());
				preparedStatement.setInt(7,result.getLodMediumCorrect());
				preparedStatement.setInt(8,result.getLodHardCorrect());
                                java.util.Date utilDate = result.getTestDate();
                                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				preparedStatement.setDate(9,sqlDate );
				preparedStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    ;
    public ArrayList<Question> selectQuestions(int numOfQuestions, String difficultyLevel) {
        ArrayList<Question> questionList = null;
        return questionList;
    }

    public ArrayList<User> selectUser(String userName, String password) {
        ArrayList<User> user = null;
        return user;
    }

    public ArrayList<StudentResults> getStudentResults(Date date) {
        ArrayList<StudentResults> studentResults = null;
        return studentResults;
    }

    public int[] getQuestionTypes(ArrayList<Question> questions) {
        int arrayqQuestionType[] = new int[4];
        //array will be [no.of MC, MA, T or F, FIB]
        return arrayqQuestionType;
    }
}
