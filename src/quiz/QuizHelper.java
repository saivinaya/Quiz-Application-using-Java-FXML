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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Group
 */
public class QuizHelper {

    public static char delimiter = ',';

    // method1: read file and return a string array list of records in the file
    public ArrayList<String> getFileData(String FileName) {
        ArrayList<String> fileList = null;

        return fileList;
    }
    // method2: takes string array list and  write to pdf file

    public void printToPDF(ArrayList<String> fileList) {

    }
    // method3: take input string and validate the input data based on requirement.  use regexpressions for the same

    public boolean validateInputString(String input, String type) {
        boolean isCorrect = true;

        return isCorrect;
    }

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
            if (q.isSkipQuestion()) {
                skipped += 1;
            } else if (q.validateAnswer()) {
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
     *
     * @param fileName 
     * @param1 Name of the file to print
     * @param appendIndicator 
     * @throws FileNotFoundException 
     * @throws IOException 
     * @param2 Content to be printed
     * @param1 Name of the file to print
     * @param content 
     */
    public void FileWriter(String fileName, ArrayList<String> content) throws FileNotFoundException, IOException {
      //  File file = new java.io.File(fileName);
        Path out = Paths.get(fileName);
          
        Files.write(out,content,Charset.defaultCharset(),StandardOpenOption.CREATE);
        
     }//end of method FileWriter
}
