/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.admin.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import quiz.QuizMain;

/**
 * FXML Controller class
 *
 * @author Hari
 */
public class AdminDashboardController implements Initializable {
   private QuizMain application;
    @FXML
    private TextField userName;
    @FXML
    private Button signIn;
    @FXML
    private Button signIn1;
    @FXML
    private Button signIn11;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loading Admin");
    }    
        public void setApp(QuizMain application){
        this.application = application;
    }
    
    
}
