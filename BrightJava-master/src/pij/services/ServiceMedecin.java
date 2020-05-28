/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pij.entity.Medecin;
import pij.utils.connectionDB;

/**
 *
 * @author DELL
 */
public class ServiceMedecin {

    private Connection con;
    private Statement ste;

    public ServiceMedecin() {
        con = connectionDB.getInstance().getCnx();
    }
   
    public void ajouterMedecin(Medecin p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `refugees`.`medecin` ( `id`, `nCIN`, `nonMedecin`, `prenomMedecin`, `telephone`, `specialite`, `email`) VALUES ( ?, ?, ?, ?, ?, ?,?);");
      
    pre.setInt(1, p.getId());
    pre.setInt(2, p.getnCIN());
    pre.setString(3, p.getNonMedecin());
    pre.setString(4, p.getPrenomMedecin());
    pre.setString(5, p.getTelephone());
    
    pre.setInt(6, p.getSpecialite());
    pre.setString(7, p.getEmail());
    pre.executeUpdate();
    }
    

            

    
      
    public void supprimerMedecin(int id) throws SQLException {
            
         try { 
            String query ="DELETE FROM medecin WHERE id="+id; 
            Statement ste=con.createStatement();
            ste.executeUpdate(query) ; 
            System.out.println("La specialite d'id = "+id+"a été bien supprimée ") ; 
            }
                   catch(SQLException ex){
                   System.out.println(ex.getMessage()) ; 
                }
        }
    
    
    public void modifierMedecin(int id,String nonMedecin,String prenomMedecin,String telephone,int specialite,String email) throws SQLException {
       try {
           String query ="UPDATE medecin SET nonMedecin='"+nonMedecin+"',prenomMedecin='"+prenomMedecin+"',telephone='"+telephone+"',specialite='"+specialite+"',email='"+email+"' WHERE id='"+id+"'";
           Statement ste=con.createStatement();
           ste.executeUpdate(query) ;
           System.out.println("Le medecin est bien modifié");
        } catch (SQLException ex){
           System.out.println(ex.getMessage()) ; 
            }
        
        }

    public List<Medecin> afficherMedecin() throws SQLException {
            
            List<Medecin> arr=new ArrayList<>();
            Statement ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select nCIN,nonMedecin,prenomMedecin,telephone,specialite,email from medecin");
    
                while(rs.next()) {
               
                    int nCIN=rs.getInt(1);
                    String nonMedecin=rs.getString(2);
                    String prenomMedecin=rs.getString(3);
                    String Telephone=rs.getString(4);
                    int specialite=rs.getInt(5);
                    String email=rs.getString(6);

                    
               
                    Medecin a = new Medecin (nCIN,nonMedecin,prenomMedecin,Telephone,specialite,email);
                    arr.add(a);
                }
            return arr;
         }
    
    

}
