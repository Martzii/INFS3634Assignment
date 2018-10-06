/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week4_2017;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yu
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        loadDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/Customer/YourAccount.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Register_class.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    
    private void loadDatabase() {
       Database.createMemberTable();
       Database.createAdministratorTable();
       Database.createAgreementStatusTable();
       Database.createDriverpostTable();
       Database.createAgreementTable();

    }
    
}

    

