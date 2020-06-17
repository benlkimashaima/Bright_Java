/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Med Amir Jday
 */
public class DataSource {
    
      Connection cnx;
        String url="jdbc:mysql://localhost:3306/refugees";
        String login="root";
        String pwd="";
        static DataSource ds;
        
        
         private DataSource() {
            try {
                cnx= (Connection) DriverManager.getConnection(url,login, pwd);
                System.out.println("Connexion effectuée avec succées");
            } catch (SQLException ex) {
                Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
         
  public static DataSource getInstance(){
        if(ds==null)
        {
            ds= new DataSource();
        }   
        return ds;
    }
  
  public Connection getCnx() {
        return cnx;
    }
    
}
