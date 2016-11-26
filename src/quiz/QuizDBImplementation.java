/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import au.com.bytecode.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Group
 */
public class QuizDBImplementation implements QuizDBDAO {

    Map<String, List<Integer>> responseIndexForQnTypeMap = new LinkedHashMap<String, List<Integer>>();
    Map<String, String> qnTypeQueryMap = new LinkedHashMap<String, String>();
    Map<String, Integer> questionTypeCountMap = new LinkedHashMap<String, Integer>();
    Map<String, List<String>> responseTextMap = new LinkedHashMap<String, List<String>>();
    List<String> qnDifficultyLevel = new ArrayList<String>();
    Map<String, Integer> restrictionForResponseMap = new LinkedHashMap<String, Integer>();

    private final String MC = "MC";
    private final String MA = "MA";
    private final String TF = "TF";
    private final String FIB = "FIB";

    public void setConstantValues() {

        //responseIndexForQnTypeMap
        responseIndexForQnTypeMap.put(MC, Arrays.asList(4, 6, 8, 10));
        responseIndexForQnTypeMap.put(MA, Arrays.asList(4, 6, 8, 10));
        responseIndexForQnTypeMap.put(TF, Arrays.asList(3));

        //questionTypeCountMap
        questionTypeCountMap.put(MC, 11);
        questionTypeCountMap.put(MA, 11);
        questionTypeCountMap.put(FIB, 4);
        questionTypeCountMap.put(TF, 4);

        //responseTextMap
        responseTextMap.put(MC, Arrays.asList("correct", "incorrect"));
        responseTextMap.put(MA, Arrays.asList("correct", "incorrect"));
        responseTextMap.put(TF, Arrays.asList("true", "false"));

        //qnTypeQueryMap
        qnTypeQueryMap.put(MC, "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,CHOICE_1,ANSWER_1,CHOICE_2,ANSWER_2,CHOICE_3,ANSWER_3,CHOICE_4,ANSWER_4) values(?,?,?,?,?,?,?,?,?,?,?)");
        qnTypeQueryMap.put(MA, "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,CHOICE_1,ANSWER_1,CHOICE_2,ANSWER_2,CHOICE_3,ANSWER_3,CHOICE_4,ANSWER_4) values(?,?,?,?,?,?,?,?,?,?,?)");
        qnTypeQueryMap.put(FIB, "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,FIB_ANSWER) values(?,?,?,?)");
        qnTypeQueryMap.put(TF, "Insert into question (QUESTION_TYPE,DIFFICULTY_LEVEL,QUESTION_DESCRIPTION,T_OR_F_ANSWER) values(?,?,?,?)");

        //difficulty level list
        qnDifficultyLevel = Arrays.asList("E", "M", "H");
        restrictionForResponseMap.put(MC, 1);
    }

    public String addQuestions(String fileName) {
        String consolidatedErrorMessage = "";
        ArrayList<String> errorList=new ArrayList<>();
        Date date=new Date();
        String erroLogName="ErrorLog.txt";
        try {
            Connection con = QuizHelper.setConnection();
            if (con != null) {
                CSVReader csvReader = QuizHelper.loadCSV(fileName);
                setConstantValues();

                String[] nextLine;

                PreparedStatement ps = null;
                try {

                    con.setAutoCommit(false);
                    int lineNumber = 1;

                    while ((nextLine = csvReader.readNext()) != null) {

                        //code to remove empty elements in an array
                        List<String> list = new ArrayList<String>(Arrays.asList(nextLine));
                        list.removeAll(Arrays.asList(null, ""));
                        String[] cleanNextLineArray = list.toArray(new String[list.size()]);

                        String errorMessage = validateCSVRecords(cleanNextLineArray);

                        if (errorMessage.isEmpty()) {
                            if (null != cleanNextLineArray) {
                                String questionType = cleanNextLineArray[0].trim().toUpperCase();
                                cleanNextLineArray[0] = questionType;

                                String questionDifficultyLevel = cleanNextLineArray[1].trim().toUpperCase();
                                cleanNextLineArray[1] = questionDifficultyLevel;

                                String query = qnTypeQueryMap.get(questionType);

                                ps = con.prepareStatement(query);

                                for (int i = 0; i < cleanNextLineArray.length; i++) {
                                    String trimmedString = cleanNextLineArray[i].trim();

                                    List<Integer> responseIndexList = responseIndexForQnTypeMap.get(questionType);

                                    if (responseIndexList != null) {
                                        if (responseIndexList.contains(i)) {
                                            trimmedString = StringUtils.remove(trimmedString, "\"").trim();
                                        }
                                    }

                                    if (trimmedString.equalsIgnoreCase("correct") || trimmedString.equalsIgnoreCase("true")) {
                                        ps.setInt(i + 1, 1);
                                    } else if (trimmedString.equalsIgnoreCase("incorrect") || trimmedString.equalsIgnoreCase("false")) {
                                        ps.setInt(i + 1, 0);
                                    } else {
                                        ps.setString(i + 1, trimmedString.trim());
                                    }
                                }
                                ps.addBatch();
                            }
                            ps.executeBatch();
                        } else {
                           // consolidatedErrorMessage = consolidatedErrorMessage + lineNumber + ": " + errorMessage + "\n";
                            errorList.add(lineNumber + ": " + errorMessage);
                        }
                        lineNumber++;
                    }

                    if (errorList.size()>0) {
                        System.out.println("Following are the exceptions (line number: error message).Please fix the file and try again.");
                        System.out.println(consolidatedErrorMessage);
                        QuizHelper qz=new QuizHelper();
                        
                        qz.FileWriter(erroLogName, errorList);
                        QuizMain.fileLoadMessage="Errors";
                        //ps.executeBatch(); // rollback remaining records
                        con.rollback();

                    } else {
                        ps.executeBatch(); // insert valid records and commit
                        con.commit();
                        QuizMain.fileLoadMessage="Success";
                    }

                } catch (Exception e) {
                    con.rollback();
                    e.printStackTrace();
                    throw new Exception(
                            "Error occured while loading data from file to database."
                            + e.getMessage());
                } finally {
                    if (null != ps) {
                        ps.close();
                    }
                    if (null != con) {
                        con.close();
                    }

                    csvReader.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(QuizDBImplementation.class.getName()).log(Level.SEVERE, null, ex);

        }
        return consolidatedErrorMessage;
    }

    public String validateCSVRecords(String[] nextLine) {

        String errorMessage = "";

        // TODO Auto-generated method stub
        if (nextLine.length != 0) {
            String questionType = nextLine[0].trim().toUpperCase();

            //incorrect question type
            if (!questionTypeCountMap.keySet().contains(questionType)) {
                errorMessage = "Incorrect question type. Shoule be one of the following: " + questionTypeCountMap.keySet();
            } //incorrect question numbers for each type
            else if (questionTypeCountMap.get(questionType) != nextLine.length) {
                errorMessage = "Incorrect number of inputs for the question type - " + questionType + ". Expected: " + (questionTypeCountMap.get(questionType)) + "; Actual: " + nextLine.length;
            } else if (!qnDifficultyLevel.contains(nextLine[1].trim().toUpperCase())) {
                errorMessage = "Incorrect question difficulty level. Should be one of the following: " + qnDifficultyLevel;
            } //incorrect response other than true,false,correct and incorrect
            else {
                List<Integer> responseIndexList = responseIndexForQnTypeMap.get(questionType);

                if (responseIndexList != null) {
                    int responseCountForCorrect = 0;
                    List<String> responseTextList = responseTextMap.get(questionType);
                    for (int listValue : responseIndexList) {
                        String trimmedString = StringUtils.remove(nextLine[listValue], "\"").trim().toLowerCase();
                        if (!responseTextList.contains(trimmedString)) {
                            errorMessage = "Incorrect answer type. Should be one of the following: " + responseTextList;
                            break;
                        } else {
                            if (trimmedString.equalsIgnoreCase("correct")) {
                                responseCountForCorrect++;
                            }
                        }
                    }

                    if (responseTextList.contains("correct")) {
                        if (responseCountForCorrect < 1) {
                            errorMessage = "Atleast one of the options should be marked correct for the question type - " + questionType;
                        } else if (restrictionForResponseMap.get(questionType) != null) {
                            if (restrictionForResponseMap.get(questionType) != responseCountForCorrect) {
                                errorMessage = "Only one of the options can be marked correct for the question type - " + questionType;
                            }
                        }
                    }

                }
            }
        } else {
            errorMessage = "Empty row. Hence skipped.";
        }
        return errorMessage;
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
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void addStudentResults(StudentResults result) {
        String insertQuery = "insert into test_results values(?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = QuizHelper.setConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, result.getLoginName());
            preparedStatement.setInt(2, result.getLodEasyQuestions());
            preparedStatement.setInt(3, result.getLodMediumQuestions());
            preparedStatement.setInt(4, result.getLodHardQuestions());
            preparedStatement.setInt(5, result.getTotalQuestions());
            preparedStatement.setInt(6, result.getLodEasyCorrect());
            preparedStatement.setInt(7, result.getLodMediumCorrect());
            preparedStatement.setInt(8, result.getLodHardCorrect());
            preparedStatement.setInt(9, result.getSkippedQuestions());
            java.util.Date utilDate = result.getTestDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(10, sqlDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int questionCount(String difficultyLevel) {
        int countRows = 0;
        String query;
        if(difficultyLevel.equalsIgnoreCase("Mixed")){  // check input
            query = "SELECT COUNT(*) AS NUMQUESTIONS FROM QUESTION";
        }
        else{
            query = "SELECT COUNT(*) AS NUMQUESTIONS FROM QUESTION WHERE DIFFICULTY_LEVEL=\'" + difficultyLevel.substring(0,1) + "\'"; //'E'";
        }
        try{
            Connection conn = QuizHelper.setConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            //System.out.println("before reading the resultset");
            countRows = rs.getInt("NUMQUESTIONS");
            return countRows;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return countRows;
    }

    public ArrayList<Question> selectQuestions(int numOfQuestions, String difficultyLevel) {
        
        //System.out.println("In the dao method");
        ArrayList<Question> questionList = new ArrayList<Question>();
        String query = "";
        if (difficultyLevel.equalsIgnoreCase("Mixed")){ // check input
            query = "SELECT * FROM QUESTION ORDER BY RANDOM() FETCH FIRST " + numOfQuestions + " ROWS ONLY";
        } else{
            difficultyLevel = difficultyLevel.substring(0, 1).toUpperCase();
            query = "SELECT * FROM QUESTION WHERE DIFFICULTY_LEVEL= \'" + difficultyLevel+ "\' ORDER BY RANDOM() FETCH FIRST " + numOfQuestions + " ROWS ONLY";
        }
        try{
            Connection conn = QuizHelper.setConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                String questionType = rs.getString("QUESTION_TYPE");
                String description = readClob(rs.getClob("QUESTION_DESCRIPTION"));
                String difficulty = rs.getString("DIFFICULTY_LEVEL");
                //System.out.println(questionType);
                if (questionType.equalsIgnoreCase("MA") || questionType.equalsIgnoreCase("MC")){
                    String choice1 = rs.getString("CHOICE_1");
                    String choice2 = rs.getString("CHOICE_2");
                    String choice3 = rs.getString("CHOICE_3");
                    String choice4 = rs.getString("CHOICE_4");
                    boolean ans1 = rs.getBoolean("ANSWER_1");
                    boolean ans2 = rs.getBoolean("ANSWER_2");
                    boolean ans3 = rs.getBoolean("ANSWER_3");
                    boolean ans4 = rs.getBoolean("ANSWER_4");
                    Question q = new MultiChoiceQuestion(questionType, difficulty, description, choice1, choice2, choice3, choice4, ans1, ans2, ans3, ans4);
                    questionList.add(q);
                }else if (questionType.equalsIgnoreCase("TF")){
                    boolean ans = rs.getBoolean("T_OR_F_ANSWER");
                    Question q = new TrueOrFalseQuestion(questionType, difficulty, description, ans);
                    questionList.add(q);
                }else if (questionType.equalsIgnoreCase("FIB")){
                    String ans = rs.getString("FIB_ANSWER");
                    Question q = new FillInTheBlanks(questionType, difficulty, description, ans);
                    questionList.add(q);
                }else{
                   //String x = questionType;
                   //System.out.println(x);
                   //System.out.println(questionType.charAt(1));
                   //System.out.println(x.equalsIgnoreCase("MC"));
                   //System.out.println(x.length());
                }
                

            }
            //System.out.println(questionList.size());
        }catch(SQLException e){
            
        } catch (IOException ex) {   
            Logger.getLogger(QuizDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }

    public User selectUser(String loginName, String password) {
        User user = null;
        System.out.println("loginName"+loginName+"password"+password);
        String query = "SELECT * FROM USERS WHERE LOGIN_NAME = '" + loginName + "' AND PASSWORD = '" + password + "'"; 
        try{
            Connection conn = QuizHelper.setConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                String userName = rs.getString("USER_NAME");
                String uniRole = rs.getString("UNI_ROLE");
                 user = new User(loginName, userName, password, uniRole);
            }
        }catch(SQLException e){
        }
        return user;
    }
    
        public boolean selectUser(String loginName) {
        User user = null;
        boolean returnFlag=true;//default flag
        System.out.println("loginName"+loginName);
        String query = "SELECT * FROM USERS WHERE LOGIN_NAME = '" + loginName+"'"; 
            System.out.println("query:"+query);
        try{
            Connection conn = QuizHelper.setConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                //set the flag to false if data exists
                System.out.println("data present");
                returnFlag=false;
            }
            
           
        }catch(SQLException e){
            
        }
            System.out.println("returnflag" +returnFlag);
        return returnFlag;
    }

    public ArrayList<StudentResults> getStudentResults() {
        ArrayList<StudentResults> results = new ArrayList<StudentResults>();
        String query = "\"SELECT * FROM TEST_RESULTS";
        try{
            Connection conn = QuizHelper.setConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                String loginName = rs.getString("LOGIN_NAME");
                int lodEasyQuestions = rs.getInt("LOD_EASY_QUESTIONS");
                int lodMediumQuestions = rs.getInt("LOD_MEDIUM_QUESTIONS");
                int lodHardQuestions = rs.getInt("LOD_HARD_QUESTIONS");
                int totalQuestions = rs.getInt("TOTAL_QUESTIONS");
                int lodEasyCorrect = rs.getInt("LOD_EASY_CORRECT");
                int lodMediumCorrect = rs.getInt("LOD_MEDIUM_CORRECT");
                int lodHardCorrect = rs.getInt("LOD_HARD_CORRECT");
                int skipped = rs.getInt("SKIPPED_QUESTIONS");
                int totalCorrect = lodEasyCorrect + lodMediumCorrect + lodHardCorrect;
                Date testDate = rs.getDate("TEST_TAKEN_DATE");
                StudentResults result = new StudentResults(loginName, lodEasyQuestions, lodMediumQuestions, lodHardQuestions, totalQuestions, totalCorrect, lodEasyCorrect, lodMediumCorrect, lodHardCorrect, skipped, testDate);
                results.add(result);
            }
        }catch(SQLException e){
            
        }
        
        return results;
    }
    
    public int[] getQuestionTypes(ArrayList<Question> questions) {
        int arrayqQuestionType[] = new int[4];
        //array will be [no.of MC, MA, T or F, FIB]
        int mc = 0;
        int ma = 0;
        int tf = 0;
        int fib = 0;
        for(Question q : questions){
            String type = q.getQuestionType();
            if (type.equalsIgnoreCase("MC")){
                mc += 1;
            }else if (type.equalsIgnoreCase("MA")){
                ma += 1;
            }else if (type.equalsIgnoreCase("TF")){
                tf += 1;
            }else if (type.equalsIgnoreCase("FIB")){
                fib +=1;
            }   
        }
        arrayqQuestionType[0] = mc;
        arrayqQuestionType[1] = ma;
        arrayqQuestionType[2] = tf;
        arrayqQuestionType[3] = fib;
        return arrayqQuestionType;
    }
    
    public String readClob (Clob c) throws SQLException, IOException{
        StringBuffer sb = new StringBuffer((int)c.length());
        Reader reader = c.getCharacterStream();
	char[] charBuff = new char[1000];
	int n = 0;
	while ((n = reader.read(charBuff, 0, charBuff.length)) != -1) {
		if (n > 0) {
			sb.append(charBuff, 0, n);
		}
	}
	return sb.toString();        
    }
}
