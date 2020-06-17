/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Med Amir Jday
 */
public class serviceUtilisateur {
    
     private static serviceUtilisateur Instance;
    private Statement st;
    private ResultSet rs;
    
    public serviceUtilisateur(){
         DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(serviceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     Connection conn = DataSource.getInstance().getCnx();
    
}
