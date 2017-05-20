/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week4_2017;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yu
 */
public class StaffController implements Initializable {

    Stage loginStage;

    @FXML
    private Label UsernameLabel;

    @FXML
    private Label PasswordLabel;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private Button LoginButton;

     @FXML
    private Label errorL;

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        String uname = UsernameTextField.getText();
        String pword = PasswordTextField.getText();

        if (uname.equals("suberstaff") && pword.equals("Access")) {
            Parent root = FXMLLoader.load(getClass().getResource("homestaff.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            errorL.setText("The details you have entered are incorrect,please enter a valid Username and Password");
        }
    }

    @FXML
    private void Back(ActionEvent event) throws IOException  {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
