/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Entities.Don;
import Services.serviceDon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Med Amir Jday
 */
public class AccueilController implements Initializable {

    @FXML
    private ImageView imgdemande;
    @FXML
    private ImageView imgdon;
    @FXML
    private ImageView imgutilisateur;
    @FXML
    private ImageView imgdonate;
    @FXML
    private ImageView imgdeconnexion;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    

    @FXML
    private void GDemande(MouseEvent event) {
        if ((event.getSource() == imgdemande) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/GestionDemande.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void GDon(MouseEvent event) {
         if ((event.getSource() == imgdon) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/GestionDon.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void GUtilisateur(MouseEvent event) {
         if ((event.getSource() == imgutilisateur) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/GestionUtilisateur.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void GDonate(MouseEvent event) {
         if ((event.getSource() == imgdonate) ) {
          try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/DonateDirect.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
    }

    @FXML
    private void deconnexion(MouseEvent event) {
        if ((event.getSource() == imgdeconnexion) ) {
          try {
              
               Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Déconnexion !");
       alert.setHeaderText("Information");
         alert.setContentText("Vous êtes déconnecté");
         alert.showAndWait();
         
         
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/Login.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
}
