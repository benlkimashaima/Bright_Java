/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Entities.Demande;
import Services.serviceDemande;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class GestionDemandeController implements Initializable {

    @FXML
    private TextArea txtdescdemande;
    @FXML
    private TextField txttitredemande;
    @FXML
    private TextField txtmontantdemande;
    private TextField txtdatelimite;
    @FXML
    private TableView<Demande> tabledemande;
    @FXML
    private TableColumn<Demande, Integer> coliddemande;
    @FXML
    private TableColumn<Demande, String> coltitredemande;
    @FXML
    private TableColumn<Demande, String> coldescription;
    @FXML
    private TableColumn<Demande, Double> colmontantV;
    @FXML
    private TableColumn<Demande, Date> coldelai;
    @FXML
    private ImageView imgdeconnexion;
    @FXML
    private ImageView imgretour;
    @FXML
    private Button btnrecherchedemande;
    @FXML
    private Button btnajouterdemande;
    @FXML
    private Button btnmodifierdemande;
    @FXML
    private Button btnsupprimerdemande;
    @FXML
    private TextField txtrecherchedemande;
    @FXML
    private DatePicker dpdatelimite;
    @FXML
    private Button btneffacer;
    
    serviceDemande d=new serviceDemande(); 
    List<Demande> listD=new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(GestionDemandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
    
    private void afficher() throws SQLException
    {
        
        listD =d.afficherDemande();
        ObservableList <Demande> listDem=FXCollections.observableArrayList(listD);
        coliddemande.setCellValueFactory(new PropertyValueFactory<>("idDemande") );
        coltitredemande.setCellValueFactory(new PropertyValueFactory<>("titreDem") );
        coldescription.setCellValueFactory(new PropertyValueFactory<>("descDem") );
        colmontantV.setCellValueFactory(new PropertyValueFactory<>("montantDem") );
        coldelai.setCellValueFactory(new PropertyValueFactory<>("delaiFinal") );
        
        tabledemande.setItems(listDem);
        
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
    private void rechercherDemande(MouseEvent event) {
    }

    @FXML
    private void ajouterDemande(MouseEvent event) throws SQLException {
        
        Demande d1 =new Demande(String.valueOf(txttitredemande.getText()),String.valueOf(txtdescdemande.getText()),Double.valueOf(txtmontantdemande.getText()),Date.valueOf(dpdatelimite.getValue()));
        d.ajouterDemande(d1);
        
          Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Ajout validé !");
       alert.setHeaderText("Information");
         alert.setContentText("La demande est bien ajoutée");
         alert.showAndWait();
         
        afficher();
        
          if (event.getSource() == btnajouterdemande){
                txttitredemande.setText("");
                txtdescdemande.setText("");
                txtmontantdemande.setText("");
            }
    }

    @FXML
    private void modifierDemande(MouseEvent event) throws SQLException {
        
        Demande d2 =tabledemande.getSelectionModel().getSelectedItem();
        System.out.println(d2.getTitreDem());
        System.out.println(d2.getDescDem());
        System.out.println(d2.getMontantDem());
        System.out.println(d2.getDelaiFinal());
        d.modifierDemande(d2.getIdDemande(),String.valueOf(txttitredemande.getText()),String.valueOf(txtdescdemande.getText()),Double.valueOf(txtmontantdemande.getText()),String.valueOf(dpdatelimite.getValue()) );
        
         Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Modification validée !");
       alert.setHeaderText("Information");
         alert.setContentText("La demande est bien modifiée");
         alert.showAndWait();
         
        afficher();
    }

    @FXML
    private void supprimerDemande(MouseEvent event) throws SQLException {
        
        ObservableList<Demande> SelectedRows, allpeople;
        allpeople = tabledemande.getItems();
        SelectedRows =tabledemande.getSelectionModel().getSelectedItems();
     
            for(Demande r1:SelectedRows){
            allpeople.remove(r1);
            d.supprimerDemande(r1.getIdDemande());
            }
            
             Alert alert= new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Suppression validée !");
       alert.setHeaderText("Information");
         alert.setContentText("La demande est bien supprimée");
         alert.showAndWait();
         
          if (event.getSource() == btnsupprimerdemande){
                txttitredemande.setText("");
                txtdescdemande.setText("");
                txtmontantdemande.setText("");
            }
    }

    @FXML
    private void choisirDem(MouseEvent event) {
        
        Demande d2 =tabledemande.getSelectionModel().getSelectedItem();
        txttitredemande.setText(String.valueOf(d2.getTitreDem()));
        txtdescdemande.setText(String.valueOf(d2.getDescDem()));
        txtmontantdemande.setText(String.valueOf(d2.getMontantDem()));
        txtdatelimite.setText(String.valueOf(d2.getDelaiFinal()));
        
    }

    @FXML
    private void effacer(MouseEvent event) {
        
        if (event.getSource() == btneffacer){
        txttitredemande.setText("");
        txtdescdemande.setText("");
        txtmontantdemande.setText("");
        }
        
    }
    
}
