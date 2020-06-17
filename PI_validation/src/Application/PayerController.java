 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

//import Entites.cmd;
//import Entites.Session;
//import IService.IServiceCmd;
//import IService.IServiceLignecmd;
//import IServices.ISendEmailService;
//import Service.serviceCmd;
//import Service.serviceLigne;
//import Utils.JavaMail;
//import Services.SendEmailService;
//import Utils.JavaMail;
import Entities.Don;
import Services.serviceDon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
;
import com.stripe.Stripe;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.MessagingException;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PayerController implements Initializable {


    @FXML
    private TextField num_carte;

    @FXML
    private TextField mois_expiration;

    @FXML
    private TextField annee_expiration;

    @FXML
    private TextField cvc;

    @FXML
    private Label total;
    
    @FXML
    private Button payer_commande;
public static double total_cmd;
int cmd_id=2;
//public static int cmd_id;
    String email;
 String carNumber;
 String cvvc;
 String expm;
 String expy;
 String total2;
 String idcu;
 
  Map <Integer,String> ListeCustomer = new HashMap<Integer,String>();
  
  int id_user=1; //Session.getCurrentSession().getId();
  
  
 
 private serviceDon donService;
         private int idDon;
 private Double montantDon;
     private String dateDon;

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Stripe.apiKey ="sk_test_51GupvwH0gcj4egsehVKNHEt098tlb6yTyHV3A11tW5CpZwFUUOy7ldR1zg5kpxB2vDMeyptxAa9Vy3N5IJEX2q0s00OaKznepS";
        donService=new serviceDon();
        Don d=new Don();
         total.setText(122+" Dt");
        
         
    }   
    
   /*  public void initData(Commande c)
    {
    Double p=c.getPrixTotal();
    cmd_id=c.getId();
    String prix_total=Double.toString(p);
    total.setText(prix_total+" DT");
    total_cmd=p;
    }
    */
    /*
    public void initData(cmd c)
    {
    //Double p=c.getPrixTotal();
    cmd_id=3;
    //String prix_total=Double.toString(p);
    total.setText(5+" DT");
    total_cmd=5;
    }
    */
    
    public void initData(Don c)
    {
        int id=c.getIdDon();
    Double p=c.getMontantDon();
    c.setIdDon(id);
    //cmd_id=2;
    //cmd_id=2;//c.getId();
    String prix=Double.toString(p);
    //total.setText(prix_total+" DT");
    //total.setText(prix+" DT");
    total.setText(122 +" Dt");
    total_cmd=p;
    }
     
      @FXML
    void Payer_commande(ActionEvent event) throws StripeException, Exception, AddressException, MessagingException {
      ArrayList<Integer> tab =new ArrayList<Integer>();
      int key=0;
  Stripe.apiKey ="sk_test_51GupvwH0gcj4egsehVKNHEt098tlb6yTyHV3A11tW5CpZwFUUOy7ldR1zg5kpxB2vDMeyptxAa9Vy3N5IJEX2q0s00OaKznepS";
  if ((num_carte.getText().length()==0)||(mois_expiration.getText().length()==0)||(annee_expiration.getText().length()==0)||(cvc.getText().length()==0) )
{
    System.out.println("Il est vide !");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir les champs manquants");
            alert.showAndWait();
 
  } 
else 
{
            try
            {
            Map <String,Object> customerParameter = new HashMap<String,Object>();
//            commandeService = new serviceCmd();
     String mail="mohamedamir.jday@esprit.tn";//Session.getCurrentSession().getEmail();
     //String np=Session.getCurrentSession().getNom()+Session.getCurrentSession().getPrenom();
     String np = "Med Amir Jday"; //Session.getCurrentSession().getUsername();
                System.out.println(mail);
    customerParameter.put("email",mail);
    customerParameter.put("description","Nom & Prénom : "+np);
    Customer newCustomer =Customer.create(customerParameter);
    System.out.println("customer ajouté");
    String idcas=newCustomer.getId();
    Customer a=Customer.retrieve(idcas);
    
    Map <String,Object> cardParam = new HashMap<String,Object>();
  cardParam.put("number",num_carte.getText());
cardParam.put("exp_month",mois_expiration.getText());
cardParam.put("exp_year",annee_expiration.getText());
cardParam.put("cvc",cvc.getText());
  
 Map <String,Object> tokenParam = new HashMap<String,Object>();
 tokenParam.put("card",cardParam);
 Token token=Token.create(tokenParam);
  
  Map <String,Object> source = new HashMap<String,Object>();
 source.put("source",token.getId());
 a.getSources().create(source);
 
 Map <String,Object> chargeParam = new HashMap<String,Object>();
    Don D=new Don();
 double d=D.getMontantDon();
 int prix=(int)d;
 int prix_f=prix*3;
 System.out.println(prix);
 chargeParam.put("amount",D.getMontantDon());
chargeParam.put("currency","eur");
chargeParam.put("customer",a.getId());
Charge.create(chargeParam);
  Gson gson=new GsonBuilder().setPrettyPrinting().create();
  tab.add(0);
            }  
            catch(StripeException e)
    {
                String m=e.getMessage();
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText(m);
            alert.showAndWait();   
               tab.add(1); 
    }
            
 for(int i=0;i<tab.size();i++)
 {
 if(tab.get(i)!=1)
 {
 key=9;
 }
 
 }
 
 if(key==9)
 {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes !");
            alert.setHeaderText(null);
            alert.setContentText("Paiement Effectué avec success");
            alert.showAndWait();   
      
     //commandeService = new serviceCmd();
     String mail="mohamedamir.jday@esprit.tn";//Session.getCurrentSession().getEmail();
     //JavaMail.sendMail(mail);
     //commandeService.ChangerEtatCommandeToPaye(cmd_id);     
     
     String message="Bonjour l'équipe de Bright vous informe que le payement de votre don est : "+122+" DT est effectué avec success ";
     
       
        if ((event.getSource() == payer_commande) ) {
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
}
    }
}
    
    

    
    
    
    
    
    
    
     

