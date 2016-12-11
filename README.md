# Quiz-Application-using-Java-FXML

The QCAS system essentially allows an instrutor to login and import a text file containing csv data (see sample csv file provided on BB). This csv file contains quiz questions (including code) with answers. Instructors will import quiz questions which will be added to a question bank (a relational database). Instructors will also be able to login to the system and get information about students who have been taking quizzes using the system and print reports to a pdf file. The dashboard will provide charts describing general student performance:
• Number of tests taken during the last month,last quarter and over the last year.
• Average student scores over last month, quarter and year.
• Scores by level of difficulty (each question has a difficulty level – easy, medium and hard).
• Students passing and failing over different periods.
• Any other statistic you might find interesting.
The QCAS system will also allow students to login and take quizzes. Students will have the option to select the number of questions along with the difficulty level (easy, medium, hard or mixed). The system will present quiz questions one at a time. There are four types of questions:
• Multiple choice (only 1 answer)
• Multiple answers
• True/False
• Fill in the blanks (a string)

The system must randomize quiz question order and answer choices each time a quiz is started by the user. At the end of the quiz students should be presented with a report of their performance including charts. Students must also have an option to print the report to a pdf file. Finally student performance must be saved on the QCAS database.
Specific Requirements:
• System should support CSV import even in the presence of commas within values and extra quotes within values. You could use an external library like Commons CSV - http://commons.apache.org/proper/commons-csv/
• Pdf export can be implemented using Apache PDFBox - https://pdfbox.apache.org/
• A well-designed GUI must be included. Java FX is the preferred option and it includes charts and other features.
• Questions that are imported must be added to the database. Student information and quizzes taken (including performance in each quiz) should be recorded in the database). It is preferable to use the database in embedded mode for this project. However testing is easier in network mode.
• The system should have a login screen which supports 2 types of login:
      Instructor (Allowed to import csv and provides information on question bank, show performance of students in a dashboard, produce a report in pdf)
      Student (Take a test, Choice of how many and types of questions, Record answers in the Database, Assess performance, record grade, correct and incorrect answers, produce a report with grade incorrect and correct questions, show one chart)

CSV File and Quiz Question Format:
(see sample on BB)
Column 1 — Enter an abbreviation representing the question format. See the table of question types & abbreviations.
Column 2 — Enter the question description.
Column 3 — For MC,MA: Enter the text of a potential answer. For TF: Enter the answer, either "true" or "false". For FIB: Enter the text before the first blank.
Column 4 — For MC,MA: Enter the validity of the answer, either "incorrect" or "correct". For FIB: Enter the solution to the first blank.
Additional Columns — For MC,MA: Repeat Column 3 & 4 as necessary.

Question Types & Abbreviations      
Abbreviation                        Question Type                       
MC                                  Multiple Choice                     
MA                                  Multiple Answer                     
TF                                  True/False                          
FIB                                 Fill in the Blank
