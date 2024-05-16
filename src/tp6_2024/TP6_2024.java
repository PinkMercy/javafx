/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6_2024;

import DAO.DAOMedicament;
import DAO.DAOUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Medicament;
import models.Patient;

/**
 *
 * @author rahma
 */
public class TP6_2024 extends Application {

    @Override
public void start(Stage stage) {
    try {
        ///TableView/TableView
        //FXMLDocument
        Parent root = FXMLLoader.load(getClass().getResource("/TableView/TableView.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        DAOMedicament user = new DAOMedicament();
        Medicament p = new Medicament(1,"bechhhh",54,88,"normal");
        user.updateMed(p); 
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY); //TRANSPARENT
        stage.show();
    } catch (Exception ex) {
        Logger.getLogger(TP6_2024.class.getName()).log(Level.SEVERE, "Error loading the FXML file", ex);
        showAlertAboutLoadingFailure();
    }
}



private void showAlertAboutLoadingFailure() {
    //Show an alert dialog to inform the user
}


    
    /**
     * @param args the command line arguments
     */
    
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/TabeleView/TableView.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(TP6_2024.class.getName()).log(Level.SEVERE, "Exception while loading FXML", ex);
        }

    }*/

    public static void main(String[] args) {
        launch(args);
    }

}
