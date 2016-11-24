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
    
    public int[] evaluateQuizResult(ArrayList<Question> questionlist) {
        int[] result = {0,0,0,0,0};
        // result array shoud have {total questions, easy correct, medium correct, hard corrrect, skipped questions} in that order
        return result;
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
