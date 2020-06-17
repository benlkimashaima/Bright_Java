/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import DataBase.DataSource;
import Services.serviceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Med Amir Jday
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtpseudo;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnconnexion;
    
    serviceUtilisateur su=new serviceUtilisateur();
    public static int idUser;
    @FXML
    private AnchorPane loginwindow;
    @FXML
    private Text txtpwdoublie;
    @FXML
    private Button btneffacer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void loginConnexion(MouseEvent event) throws IOException {
        {
        
        String pseudo=txtpseudo.getText();
        String pwd=txtpassword.getText();
        
        Connection conn = DataSource.getInstance().getCnx();
       
        if ((event.getSource() == btnconnexion) ) {
            
        if (txtpseudo.getText().equals("") && txtpassword.getText().equals("")) {
            System.out.println("Le nom d'utilisateur et le mot de passe sont ivalides !!");
            
        } else if (txtpassword.getText().equals("")) {
            System.out.println("Le mot de passe est invalide !!");
            
        } else if (txtpseudo.getText().equals("")) {
            System.out.println("Le nom d'utilisateur est invalide !!");
            TrayNotification notif=new TrayNotification();
                notif.setAnimationType(AnimationType.POPUP);
                notif.setTitle("Erreur");
                notif.setMessage("Mots de passe ou login incorrecte");
                notif.setNotificationType(NotificationType.ERROR);        
    }else {
            try {
                
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM `users` WHERE `username`= '" + pseudo + "' AND `password` = '" + pwd + "'");

                
                    while (rs.next()) {
                        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/Application/Accueil.fxml"));
                        Scene sceneview = new Scene(tableview);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sceneview);
                        window.show();     
                    }
                }catch (SQLException e) {
                System.err.println(e.getMessage());
                }
    
                }
            }
        }
    }

    @FXML
    private void mouseMdpoublie(MouseEvent event) {
    }

    @FXML
    private void effacer(MouseEvent event) {
        if (event.getSource() == btneffacer){
        txtpseudo.setText("");
        txtpassword.setText("");
        }
    }
}