/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Demande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Med Amir Jday
 */
public class serviceDemande {
    
     private static serviceDemande instance;
     private Statement st;
    private ResultSet rs;
    
    public serviceDemande(){
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        Connection conn = DataSource.getInstance().getCnx();
        
        public List<Demande> afficherDemande() throws SQLException {
            
            List<Demande> arr=new ArrayList<>();
            Statement ste=conn.createStatement();
            ResultSet rs=ste.executeQuery("select * from demande");
    
                while(rs.next()) {
               
                    int idDemande=rs.getInt(1);
                    String titreDem=rs.getString(2);
                    String descDem=rs.getString(3);
                    Double montantDem=rs.getDouble(4);
                    Date delaifinal=rs.getDate(5);
               
                    Demande a = new Demande (idDemande,titreDem,descDem,montantDem,delaifinal);
                    arr.add(a);
                }
            return arr;
         }
        
        public static serviceDemande getInstance(){
        if(instance==null) 
            instance=new serviceDemande();
        return instance;
    }
        
        public void supprimerDemande(int idDemande) throws SQLException {
            try { 
            String query ="DELETE FROM demande WHERE idDemande="+idDemande; 
            Statement ste=conn.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("La demande d'id = "+idDemande+"a été bien supprimée ") ; 
            }
                   catch(SQLException ex){
                   System.out.println(ex.getMessage()) ; 
                }
        }
        
        public void ajouterDemande( Demande d ) throws SQLException {
        try{
        Statement ste = conn.createStatement();
        String requeteInsert = "INSERT INTO demande (titreDem,descDem,montantDem,delaiFinal) VALUES  ( '" + d.getTitreDem() +"','"+d.getDescDem()+"','"+d.getMontantDem()+"','"+d.getDelaiFinal()+ "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
        
        public void modifierDemande(int idDemande,String titreDem,String descDem,Double montantDem,String delaiFinal) {
       
        try {
           String query ="UPDATE demande SET titreDem='"+titreDem+"',descDem='"+descDem+"',montantDem='"+montantDem+"',delaiFinal='"+delaiFinal+"' WHERE idDemande='"+idDemande+"'";
           Statement ste=conn.createStatement();
           ste.executeUpdate(query) ;
           System.out.println("La demande est bien modifiée");
        } catch (SQLException ex){
           System.out.println(ex.getMessage()) ; 
            }
   }
        
        public void chercherTitre(String titreDem) throws SQLException{
         
        }
    
}
