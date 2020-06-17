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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Med Amir Jday
 */
public class DonateDirectController implements Initializable {

    @FXML
    private ImageView imgdeconnexion;
    @FXML
    private ImageView imgretour;
    @FXML
    private Button btnajouter;
    
    serviceDon d=new serviceDon(); 
    @FXML
    private TextField montantdirect;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void precedant(MouseEvent event) {
           
         if ((event.getSource() == imgretour) ) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/Accueil.fxml"));
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
    private void ajouterDon(MouseEvent event) throws SQLException {
         Don d1 =new Don(Double.valueOf(montantdirect.getText()));
        d.ajouterDon(d1);
        
        Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Procédure du paiement !");
       alert.setHeaderText("Information ");
         alert.setContentText("Remplir les informations du paiement en ligne !");
         alert.showAndWait();
        
        montantdirect.setText("");
        
         if ((event.getSource() == btnajouter) ) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/Payer.fxml"));
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
