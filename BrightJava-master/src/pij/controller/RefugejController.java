/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import pij.entity.Campement;
import pij.entity.Refugie;
import pij.services.campCrud;
import pij.services.refugieCrud;
import pij.utils.connectionDB;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class RefugejController implements Initializable {
    ObservableList<Refugie> Rlist  = FXCollections.observableArrayList();
    ObservableList<String> genderList  = FXCollections.observableArrayList();
    ObservableList<String> situationList  = FXCollections.observableArrayList();
    @FXML
    private TableView<Refugie> table_R;
    @FXML
    private TableColumn<Refugie, String> nom;
    @FXML
    private TableColumn<Refugie, String> prenom;
    @FXML
    private TableColumn<Refugie, String> nationality;
    @FXML
    private TableColumn<Refugie, Date> birthLoc;
    @FXML
    private TableColumn<Refugie, String> socialSit;
    @FXML
    private JFXTextField nom_txt;
    @FXML
    private JFXTextField prenom_txt;
    @FXML
    private JFXTextField nationality_txt;
    @FXML
    private JFXTextField birthLoc_txt;
    @FXML
    private JFXDatePicker birthD_txt;
    @FXML
    private JFXComboBox<String> sexe_txt;
    @FXML
    private JFXComboBox<String> social_txt;
    @FXML
    private JFXButton ajouter_R1;
    @FXML
    private JFXButton modifier_R;
    @FXML
    private JFXButton supprimer_R;

    refugieCrud rC = new refugieCrud();
    campCrud cC = new campCrud();
    @FXML
    private JFXComboBox<String> camp_txt;
    @FXML
    private TableColumn<Refugie, String> camp;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private JFXButton upload_img;
    @FXML
    private ImageView imgView;
    @FXML
    private ImageView R_img;
    @FXML
    private JFXTextField path_txt;
    @FXML
    private JFXButton scroll_down_btn;
    @FXML
    private JFXButton scroll_up_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   modifier_R.setDisable(true);
      //  supprimer_R.setDisable(true);
        try {
            // TODO
            update_ObList();
            genderList.add("Homme");
            genderList.add("Femme");
            genderList.add("Autre");
            sexe_txt.setItems(genderList);
            situationList.add("Célibataire");
            situationList.add("En relation");
            situationList.add("Veuf.ve");
            social_txt.setItems(situationList);
            camp_txt.setItems(cC.getCampList());
        } catch (SQLException ex) {
            Logger.getLogger(RefugejController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    @FXML
    private void get_data(MouseEvent event) throws IOException {
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           Refugie c = (Refugie)table_R.getSelectionModel().getSelectedItem();
           System.out.println(c);
          
          nom_txt.setText(String.valueOf(c.getNom()));
          sexe_txt.setValue(String.valueOf(c.getSexe()));
          birthD_txt.setValue(LOCAL_DATE(c.getBirthD().toString()));
          birthLoc_txt.setText(String.valueOf(c.getBirthLoc()));
          social_txt.setValue(String.valueOf(c.getSocialSit()));
          nationality_txt.setText(String.valueOf(c.getNationality()));
          camp_txt.setValue(String.valueOf(c.getCamp()));
          prenom_txt.setText(String.valueOf(c.getPrenom()));
          prenom_txt.setDisable(true);
         sexe_txt.setDisable(true);
         birthD_txt.setDisable(true);
         birthLoc_txt.setDisable(true);
         nationality_txt.setDisable(true);
         nom_txt.setDisable(true);
          ajouter_R1.setDisable(true);
          modifier_R.setDisable(false);
          supprimer_R.setDisable(false);
          path_txt.setDisable(true);
          BufferedImage bf;
          String path = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BrightJava-master\\src\\pij\\views\\ressources\\R_images\\"+ rC.getImgPath(c.getNom());
          System.out.println(path);
           bf = ImageIO.read(new File(path));
                
                Image image = SwingFXUtils.toFXImage(bf, null);
                R_img.setImage(image);
                imgView.setImage(image);
    }

    @FXML
    private void exit_btn(MouseEvent event) {
    }

    @FXML
    private void enter_btn(MouseEvent event) {
    }

    @FXML
    private void ajouter_R(ActionEvent event) {
     // String lib =  camp_txt.getValue();
      if (nom_txt.getText().trim().isEmpty() || prenom_txt.getText().trim().isEmpty() || nationality_txt.getText().trim().isEmpty() || sexe_txt.getValue().trim().isEmpty() || birthLoc_txt.getText().trim().isEmpty()) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Erreur lors de l'ajout , informations manquantes !");
            alert.showAndWait();
                    }
                    else {
                                
                       // int capacity = Integer.parseInt(capacity_txt.getText());
         Refugie R = new Refugie(nom_txt.getText(), prenom_txt.getText(),nationality_txt.getText(),path_txt.getText(),java.sql.Date.valueOf(birthD_txt.getValue()), birthLoc_txt.getText(), sexe_txt.getValue(), camp_txt.getValue(), social_txt.getValue());
           
           int status = rC.addRefugie(R);
                                System.out.println(R);
                                System.out.println(status);
                      if (status > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            update_ObList();
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
         //  Clist.clear();
           update_ObList();
            alert.setTitle("Add Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié ajouté avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié NON ajouté!");
            alert.showAndWait();
        }
                    }
    }

    @FXML
    private void modifier_R(ActionEvent event) {
         Refugie R = new Refugie(nom_txt.getText(), prenom_txt.getText(),nationality_txt.getText(), "img",java.sql.Date.valueOf(birthD_txt.getValue()), birthLoc_txt.getText(), sexe_txt.getValue(), camp_txt.getValue(), social_txt.getValue());
         int pst = rC.updateRefugie(R);
         if (pst > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
           
           update_ObList();
            alert.setTitle("Update Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié modifié avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié NON modifié!");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimer_R(ActionEvent event) {
       // Refugie R = new Refugie(nom_txt.getText(), prenom_txt.getText(),nationality_txt.getText(), "img",java.sql.Date.valueOf(birthD_txt.getValue()), birthLoc_txt.getText(), sexe_txt.getValue(), camp_txt.getValue(), social_txt.getValue());
         int pst = rC.deleteRefugie(nom_txt.getText());
         if (pst > 0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            
//              // get a handle to the stage
//    Stage stage = (Stage) ajout_C.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//         
           
           update_ObList();
            alert.setTitle("supression Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié supprimé avec succès !");
            alert.showAndWait();
            
        }else {
             Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("supression Réfugié");
            alert.setHeaderText("information!");
            alert.setContentText("Réfugié NON supprimé!");
            alert.showAndWait();
    }
    }
    
    private void update_ObList(){
        Rlist.clear();
          try{
                        Connection con=connectionDB.getInstance().getCnx();
                   
ResultSet rs =con.createStatement().executeQuery("SELECT * FROM refuge ");
//ResultSet rs2 =con.createStatement().executeQuery("SELECT * FROM don ");

while (rs.next()){
    
    Rlist.add(new Refugie(rs.getString("nom"), rs.getString("prenom"),rs.getString("nationalite"), rs.getString("img"), rs.getDate("birthD"), rs.getString("birthLoc"), rs.getString("sexe") , cC.getLib(rs.getString("camp")), rs.getString("socialSit")));
                        System.out.println(Rlist);
                    }

          }catch (Exception ex) {
            Logger.getLogger(Campement.class.getName()).log(Level.SEVERE, null, ex);
        }    
                  
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));     
       nationality.setCellValueFactory(new PropertyValueFactory<>("nationality")); 
       birthLoc.setCellValueFactory(new PropertyValueFactory<>("birthD"));
       socialSit.setCellValueFactory(new PropertyValueFactory<>("socialSit"));
       camp.setCellValueFactory(new PropertyValueFactory<>("camp"));
       table_R.setItems(Rlist);

    }

    @FXML
    private void upload_img(ActionEvent event) {
           FileChooser fc = new FileChooser();
           FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
           FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
           fc.getExtensionFilters().addAll(ext1,ext2);
           File file = fc.showOpenDialog(scroll_down_btn.getScene().getWindow());
            BufferedImage bf;
            try {
                bf = ImageIO.read(file);
                
                Image image = SwingFXUtils.toFXImage(bf, null);
                imgView.setImage(image);
                String imgName = file.getName();
                path_txt.setText(nom_txt.getText()+".jpg");
                 String fileName = "C:\\Users\\HP\\Documents\\NetBeansProjects\\BrightJava-master\\src\\pij\\views\\ressources\\R_images\\" + nom_txt.getText()+".jpg";
                 System.out.println(fileName);
                 System.out.println(fc.getExtensionFilters().toString());
                  boolean result = ImageIO.write(bf,"jpg", new File (fileName));
                  System.out.println(result);
             FileInputStream fin = new FileInputStream(file);
                  int len = (int)file.length();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(RefugejController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RefugejController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void scroll_down(ActionEvent event) {
         scrollpane.setVvalue(1.0);
    }

    @FXML
    private void scroll_up(ActionEvent event) {
         scrollpane.setVvalue(0.0);
    }

    @FXML
    private void enable_Ajout(MouseEvent event) {
         if(event.getClickCount() == 2){
                System.out.println("Double clicked");
                  ajouter_R1.setDisable(false);
                  nom_txt.clear();
                  prenom_txt.clear();
                  nationality_txt.clear();
                  path_txt.clear();
                  camp_txt.setValue(null);
                  sexe_txt.setValue(null);
                  birthD_txt.setValue(null);
                  birthLoc_txt.clear();
                  camp_txt.setValue(null);
                 // adresse_txt.setPlaceholder();
                  imgView.setImage(null);
                  supprimer_R.setDisable(true);
                  modifier_R.setDisable(true);
            }
    }
     

     
}
