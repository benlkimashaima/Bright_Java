/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pij.entity.Specialite;
import pij.utils.connectionDB;

/**
 *
 * @author DELL
 */
public class serviceSpecialite {
    
    private Connection con;
    private Statement ste;
    
    public serviceSpecialite() {
        con = connectionDB.getInstance().getCnx();
    }
    
    public List<Specialite> afficherSpecialite() throws SQLException {
        
    List<Specialite> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from specialite");
    
     while (rs.next()) {                
         
              int id=rs.getInt(1);
              String nomSpecialite=rs.getString(2);
             
                     
              Specialite a = new Specialite (id,nomSpecialite);
              arr.add(a);
        }
        
        return arr;
    }
    
    public void ajouterSpecialite(Specialite d) throws SQLException
    {
   try{
        Statement ste = con.createStatement();
        String requeteInsert = "INSERT INTO specialite (nomSpecialite) VALUES  ( '" + d.getNomSpecialite()+ "');";
        ste.executeUpdate(requeteInsert);}
         catch(SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
    
        public void supprimerSpecialite(int id) throws SQLException {
           try { 
            String query ="DELETE FROM specialite WHERE id="+id; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("La specialite d'id = "+id+"a été bien supprimée ") ; 
            }
                   catch(SQLException ex){
                   System.out.println(ex.getMessage()) ; 
                }
        }
        
        public void modifierSpecialite(int id,String nomSpecialite) {
       
        try {
           String query ="UPDATE specialite SET nomSpecialite='"+nomSpecialite+"' WHERE id='"+id+"'";
           Statement ste=con.createStatement();
           ste.executeUpdate(query) ;
           System.out.println("La specialite est bien modifiée");
        } catch (SQLException ex){
           System.out.println(ex.getMessage()) ; 
            }
   }
    
}
