/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import pij.entity.Campement;
import pij.services.campCrud;
import pij.utils.JSON_Reader;
import pij.utils.connectionDB;
//import pij.controller.PieChartSample;


/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class MapViewController implements Initializable {

    @FXML
    private WebView webview;
    JSON_Reader jsonR = new JSON_Reader();
    @FXML
    private JFXListView<String> campList;
    ObservableList<Campement> Clist = FXCollections.observableArrayList();
    ObservableList<String> stringList = FXCollections.observableArrayList();
    campCrud cC = new campCrud();
    WebEngine webEngine ;
    private BorderPane pane;
    private StackedBarChart<String, Integer> barChart;
    Connection con=connectionDB.getInstance().getCnx();
    //PieChartSample P ;
    //Stage s;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //P.start(s);
        //Load Maps
          // loadChart();
           webEngine = webview.getEngine();
       
      webEngine.load(getClass().getResource("/pij/views/webmaps.html").toString());
     
      //Load List String of Camp Libelle
      Clist = update_list();
     Clist.stream().forEach(c -> stringList.add(c.getLibelle()));
     campList.setItems(stringList);
    }
//Load all camps
public ObservableList<Campement> update_list() {
                           try{
                        
                   
ResultSet rs =con.createStatement().executeQuery("SELECT * FROM camp ");


while (rs.next()){
    Clist.add(new Campement(rs.getString("libelle"), rs.getString("location"),rs.getInt("capacity"), rs.getString("lat"), rs.getString("lng")));
                        
                    }

          }catch (Exception ex) {
            Logger.getLogger(Campement.class.getName()).log(Level.SEVERE, null, ex);
        }
      return Clist;
                            
}    

    @FXML
    private void get_data(MouseEvent event) throws SQLException {
     //  Campement c = (Campement)C.getSelectionModel().getSelectedItem();
       //Connection con=connectionDB.getInstance().getCnx();
       String lib = campList.getSelectionModel().getSelectedItem();
        System.out.println(lib);
        Campement c = cC.getCamp(lib);
        System.out.println(c);
                webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<State>() {
                public void changed(ObservableValue ov, State oldState, State newState) {
                    if (newState == State.SUCCEEDED) {
                        webEngine.executeScript("addpopup(" + c.getLat() + "," + c.getLng() + ",'" + c.getLocation()+ "')");
                    }
                }
            });
       
      webEngine.load(getClass().getResource("/pij/views/webmaps.html").toString());
        //PreparedStatement stat = con.prepareStatement(rs);
     
       
    }
    private void refreshNodes(String node)
    {
        pane.getChildren().clear();
        
        Node [] nodes = new  Node[1];
        

            try {
                nodes[0] = (Node)FXMLLoader.load(getClass().getResource(node));
               pane.getChildren().add(nodes[0]);
                
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
          
    }
       /*      private void loadChart() {
        String query="select libelle,capacity from camp order by capacity asc";
     XYChart.Series<String,Integer> series = new XYChart.Series<>();
     try{
         //Connection con = (Connection) cn.cnx;
        
         ResultSet rs = con.createStatement().executeQuery(query);
         while(rs.next()){
               series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
         }
         barChart.getData().add(series);
         
         
     }catch(SQLException ex){
         System.out.println(ex);
     }
    }*/
}
