package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;

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
            // TODO Auto-generated method stub
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Establish Derby connection with QuizDB embedded mode
            String connectionString = "jdbc:derby:QuizDB";
            String username = "app";
            String password = "app";
            conn = DriverManager.getConnection(connectionString, username, password);

            /*            Statement stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData(); // creating metadata object to check if the table already exists;
            ResultSet tables = dbm.getTables(null, null, "QUESTION", null);

            // if table is not present in database then create the table
            if (!tables.next()) {

                stmt.executeUpdate("CREATE TABLE QUESTION( QUESTION_TYPE VARCHAR(30), QUESTION_DESCRIPTION CLOB, DIFFICULTY_LEVEL VARCHAR(10), CHOICE_1 CLOB,CHOICE_2 CLOB,CHOICE_3 CLOB,CHOICE_4 CLOB,ANSWER_1 BOOLEAN,ANSWER_2 BOOLEAN,ANSWER_3 BOOLEAN,ANSWER_4 BOOLEAN,T_OR_F_ANSWER BOOLEAN,FIB_ANSWER VARCHAR(300))");

                System.out.print("\nCreated table Question");
            } else {
                stmt.executeUpdate("truncate table QUESTION");
            }
             */
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

}
