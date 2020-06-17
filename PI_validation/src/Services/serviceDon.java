/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Don;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Med Amir Jday
 */
public class serviceDon {
    
     private static serviceDon instance;
     private Statement st;
    private ResultSet rs;
    
    public serviceDon(){
         DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    Connection conn = DataSource.getInstance().getCnx();
    
    public static serviceDon getInstance(){
        if(instance==null) 
            instance=new serviceDon();
        return instance;
    }
    
    public List<Don> afficherDon() throws SQLException {
        
    List<Don> arr=new ArrayList<>();
    Statement ste=conn.createStatement();
    ResultSet rs=ste.executeQuery("select * from don");
    
        while(rs.next()) {
               
                int idDon=rs.getInt(1);
                Double montantDon=rs.getDouble(2);
                String dateDon=rs.getString(3);
               
                Don a = new Don (idDon,montantDon,dateDon);
                arr.add(a);
        }
         return arr;
    }
    
    public void supprimerDon(int idDon) throws SQLException {
            try { 
            String query ="DELETE FROM don WHERE idDon="+idDon; 
            Statement ste=conn.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("Le don de l id = "+idDon+"a été supprimée ") ; 
            }
                   catch(SQLException ex){
                   System.out.println(ex.getMessage()) ; 
                }
        }
    
//    public void ajouterDon( Don d ) throws SQLException {
//        try{
//        Statement ste = conn.createStatement();
//        String requeteInsert = "INSERT INTO don (   montantDon) VALUES  (   '" + d.getMontantDon() + "');";
//        ste.executeUpdate(requeteInsert);}
//         catch(SQLException e) {
//             System.out.println(e.getMessage());
//        }
//        
//    }
    
    public void ajouterDon( Don d ) throws SQLException {
        try{
           
//         LocalDateTime now = LocalDateTime.now();
//         java.util.Date DateReclamation = java.util.Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
            
         
        Statement ste = conn.createStatement();
        String requeteInsert = "INSERT INTO don (  montantDon,  dateDon) VALUES  ( '" +  d.getMontantDon() + "', '" +LocalDate.now()+ "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
    public void modifierDon(int idDon,Double montantDon) {
       
        try {
           String query ="UPDATE don SET montantDon='"+montantDon+"' WHERE idDon='"+idDon+"'";
           Statement ste=conn.createStatement();
           ste.executeUpdate(query) ;
           System.out.println("Le don bien modifiée");
        } catch (SQLException ex){
           System.out.println(ex.getMessage()) ; 
            }
   }
    
}
