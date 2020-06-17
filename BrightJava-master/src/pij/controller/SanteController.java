/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.gn.lab.GNButton;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pij.entity.Medecin;
import pij.entity.Specialite;
import pij.services.ServiceMedecin;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pij.controller.sendmail2;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class SanteController implements Initializable {

    @FXML
    private TableView<Medecin> tablemedecin;

    @FXML
    private TableColumn<Medecin, String> colnom;
    @FXML
    private TableColumn<Medecin, String> colprenom;
    @FXML
    private TableColumn<Medecin, String> coltel;
    @FXML
    private TableColumn<Medecin, Integer> colspec;
    @FXML
    private TableColumn<Medecin, String> colemail;

    @FXML
    private JFXTextField txtprenom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXTextField txtspec;
    @FXML
    private JFXTextField txttel;
    @FXML
    private JFXTextField txtnom;
    @FXML
    private Button btn_ajouter_med;
    @FXML
    private Button btn_modifer_med;
    @FXML
    private Button btn_supprimer_med;
    @FXML
    private Button btn_vider_med;

    ServiceMedecin d = new ServiceMedecin();
    List<Medecin> listD = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(SanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficher() throws SQLException {

        listD = d.afficherMedecin();
        ObservableList<Medecin> listDem = FXCollections.observableArrayList(listD);
        colnom.setCellValueFactory(new PropertyValueFactory<>("nonMedecin"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenomMedecin"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colspec.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tablemedecin.setItems(listDem);

    }

    @FXML
    private void choisirMed(MouseEvent event) {
        Medecin d2 = tablemedecin.getSelectionModel().getSelectedItem();
        txtnom.setText(String.valueOf(d2.getNonMedecin()));
        txtprenom.setText(String.valueOf(d2.getPrenomMedecin()));
        txttel.setText(String.valueOf(d2.getTelephone()));
        txtspec.setText(String.valueOf(d2.getSpecialite()));
        txtemail.setText(String.valueOf(d2.getEmail()));

    }

    @FXML
    private void ajouterMed(MouseEvent event) throws SQLException {
        try {
            Medecin d1 = new Medecin(String.valueOf(txtnom.getText()), String.valueOf(txtprenom.getText()), String.valueOf(txttel.getText()), Integer.valueOf(txtspec.getText()), String.valueOf(txtemail.getText()));
            d.ajouterMedecin(d1);
            
            String adressemail = colemail.getText();
            sendmail2.sendmail2(adressemail);
            
            System.out.println("Ajouté a");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("done");
            alert.setContentText("Ajouté ");
            afficher();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierMed(MouseEvent event) throws SQLException {
        Medecin d2 = tablemedecin.getSelectionModel().getSelectedItem();

        System.out.println(d2.getNonMedecin());
        System.out.println(d2.getPrenomMedecin());
        System.out.println(d2.getTelephone());
        System.out.println(d2.getSpecialite());
        System.out.println(d2.getEmail());

        d.modifierMedecin(d2.getId(), String.valueOf(txtnom.getText()), String.valueOf(txtprenom.getText()), String.valueOf(txttel.getText()), Integer.valueOf(txtspec.getText()), String.valueOf(txtemail.getText()));
        afficher();
    }

    @FXML
    private void supprimerMed(MouseEvent event) throws SQLException {
        ObservableList<Medecin> SelectedRows, allpeople;
        allpeople = tablemedecin.getItems();
        SelectedRows = tablemedecin.getSelectionModel().getSelectedItems();

        for (Medecin r1 : SelectedRows) {
            allpeople.remove(r1);
            d.supprimerMedecin(r1.getId());
        }
    }

    @FXML
    private void viderMed(MouseEvent event) {
        if (event.getSource() == btn_vider_med) {

            txtnom.setText("");
            txtprenom.setText("");
            txttel.setText("");
            txtspec.setText("");
            txtemail.setText("");
        }
    }

}
