/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;






import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pij.entity.Specialite;
import pij.services.serviceSpecialite;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SpecialiteController implements Initializable {

    @FXML
    private TableView<Specialite> tablespecialite;
    @FXML
    private TableColumn<Specialite, String> colspecialite;
    @FXML
    private JFXTextField txtspecialite;
    @FXML
    private JFXButton btn_ajouter;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton btn_supprimer;
    
     serviceSpecialite d=new serviceSpecialite(); 
    List<Specialite> listD=new ArrayList<>();
    @FXML
    private JFXButton btn_vider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(SpecialiteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
    private void afficher() throws SQLException
    {
        
        listD =d.afficherSpecialite();
        ObservableList <Specialite> listDem=FXCollections.observableArrayList(listD);
        colspecialite.setCellValueFactory(new PropertyValueFactory<>("nomSpecialite") );
 
        tablespecialite.setItems(listDem);
        
    }

    @FXML
    private void choisirSpec(MouseEvent event) {
        Specialite d2 =tablespecialite.getSelectionModel().getSelectedItem();
        txtspecialite.setText(String.valueOf(d2.getNomSpecialite()));
    }

    @FXML
    private void ajouterSpec(MouseEvent event) throws SQLException {
        Specialite d1 =new Specialite(String.valueOf(txtspecialite.getText()));
        d.ajouterSpecialite(d1);
        afficher();
    }

    @FXML
    private void modifierSpec(MouseEvent event) throws SQLException {
         Specialite d2 =tablespecialite.getSelectionModel().getSelectedItem();
        System.out.println(d2.getNomSpecialite());
        d.modifierSpecialite(d2.getId(),String.valueOf(txtspecialite.getText()));
        afficher();
    }

    @FXML
    private void supprimerSpec(MouseEvent event) throws SQLException {
        ObservableList<Specialite> SelectedRows, allpeople;
        allpeople = tablespecialite.getItems();
        SelectedRows =tablespecialite.getSelectionModel().getSelectedItems();
     
            for(Specialite r1:SelectedRows){
            allpeople.remove(r1);
            d.supprimerSpecialite(r1.getId());
            }
    }

    @FXML
    private void vider(MouseEvent event) {
         if (event.getSource() == btn_vider){
        txtspecialite.setText("");
        }
        
    }
    
}

