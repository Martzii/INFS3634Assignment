/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week4_2017;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yu
 */
public class MemberController {

    @FXML
    private Label loginOutput;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pword;

    Database d = new Database();

    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {

        String user = username.getText().trim();
        String password = pword.getText();
        try {
            ResultSet rs = d.getResultSet("SELECT * FROM APP.MEMBER WHERE "
                    + "USERNAME = '" + user + "' "
                    + "AND PASSWORD = '" + password + "'");
            if (!rs.next()) {
                loginOutput.setText("Incorrect username or password");
                loginOutput.setVisible(true);
            } else {
                loginOutput.setText("Login successful");
                loginOutput.setVisible(true);
                Parent root = FXMLLoader.load(getClass().getResource("homemember.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void initialize() {
        loginOutput.setVisible(false);
    }

}
