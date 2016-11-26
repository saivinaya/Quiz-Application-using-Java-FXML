/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.student.result;

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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import quiz.QuizMain;


/**
 *
 * @author Kuhu
 */
public class NoOfTestTakenController implements Initializable {

    private QuizMain application;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (StudentStatsController.selectedReport.equals("No Of Tests Taken")) {

            barChart.getData().add(viewNoOfTests());
        } else if (StudentStatsController.selectedReport.equals("Average Student Scores")) {

            barChart.getData().add(viewAvgScores());
        } else if (StudentStatsController.selectedReport.equals("Scores by LOD")) {

            barChart.getData().add(scoresByLOD());
        } else {
            barChart.getData().add(passAndFail());
        }

        System.out.println("sjdnfsjnc");

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

        if (StudentStatsController.selectedPeriod.equals("Last Month")) {
            statsLabel.setText("Average score of students based on LOD for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", StudentStatsController.avgEasyCorrect[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", StudentStatsController.avgMedCorrect[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", StudentStatsController.avgHardCorrect[0]));
            series1.getData().add(new XYChart.Data<String, Number>("All", StudentStatsController.avgStudentScores[0]));
        } else if (StudentStatsController.selectedPeriod.equals("Last Quarter")) {
            statsLabel.setText("Average score of students based on LOD for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", StudentStatsController.avgEasyCorrect[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", StudentStatsController.avgMedCorrect[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", StudentStatsController.avgHardCorrect[1]));
            series1.getData().add(new XYChart.Data<String, Number>("All", StudentStatsController.avgStudentScores[1]));
        } else {
            statsLabel.setText("Average score of students based on LOD for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Easy", StudentStatsController.avgEasyCorrect[2]));
            series1.getData().add(new XYChart.Data<String, Number>("Medium", StudentStatsController.avgMedCorrect[2]));
            series1.getData().add(new XYChart.Data<String, Number>("Hard", StudentStatsController.avgHardCorrect[2]));
            series1.getData().add(new XYChart.Data<String, Number>("All", StudentStatsController.avgStudentScores[2]));
        }

        return series1;
    }

    protected XYChart.Series<String, Number> passAndFail() {

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Pass/Fail data");
        if (StudentStatsController.selectedPeriod.equals("Last Month")) {
            statsLabel.setText("Total Pass/Fail students for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", StudentStatsController.passing[0]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", StudentStatsController.failing[0]));
        } else if (StudentStatsController.selectedPeriod.equals("Last Quarter")) {
            statsLabel.setText("Total Pass/Fail students for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", StudentStatsController.passing[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", StudentStatsController.failing[0]));
        } else {
            statsLabel.setText("Total Pass/Fail students for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Pass", StudentStatsController.passing[1]));
            series1.getData().add(new XYChart.Data<String, Number>("Fail", StudentStatsController.failing[0]));
        }

        return series1;
    }

    public XYChart.Series<String, Number> viewNoOfTests() {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Number of tests taken");
        if (StudentStatsController.selectedPeriod.equals("Last Month")) {
            statsLabel.setText("Number of tests taken in last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last month", StudentStatsController.noOfTestsTaken[0]));
        } else if (StudentStatsController.selectedPeriod.equals("Last Quarter")) {
            statsLabel.setText("Number of tests taken in last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken last quarter", StudentStatsController.noOfTestsTaken[1]));
        } else {
            statsLabel.setText("Number of tests taken in last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Tests taken Last Year", StudentStatsController.noOfTestsTaken[2]));
        }

        return series1;
    }

    public XYChart.Series<String, Number> viewAvgScores() {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Average Scores");
        if (StudentStatsController.selectedPeriod.equals("Last Month")) {
            statsLabel.setText("Average score of students for last one month");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for last month", StudentStatsController.avgStudentScores[0]));
        } else if (StudentStatsController.selectedPeriod.equals("Last Quarter")) {
            statsLabel.setText("Average score of students for last one quarter");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for last quarter", StudentStatsController.avgStudentScores[1]));
        } else {
            statsLabel.setText("Average score of students for last one year");
            series1.getData().add(new XYChart.Data<String, Number>("Average scores for Last Year", StudentStatsController.avgStudentScores[2]));
        }

        return series1;
    }

}
