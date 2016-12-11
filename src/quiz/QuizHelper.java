package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * QuizHelper: This is the common helper class for this application
 * @author Group
 */
public class QuizHelper {

    /**
     * define  the delimiter for text file
     */
    public static char delimiter = ',';

    // method1: read file and return a string array list of records in the file

    /**
     * getFileData
     * @param FileName
     * @return
     */
    public ArrayList<String> getFileData(String FileName) {
        ArrayList<String> fileList = null;

        return fileList;
    }


    /**
     *printToPDF:takes string array list and  write to pdf file
     * @param fileList
     */
    public void printToPDF(ArrayList<String> fileList) {

    }
     

    /**
     * validateInputString:take input string and validate the input data based on requirement.  use regexpressions for the same
     * @param input
     * @param type
     * @return
     */
    public boolean validateInputString(String input, String type) {
        boolean isCorrect = true;

        return isCorrect;
    }

    /**
     * setConnection: set the connection used across the application
     * @return
     */
    public static Connection setConnection() {
        Connection conn = null;
        try {
            // Establish Derby connection with QuizDB embedded mode
            //String connectionString = "jdbc:derby:QuizDB";
            String connectionString="jdbc:derby://localhost:1527/QuizDB";
            String username = "app";
            String password = "app";
            conn = DriverManager.getConnection(connectionString, username, password);

        } catch (SQLException ex) {
            Logger.getLogger(QuizHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(QuizHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    /**
     * loadCSV: Read CSV files
     * @param csvFile
     * @return
     * @throws Exception
     */
    public static CSVReader loadCSV(String csvFile) throws Exception {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile), delimiter);
            return csvReader;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occured while executing file. "
                    + e.getMessage());
        }
    }
    
    /**
     * evaluateQuizResult: check the results of the quiz
     * @param questions
     * @return
     */
    public int[] evaluateQuizResult(ArrayList<Question> questions) {
        int[] arrayCorrectQuestionType = new int[5];
        int skipped = 0;
        int easyCorrect = 0;
        int mediumCorrect = 0;
        int hardCorrect = 0;
        int easy = 0;
        int medium = 0;
        int hard = 0;
        int totalCorrect = 0;
        for (Question q : questions) {
            //System.out.println(q.getQuestiondesc() + "\n");
            if (q.isSkipQuestion()) {
                skipped += 1;
            } else if (q.validateAnswer()) {
                //System.out.println("Answer correct\n\n");
                String type = q.LevelOfDifficulty;
                if (type.equalsIgnoreCase("E")) {
                    easyCorrect += 1;
                } else if (type.equalsIgnoreCase("M")) {
                    mediumCorrect += 1;
                } else if (type.equalsIgnoreCase("H")) {
                    hardCorrect += 1;
                }
            }
        }
        totalCorrect = easyCorrect + mediumCorrect + hardCorrect;
        arrayCorrectQuestionType[0] = questions.size();
        arrayCorrectQuestionType[1] = easyCorrect;
        arrayCorrectQuestionType[2] = mediumCorrect;
        arrayCorrectQuestionType[3] = hardCorrect;
        arrayCorrectQuestionType[4] = skipped;
        for (Question q : questions){
            String difficulty = q.getLevelOfDifficulty();
            if (difficulty.equalsIgnoreCase("E")){
                easy +=1;
            }
            else if (difficulty.equalsIgnoreCase("M")){
                medium +=1;
            }
            else if (difficulty.equalsIgnoreCase("H")){
                hard +=1;
            }
        }
        String loginName = QuizMain.loginName;
        Date testDate = new Date();
        StudentResults result = new StudentResults(loginName, easy, medium, hard, questions.size(), totalCorrect, easyCorrect, mediumCorrect, hardCorrect, skipped, testDate);
        QuizDBImplementation storeData= new QuizDBImplementation();
        storeData.addStudentResults(result);
        return arrayCorrectQuestionType;
    }
     
     /**
      * FileWriter: This method takes fileName and ArrayList of string as input
     * and generates the output file
      * @param fileName
      * @param content
      * @throws FileNotFoundException
      * @throws IOException 
      */
    public void FileWriter(String fileName, ArrayList<String> content) throws FileNotFoundException, IOException {
      //  File file = new java.io.File(fileName);
        Path out = Paths.get(fileName);
        Files.write(out,content,Charset.defaultCharset(),StandardOpenOption.CREATE);
     }//end of method FileWriter
    
    /**
     *
     * @throws SQLException
     */
    public void createTable() throws SQLException
    {
        String url = "jdbc:derby://localhost:1527/QuizDB;create=true";
        String username = "app";
        String password = "app";
        try{
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();
        DatabaseMetaData dbm = conn.getMetaData();
            // check if "Quiz" table is there or not, if not there create the table if it is there do not recreate it again.
            ResultSet tables = dbm.getTables(null, null, "QUESTION", null);
            if (!tables.next()) 
            {
                statement.executeUpdate("create table QUESTION(QUESTION_TYPE VARCHAR(30),QUESTION_DESCRIPTION CLOB, DIFFICULTY_LEVEL VARCHAR(10), CHOICE_1 CLOB, CHOICE_2 CLOB, CHOICE_3 CLOB, CHOICE_4 CLOB, ANSWER_1 BOOLEAN, ANSWER_2 BOOLEAN, ANSWER_3 BOOLEAN, ANSWER_4 BOOLEAN, T_OR_F_ANSWER BOOLEAN, FIB_ANSWER VARCHAR(300))");
            }
            ResultSet tables1 = dbm.getTables(null, null, "TEST_RESULTS", null);
            if (!tables1.next()) 
            {   
                statement.executeUpdate("create table TEST_RESULTS(LOGIN_NAME VARCHAR(30), LOD_EASY_QUESTIONS  INTEGER, LOD_MEDIUM_QUESTIONS  INTEGER, LOD_HARD_QUESTIONS  INTEGER, TOTAL_QUESTIONS INTEGER, LOD_EASY_CORRECT  INTEGER,  LOD_MEDIUM_CORRECT  INTEGER, LOD_HARD_CORRECT  INTEGER, SKIPPED_QUESTIONS  INTEGER, TEST_TAKEN_DATE DATE)");
            }
            ResultSet tables2 = dbm.getTables(null, null, "USERS", null);
            if (!tables2.next()) 
            {   statement.executeUpdate("create table USERS(LOGIN_NAME VARCHAR(30) PRIMARY KEY, USER_NAME VARCHAR(100), PASSWORD VARCHAR(30), UNI_ROLE VARCHAR(30))");
                System.out.println("Before insert");
                String query = "insert into USERS(select ?,?,?,? from SYSIBM.SYSDUMMY1 where not exists (select 1 from USERS inr where inr.login_name=?))";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1,"admin");
                pstm.setString(2,"Main Admin");
                pstm.setString(3,"admin");
                pstm.setString(4,"Admin");
                pstm.setString(5,"admin");
                pstm.executeUpdate();
                conn.commit();
            }
            else
            {
                String query = "insert into USERS(select ?,?,?,? from SYSIBM.SYSDUMMY1 where not exists (select 1 from USERS inr where inr.login_name=?))";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1,"admin");
                pstm.setString(2,"Main Admin");
                pstm.setString(3,"admin");
                pstm.setString(4,"Admin");
                pstm.setString(5,"admin");
                pstm.executeUpdate();
                conn.commit();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}//end of QuizHelper
