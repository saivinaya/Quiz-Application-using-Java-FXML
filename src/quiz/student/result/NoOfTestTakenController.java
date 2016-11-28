/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.result;

import com.itextpdf.io.font.FontConstants;
import static com.itextpdf.io.font.FontConstants.BOLD;
import static com.itextpdf.io.font.FontConstants.TIMES_BOLD;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import quiz.QuizMain;


/**
 *
 * @author Kuhu
 */
public class NoOfTestTakenController implements Initializable {

    private QuizMain application;
    StudentStatsController stud = new StudentStatsController();
    /**
     * Initializes the controller class.
     */
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Pane Statistics;
    @FXML
    private Button exportAsPDF;
    @FXML
    private Button back;
    @FXML
    private Label statsLabel;
    @FXML
    private Label statsMainLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (StudentStatsController.getSelectedReport().equals("No Of Tests Taken")) {

            barChart.getData().add(viewNoOfTests());
        } else if (StudentStatsController.getSelectedReport().equals("Average Student Scores")) {

            barChart.getData().add(viewAvgScores());
        } else if (StudentStatsController.getSelectedReport().equals("Scores by LOD")) {

            barChart.getData().add(scoresByLOD());
        }else if (StudentStatsController.getSelectedReport().equals("No of Skipped Questions")) {

            barChart.getData().add(viewNoOfSkippedQuestions());
        } else {
            barChart.getData().add(passAndFail());
        }

        

    }

    @FXML
    public void viewStats(ActionEvent e) {
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job != null) {
//            job.showPrintDialog(application.stage); // Window must be your main Stage
//            job.printPage(barChart);
//            job.endJob();
//        }
FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
        
            File file = chooser.showSaveDialog(application.stage);
            if (file != null) {
                try {
                    WritableImage img = barChart.snapshot(null, null);
                    ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
                    com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);
                    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document doc = new Document(pdfDoc);
                    Paragraph preface = new Paragraph();
                    preface.add(statsMainLabel.getText());
                    doc.add(preface.setBold());
                    preface.add(statsLabel.getText());
                    doc.add(preface);
                    
                    doc.add(pdfImg);
                    doc.close();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }               
            }
        
    }

    @FXML
    public void goBack() {
        application.goTOStudentStats();
    }

    public void setApp(QuizMain application) {
        this.application = application;

    }

    private XYChart.Series<String, Number> scoresByLOD() {

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Scores by LOD");

        if (StudentStatsController.getSelectedPeriod().equals("Last Month")) {
            statsLabel.setText("Average score of students based on LOD for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", stud.getAvgEasyCorrect()[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", stud.getAvgMedCorrect()[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", stud.getAvgHardCorrect()[0]));
            
        } else if (StudentStatsController.getSelectedPeriod().equals("Last Quarter")) {
            statsLabel.setText("Average score of students based on LOD for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", stud.getAvgEasyCorrect()[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", stud.getAvgMedCorrect()[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", stud.getAvgHardCorrect()[1]));
            
        } else {
            statsLabel.setText("Average score of students based on LOD for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", stud.getAvgEasyCorrect()[2]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", stud.getAvgMedCorrect()[2]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", stud.getAvgHardCorrect()[2]));
            
        }

        return series1;
    }

    protected XYChart.Series<String, Number> passAndFail() {

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Pass/Fail data");
        if (StudentStatsController.getSelectedPeriod().equals("Last Month")) {
            statsLabel.setText("Total Pass/Fail students for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", stud.getPassing()[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", stud.getFailing()[0]));
        } else if (StudentStatsController.getSelectedPeriod().equals("Last Quarter")) {
            statsLabel.setText("Total Pass/Fail students for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", stud.getPassing()[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", stud.getFailing()[1]));
        } else {
            statsLabel.setText("Total Pass/Fail students for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", stud.getPassing()[2]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", stud.getFailing()[2]));
        }

        return series1;
    }

    public XYChart.Series<String, Number> viewNoOfTests() {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Number of tests taken");
        if (StudentStatsController.getSelectedPeriod().equals("Last Month")) {
            statsLabel.setText("Number of tests taken in last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last month", stud.getNoOfTestsTaken()[0]));
        } else if (StudentStatsController.getSelectedPeriod().equals("Last Quarter")) {
            statsLabel.setText("Number of tests taken in last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last quarter", stud.getNoOfTestsTaken()[1]));
        } else {
            statsLabel.setText("Number of tests taken in last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken Last Year", stud.getNoOfTestsTaken()[2]));
        }

        return series1;
    }
    
    public XYChart.Series<String, Number> viewNoOfSkippedQuestions() {

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
        series1.setName("Number of Skipped Questions");
        if (StudentStatsController.getSelectedPeriod().equals("Last Month")) {
            statsLabel.setText("Number of skipped questions in last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last month", stud.getNumberOfSkippedQuestion()[0]));
        } else if (StudentStatsController.getSelectedPeriod().equals("Last Quarter")) {
            statsLabel.setText("Number of skipped questions in last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last quarter", stud.getNumberOfSkippedQuestion()[1]));
        } else {
            statsLabel.setText("Number of skipped questions in last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken Last Year", stud.getNumberOfSkippedQuestion()[2]));
        }

        return series1;
    }

    public XYChart.Series<String, Number> viewAvgScores() {

      

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Average Scores");
        if (StudentStatsController.getSelectedPeriod().equals("Last Month")) {
            statsLabel.setText("Average score of students for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for last month", stud.getAvgStudentScores()[0]));
        } else if (StudentStatsController.getSelectedPeriod().equals("Last Quarter")) {
            statsLabel.setText("Average score of students for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for last quarter", stud.getAvgStudentScores()[1]));
        } else {
            statsLabel.setText("Average score of students for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for Last Year", stud.getAvgStudentScores()[2]));
        }

        return series1;
    }

}
