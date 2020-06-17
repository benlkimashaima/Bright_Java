
package pij.controller;

/**
 * FXML Controller class
 *

 */
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pij.entity.stock;
import pij.services.stock_crud;


public class StockController implements Initializable {
     @FXML
    private TextField type;
    @FXML
    private Label ltype;
     @FXML
    private Label lb;
      
    @FXML
    private TableView<stock> table;
   
    @FXML
    private TableColumn<stock, String> col_type;
  
    public ObservableList<stock> tables = FXCollections.observableArrayList();
    private stock ev=null;
    stock s =new stock();

@FXML
    private TextField search;


 @FXML
    private void SelectItemes(MouseEvent event)throws SQLException{
         ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            type.setText(oblist.get(0).getType());

        }
    }

    
    

     
    
@FXML
private void ajouter(ActionEvent event) {
         ltype.setText("");
         if(type.getText().isEmpty()){
         ltype.setText("Champs Vide * ");
        }else {
        String TYPE = type.getText();
        stock_crud sc = new stock_crud();
        stock s = new stock(TYPE);
            sc.ajouter(s);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("Stock bien Ajouter!");
            alert.showAndWait();
         }  
    }
    
    
@Override
public void initialize(URL url, ResourceBundle rb) {
      
    } 
 
    
    
    
    
@FXML
private void modifier(ActionEvent event)throws SQLException {
        lb.setText("");
        if(type.getText().isEmpty()){
      lb.setText("Champs Vide * ");
        }else {
        stock s = new stock();
        s.setType(type.getText());
        ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getId();
        stock_crud act = new stock_crud();
        try {
            act.modifier(s, max);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Update  Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("updated Stock !");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }
    
    

@FXML
private void supp(ActionEvent event) {
    

                ObservableList<stock> oblist;
                oblist = table.getSelectionModel().getSelectedItems();
                int max = oblist.get(0).getId();
                stock_crud act = new stock_crud();
                try {
            act.supprimer(max);
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("delete  Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("deleted Stock !");
            alert.showAndWait();
                } catch (SQLException ex) {
                    System.out.println(ex);
                
        }
    }
    
    
    
@FXML
private void afficher(ActionEvent event) {
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        try {
            stock_crud sc = new stock_crud();
            stock s = new stock();
            tables = sc.afficher(s);
        } catch (SQLException ex) {
        }
        table.setItems((ObservableList<stock>) tables);
    }
    
    
    
@FXML
private void rechercheStock(KeyEvent event) {
        table.setItems((ObservableList<stock>) tables);
        FilteredList<stock> filteredData = new FilteredList<>(tables, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (A.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        SortedList<stock> stock = new SortedList<>(filteredData);
        stock.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(stock);
    }
    
    
    
     
public void vider (){
        type.clear();
    }
   
}
