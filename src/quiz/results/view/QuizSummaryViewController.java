/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.results.view;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import quiz.QuizMain;

/**
 * This class is the controller class for the QuizSummaryView fxml page; it has
 * initialize() method, setApp() is used to set the values to the labels and
 * also to build the pie chart to represent the data
 *
 * @author VinayaSaiD
 */
public class QuizSummaryViewController implements Initializable {

    private QuizMain application;

    @FXML
    private Label totalQuestions;
    @FXML
    private Label easyCorrect;
    @FXML
    private Label mediumCorrect;
    @FXML
    private Label hardCorrect;
    @FXML
    private Label totalScore;
    @FXML
    private Label testResult;
    @FXML
    private Label skipped;
    @FXML
    private Label wrongAnswer;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button backResultDashboard;
    @FXML
    private Pane resultFrame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setApp(QuizMain application, int[] rltArray, boolean flag) {
        this.application = application;
        backResultDashboard.setVisible(flag);
        // to set the values from the array to labels to display the results on the screen
        totalQuestions.setText(Integer.toString(rltArray[0]));
        easyCorrect.setText(Integer.toString(rltArray[1]));
        mediumCorrect.setText(Integer.toString(rltArray[2]));
        hardCorrect.setText(Integer.toString(rltArray[3]));
        skipped.setText(Integer.toString(rltArray[4]));
        // coputes the score based on correct answers 
        double score = rltArray[1] + rltArray[2] + rltArray[3];
        double wrong = rltArray[0] - (score + rltArray[4]);
        totalScore.setText(Integer.toString((int) score));
        wrongAnswer.setText(Integer.toString((int) wrong));
        // passed if the student gets 40% marks
        if ((double) (score / rltArray[0]) > 0.4) {
            testResult.setText("Passed!");
        } else if ((double) (score / rltArray[0]) < 0.4) {
            testResult.setText("Failed!");
        } else {
            testResult.setText("Cannot Compute!");
        }
        // this is used to depict the previous data in a pie chart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Easy Questions Answered Correctly", rltArray[1]),
                        new PieChart.Data("Medium Questions Answered Correctly", rltArray[2]),
                        new PieChart.Data("Hard Questions Answered Correctly", rltArray[3]),
                        new PieChart.Data("Questions Skipped", rltArray[4]),
                        new PieChart.Data("Wrong Answers", (int) wrong));
        pieChart.setData(pieChartData);
    }

    @FXML
    private void goBackDashboard(ActionEvent event) {
        // goto the main dashboard 
        application.gotoStudentDashboard();
    }

    @FXML
    private void goResultDashboard(ActionEvent event) {
        // go to the students result dashboard
        application.gotoStudentResultDashboard();
    }

    @FXML
    private void logout(ActionEvent event) {
        // call the logoutAccount() to logout of the application
        application.logoutAccount();
    }

    @FXML
    public void saveToPdf(ActionEvent e) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File file = chooser.showSaveDialog(application.stage);
        if (file != null) {
            try {
                // take the snapshot of the result frame and add it to the pdf
                WritableImage img = resultFrame.snapshot(null, null);
                ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
                com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

                PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document doc = new Document(pdfDoc);
                doc.add(pdfImg);
                doc.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}
