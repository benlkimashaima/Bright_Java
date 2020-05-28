/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import pij.entity.Campement;
import pij.entity.Refugie;
import pij.utils.connectionDB;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.util.logging.Level;


/**
 *
 * @author Brahim
 */
public class refugieCrud {
        Connection cn2;
    Statement st;
campCrud cC = new campCrud();

    public refugieCrud() {
        cn2 =connectionDB.getInstance().getCnx();
    }
    
    public int addRefugie(Refugie c) {
        int st = 0;
        try {
            PreparedStatement pst;
            String requete2;
            requete2 = "INSERT INTO refuge (nom, prenom, nationalite, img, BirthD, birthLoc, sexe, socialSit, camp)VALUES (?,?,?,?,?,?,?,?,?)";
            pst = cn2.prepareStatement(requete2);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getNationality());
            pst.setString(4, c.getImg());
            pst.setDate(5, (Date) c.getBirthD());
            pst.setString(6, c.getBirthLoc());
            pst.setString(7, c.getSexe());
            pst.setString(8, c.getSocialSit());
            pst.setInt(9, cC.getId(c.getCamp()));
            st = pst.executeUpdate();
            //cn2.close();

         //   pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("erreur lors de l'ajout"+ex.getMessage());
        }
        return st;
    }
        public int deleteRefugie(String nom) {
        
            int st = 0;
            try {

            String reqDel = "DELETE FROM refuge WHERE nom=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setString(1, nom);
            st = pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
            return st;
    }
        public int updateRefugie(Refugie c){
    int st=0;
    try{
        String  sql =" UPDATE refuge SET socialSit = ?, camp = ?  WHERE nom=?";
       

     
     PreparedStatement pst = cn2.prepareStatement(sql);
     pst= cn2.prepareStatement(sql);
     pst.setString(1, c.getSocialSit());
     pst.setInt(2, cC.getId(c.getCamp()));
     pst.setString(3, c.getNom());
     st= pst.executeUpdate();
     System.out.println("Test");
       //con.close();
        }catch (SQLException e){
        e.printStackTrace();
    }
    return st;
    
}
        public String getImgPath(String nom)
       {
           String img_path="";
            try {
                
                String query = "select img from refuge where nom LIKE?";
                PreparedStatement ps = cn2.prepareStatement(query);
                ps.setString(1,nom);
                 ResultSet rs = ps.executeQuery();
            while (rs.next()){
                img_path = rs.getString("img");
            }
            } catch (SQLException ex) {
                Logger.getLogger(refugieCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
           return img_path;
           
       }
}
