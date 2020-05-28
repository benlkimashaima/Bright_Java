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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pij.entity.stock;
import pij.utils.MyConnection;

/**
 *
 * @author HP
 */
public class stock_crud {
   Connection cn2;
   Statement st;
    public stock_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
    
    public stock getStock(String a) throws SQLException {   
        stock s = new stock();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM stock WHERE type LIKE ?  ;");
        pre.setString(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
           s.setId(rs.getInt("id"));
           s.setType(a);
        }
        return s;
    }
    
    
    public stock getStockId(int a) throws SQLException {
        stock s = new stock();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM stock WHERE id = ?  ;");
        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
           s.setId(a);
           s.setType(rs.getString("type"));
        }
        return s;
     }
    
    
    
     public String getStockType(int a) throws SQLException {
        String an="" ;
        PreparedStatement pre = cn2.prepareStatement("SELECT type FROM stock WHERE id = ?  ;");
        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
        an=rs.getString(1);
        }
        return an;
     }
     
     
     
    public ObservableList<stock> displayALLStock() {
        ObservableList<stock> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM stock";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                stock s = new stock();
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                myList.add(s);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
    
    
    
    public void ajouter(stock a) {
        try {
            String requete = "INSERT INTO stock (id,type) VALUES (?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getType());
            pst.executeUpdate();
            System.out.println("good");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public ObservableList<stock> afficher(stock A) throws SQLException {
        ObservableList<stock> s = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from stock");
        while (rs.next()) {
            s.add(new stock(rs.getInt("id"),rs.getString("type")));
        }
        return s;

    }
    
    
    
    public void modifier(stock s, int id) throws SQLException {
        try {
            if ((s.getType() != "")) {
                String query = "update stock set type='" + s.getType() + "' where stock.id=" + id;
                st = cn2.createStatement();
                st.executeUpdate(query);
                System.out.println("bien modifiée");
            } else {
                System.out.println("tu dois inserer tous les elements");
            }
        } catch (SQLException ex) {

        }
    }

    
    
    public void supprimer(int id) throws SQLException {
        st = cn2.createStatement();
        String q = "delete from stock where id= " + id;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");
    }
    
    
    
    public ObservableList<stock> rechercheStock(String recherche) throws SQLException {
        stock s = new stock();
        ObservableList<stock> list = FXCollections.observableArrayList();
        String requete = "select type from stock   WHERE type LIKE '%"+recherche+"%'    ";
        try {
            PreparedStatement ps = cn2.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            s.setType(rs.getString("type"));
            list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

         
}