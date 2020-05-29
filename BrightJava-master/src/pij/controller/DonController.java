/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pij.entity.don;
import pij.entity.stock;
import pij.services.don_crud;
import pij.services.stock_crud;
import pij.utils.MyConnection;

/**
 *
 * @author HP
 */
public class DonController implements Initializable {
    
     @FXML
    private TextField tlibelle;
    @FXML
    private TextField tquantite;
    @FXML
    private TableView<don> table;
    @FXML
    private TableColumn<don, String> col_libelle;
    @FXML
    private TableColumn<don, Integer> col_quantite;
    @FXML
    private TableColumn<don, Date> col_date;
    @FXML
    private TableColumn<don, String> col_stock;
    @FXML
    private DatePicker dated;
    @FXML
    private ComboBox<String> Cstock;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelu;
    @FXML
    private Label labesquanite;
    @FXML
    private Label labeldate;
    @FXML
    private Label labelstock;
    public ObservableList<don> tables = FXCollections.observableArrayList();
    private don ev=null;
    stock s =new stock();
              //List<String> type;

    @FXML 
    private TextField search;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner stock");
        stock_crud a=new stock_crud();
        String req = "SELECT * FROM stock";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                availableChoices.add(s.getType());
                Cstock.setItems(availableChoices);
                Cstock.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }                   

     
    } 
    
    
   @FXML
    private void convertirEnPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
    String file="E:\\don.pdf";
    Document document =new Document();
    Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
    a2.setTitle("Conversion PDF !");
    a2.setContentText("PDF telecharge avec succés!");
    a2.show();

     try{
           Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.UNDERLINE));
           f.setColor(0, 153, 255);
           Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD));
           f2.setColor(0, 0, 0);
           PdfWriter.getInstance(document, new FileOutputStream(new File(file)));
           document.open();
           Paragraph p =new Paragraph("LISTE  DES  DON  " ,f);
           document.add(Image.getInstance("E:\\don.png"));
           Paragraph pm =new Paragraph();
           pm.add("   \n  ");
           p.setAlignment(Element.ALIGN_CENTER);
           document.add(p);
           document.add(pm);
           document.add(pm);
           Paragraph posss= new Paragraph("__________________________________________________");
           document.add(posss);
           Paragraph pos= new Paragraph("Libelle"+"      "+"Quantite "+"      "+" Date"+"      "+"Type",f2);
           document.add(pos);
           document.add(posss);
           Connection cn2 = MyConnection.getInstance().getCnx();
           String req ="select d.*,s.type from don d INNER JOIN stock s on d.Stock_id = s.id  ";
           Statement pst = cn2.createStatement();
           ResultSet rs = pst.executeQuery(req);
      while (rs.next()) {
           Paragraph p1= new Paragraph( "   ");
           Paragraph po= new Paragraph(rs.getString("libelle")+"                      "+rs.getString("quantite")+"                     "+rs.getString("date")+"               "+rs.getString("type"));
           document.add(p1);
           document.add(po);
            }
         document.close();
         System.out.println("Done");
     }catch(Exception e){
         e.printStackTrace();
     }
    }

     
     @FXML
    private void ajouter(ActionEvent event) throws SQLException {
     labelnom.setText("");
     labesquanite.setText("");
     labeldate.setText("");
     labelstock.setText(""); 
     System.out.println("date d'aujourdhui"+new java.util.Date());
     SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
     System.out.println(formater.format(new java.util.Date()));
     String aujourdhui=formater.format(new java.util.Date());
 if(tlibelle.getText().isEmpty()||dated.getValue() == null||tquantite.getText().isEmpty()||Cstock.getSelectionModel().getSelectedItem().equals("Selectionner stock")){
         if (tlibelle.getText().isEmpty()) {
          labelnom.setText("Libelle Vide");
        }
         if (tquantite.getText().isEmpty()) {
          labesquanite.setText("Qantité Vide");
        }
         if (dated.getValue() == null) {
        labeldate.setText("Date Vide");
        } 
         if (Cstock.getSelectionModel().getSelectedItem().equals("Selectionner stock")) {
           labelstock.setText("Stock Vide");
        } 
        }else { 
        int nb_place= Integer.parseInt(tquantite.getText());
        if(nb_place<0 || dated.getValue().toString().compareTo(aujourdhui)>0   ){
             
            if (nb_place<0) {
            labesquanite.setText("quantite doit etre > 0 ");}
            if (dated.getValue().toString().compareTo(aujourdhui)>0) {
            labeldate.setText("Date doit etre < a celle d'aujourdhui  ");}
       } else {
        String a = tlibelle.getText();
        LocalDate d= (LocalDate)dated.getValue();
        java.sql.Date sqlDate1 = java.sql.Date.valueOf(d);
        don_crud sp = new don_crud();
        stock_crud stocks = new stock_crud();
        stock s=new stock();
        s=stocks.getStock(Cstock.getValue());
        don_crud ac = new don_crud();
        don dd = new don(a,nb_place,sqlDate1,s.getId());
        ac.ajouter(dd); 
        Alert alert =new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Don!");
        alert.setHeaderText("information!");
        alert.setContentText("Added  Don!");
        alert.showAndWait();}
 }
    }
     
    
    @FXML
    private void SelectItemes(MouseEvent event) {
        Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<don> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        ev = (don)table.getSelectionModel().getSelectedItem();
        stock_crud stocks = new stock_crud();
        stock b=new stock();
        if (oblist != null) {
            tlibelle.setText(oblist.get(0).getLibelle());
            tquantite.setText(""+oblist.get(0).getQuantite());
            dated.setValue(LocalDate.now());
            try {
                 Cstock.setValue(stocks.getStockType(ev.getStock_id()));
                } catch (SQLException ex) {
                  Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
                }                        
            int max = oblist.get(0).getReference();

        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner stock");
        stock_crud a=new stock_crud();
        String req = "SELECT * FROM stock";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                availableChoices.add(s.getType());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         labelu.setText("");
         System.out.println("date d'aujourdhui"+new java.util.Date());
     SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
     System.out.println(formater.format(new java.util.Date()));
     String aujourdhui=formater.format(new java.util.Date());
       if(tlibelle.getText().isEmpty()||dated.getValue() == null||tquantite.getText().isEmpty()||Cstock.getSelectionModel().getSelectedItem().equals("Selectionner stock")){
         if (tlibelle.getText().isEmpty()) {
          labelnom.setText("Libelle Vide");
        }
         if (tquantite.getText().isEmpty()) {
          labesquanite.setText("Qantité Vide");
        }
         if (dated.getValue() == null) {
        labeldate.setText("Date Vide");
        } 
         if (Cstock.getSelectionModel().getSelectedItem().equals("Selectionner stock")) {
           labelstock.setText("Stock Vide");
        } 
        }else { 
        int nb_place= Integer.parseInt(tquantite.getText());
        if(nb_place<0 || dated.getValue().toString().compareTo(aujourdhui)>0   ){
             
            if (nb_place<0) {
            labesquanite.setText("quantite doit etre > 0 ");}
            if (dated.getValue().toString().compareTo(aujourdhui)>0) {
            labeldate.setText("Date doit etre < a celle d'aujourdhui  ");}
       } else {
                don A = new don();
                A.setLibelle(tlibelle.getText());
                A.setQuantite(Integer.parseInt(tquantite.getText()));
                stock_crud stocks = new stock_crud();
                stock b=new stock();
                b=stocks.getStock(Cstock.getValue());  
                System.out.println("cle etranger"+b.getId());

               ObservableList<don> oblist;
               oblist = table.getSelectionModel().getSelectedItems();
               don_crud act = new don_crud();
               LocalDate d= (LocalDate)dated.getValue();
               java.sql.Date datee = java.sql.Date.valueOf(d);
               don a=new don(tlibelle.getText(),Integer.parseInt(tquantite.getText()),datee,b.getId());
               try {
               act.modifier(a,ev.getReference());
            
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Update Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Updated Don!");
            alert.showAndWait();
            } catch (SQLException ex) {
            System.out.println(ex);
        }
 }
        
    }
    }
    
    
    @FXML
    private void supp(ActionEvent event) {
        ObservableList<don> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getReference();
        don A = new don();
        don_crud act = new don_crud();
        try {
            act.supprimer(max);
             Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("delete Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Deleted  Don!");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    
    
    @FXML
    private void afficher(ActionEvent event) {

     don_crud sp = new don_crud();
      List events=sp.displayALLDon();
       ObservableList et=FXCollections.observableArrayList(events);
       table.setItems(et);
       
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("sto"));

    }
    
    
    public void vider (){
       tlibelle.clear();
       tquantite.clear();
         Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner stock");
        stock_crud a=new stock_crud();
        String req = "SELECT * FROM stock";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                availableChoices.add(s.getType());
                Cstock.setItems(availableChoices);
                Cstock.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
     
     
    @FXML
    private void recherche(KeyEvent event) {
             don_crud sp = new don_crud();

         List events=sp.displayALLDon();
       ObservableList et=FXCollections.observableArrayList(events);
       table.setItems(et);
        table.setItems((ObservableList<don>) et);
        FilteredList<don> filteredData = new FilteredList<>(et, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (A.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }// Filter matches first name.
                else {
                    return false;
                }
            });
        });
        SortedList<don> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
   public boolean isAEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
   
}
