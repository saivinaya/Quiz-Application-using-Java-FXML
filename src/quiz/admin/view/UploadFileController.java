package quiz.admin.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import quiz.QuizMain;

/**
 * UploadFileController: This class is used while uploading files to database
 *
 * @author Hari
 */
public class UploadFileController implements Initializable {

    private QuizMain application;
    String selectedFileAbsolutePath = null;
    String selectedFileName = null;
    @FXML
    private TextField fileName;

    /**
     * Initializes the controller class.
     *
     * @param application
     */
    public void setApp(QuizMain application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onClickChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileName.setText(selectedFile.getAbsolutePath());
            selectedFileName = selectedFile.getName();
            selectedFileAbsolutePath = selectedFile.getAbsolutePath();
        } else {
        }

    }

    @FXML
    private void onClickBackButton(ActionEvent event) {
        application.gotoAdminDashboard();
    }

    @FXML
    private void onClickFileUpload(ActionEvent event) {

        try {
            if (fileName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select the file to upload");
                alert.showAndWait();
            } else {
                application.uploadQuestions(selectedFileAbsolutePath);
            }
        } catch (Exception exp) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error in File Upload. Please check if the file is in correct format");
            alert.showAndWait();
        }
        if (QuizMain.fileLoadMessage.equals("Errors")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File " + selectedFileName + " has errors. Please cross check if the format is correct");
            alert.showAndWait();
            fileName.setText(null);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("File " + selectedFileName + " has been successfully Uploaded");
            alert.showAndWait();
            fileName.setText(null);
        }

    }

    @FXML
    private void logout(ActionEvent event) {
        application.logoutAccount();
    }

}//end of  UploadFileController
