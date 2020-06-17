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
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Med Amir Jday
 */
public class GestionDonController implements Initializable {

    @FXML
    private TableView<Don> tabledon;
    @FXML
    private TableColumn<Don, Integer> coliddon;
    @FXML
    private TableColumn<Don, Double> colmontant;
    @FXML
    private TableColumn<Don, Date> coldate;
    @FXML
    private TextField montant;
    
    @FXML
    private ImageView imgdeconnexion;
    @FXML
    private ImageView imgretour;
    
    @FXML
    private Button btnajouter;
    @FXML
    private Button effacer;
   
    serviceDon d=new serviceDon(); 
    List<Don> listD=new ArrayList<>();
    @FXML
    private Button btnrechercher;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrechercherdon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(GestionDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    
    private void afficher() throws SQLException
    {
        
        listD =d.afficherDon();
        ObservableList <Don> listDon=FXCollections.observableArrayList(listD);
        coliddon.setCellValueFactory(new PropertyValueFactory<>("idDon") );
        colmontant.setCellValueFactory(new PropertyValueFactory<>("montantDon") );
        coldate.setCellValueFactory(new PropertyValueFactory<>("dateDon") );
        tabledon.setItems(listDon);
        
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
    private void Choisir(MouseEvent event) {
        
        Don d2 =tabledon.getSelectionModel().getSelectedItem();
        montant.setText(String.valueOf(d2.getMontantDon()));
        
    }

    @FXML
    private void modifierDon(MouseEvent event) throws SQLException {
        
        Don d2 =tabledon.getSelectionModel().getSelectedItem();
        System.out.println(d2.getMontantDon());
        d.modifierDon(d2.getIdDon(),Double.valueOf(montant.getText()) );
        
        Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Modification validée !");
       alert.setHeaderText("Information");
         alert.setContentText("Le don est bien modifié");
         alert.showAndWait();
         
        afficher();
        
    }

    @FXML
    private void supprimerDon(MouseEvent event) throws SQLException {
        
        ObservableList<Don> SelectedRows, allpeople;
        allpeople = tabledon.getItems();
        SelectedRows =tabledon.getSelectionModel().getSelectedItems();
     
            for(Don r1:SelectedRows){
            allpeople.remove(r1);
            d.supprimerDon(r1.getIdDon());
            }
            
             Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Suppression validée !");
       alert.setHeaderText("Information");
         alert.setContentText("Le don est bien supprimé");
         alert.showAndWait();
            
            if (event.getSource() == btnsupprimer){
        montant.setText("");
            }
    }

    @FXML
    private void ajouterDon(MouseEvent event) throws SQLException {
        
        Don d1 =new Don(Double.valueOf(montant.getText()));
        d.ajouterDon(d1);
        
           Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Procédure du paiement !");
       alert.setHeaderText("Information ");
         alert.setContentText("Remplir les informations du paiement en ligne !");
         alert.showAndWait();
         
         afficher();
        
         if (event.getSource() == btnajouter){
        montant.setText("");
            }
        
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


    @FXML
    private void effacer(MouseEvent event) {
        if (event.getSource() == effacer){
        montant.setText("");
    }
    }

    @FXML
    private void rechercherDon(MouseEvent event) {
    }
         
}
