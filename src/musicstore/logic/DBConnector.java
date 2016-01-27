/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jablo
 */
public class DBConnector {
    public static Connection Connect(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "inf117270");
        connectionProps.put("password", "heroes5");
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//admlab2-main.cs.put.poznan.pl:1521/dblab01.cs.put.poznan.pl", 
                    connectionProps);
            System.out.println("Połączono z bazą danych");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE,
            "Nie udało się połączyć z bazą danych", ex);
            System.exit(-1);
        }
        return conn;
    }
     
    public static void Disconnect(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
