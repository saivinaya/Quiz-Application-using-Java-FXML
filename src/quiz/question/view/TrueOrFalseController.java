/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.question.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import quiz.FillInTheBlanks;
import quiz.QuizMain;
import quiz.TrueOrFalseQuestion;

/**
 * FXML Controller class
 *
 * @author VinayaSaiD
 */
public class TrueOrFalseController implements Initializable {
    private QuizMain application;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setApp(QuizMain application, TrueOrFalseQuestion qust){
        this.application = application;
        setup();
    }

    private void setup() {
        
    }
}
