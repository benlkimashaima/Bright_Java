/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pij.utils.MyConnection;


/**
 *
 * @author YURI
 */
public class EvenementCrud {

    Connection cn2;
    Statement st;

    public EvenementCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }

    public void ajouter(Evenement a) {
        try {
            String requete = "INSERT INTO evenement (ref,nbrPart,titre,description,date,ville,categorie_id) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
             pst.setInt(1, a.getRef());

            pst.setInt(2, a.getNbPart());
            pst.setString(3, a.getTitre());
            pst.setString(4, a.getDescription());
            pst.setString(5, a.getDate());
            pst.setString(6, a.getVille());
            pst.setInt(7, a.getCategorie_id());
            pst.executeUpdate();
            System.out.println("Event ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Evenement> afficher() {

        ArrayList<Evenement> ann = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM evenement";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Evenement a = new Evenement();
                a.setRef(rs.getInt("ref"));
                a.setNbPart(rs.getInt("nbrPart"));
                a.setTitre(rs.getString("titre"));
                a.setDescription(rs.getString("description"));
                a.setDate(rs.getString("date"));
                a.setVille(rs.getString("ville"));
                ann.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ann;

    }

    public ObservableList<Evenement> afficher(Evenement A) throws SQLException {

        ObservableList<Evenement> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from evenement");

        while (rs.next()) {

            arr.add(new Evenement(rs.getInt("ref"),rs.getInt("nbrPart"), rs.getString("titre"), rs.getString("description"), rs.getString("date"), rs.getString("ville")));

        }
        return arr;

    }

    public void supprimer(int id_annonce) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from evenement where ref= " + id_annonce;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }

    
    public void modifier(Evenement A, int id) throws SQLException {

        try {
         
                              String query = "update evenement set nbrPart='" + A.getNbPart() + "',titre='" + A.getTitre() + "',titre='" + A.getTitre() + "',description='" + A.getDescription() + "',date='" + A.getDate() + "',ville='" + A.getVille() +"' where ref=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

           
        } catch (SQLException ex) {

        }

    }
    
}
