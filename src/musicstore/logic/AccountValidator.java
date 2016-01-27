/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jablo
 */
public class AccountValidator {
    public static int AuthenticateUser(String email, String password){
        int userID = 0;
        try {
            Connection conn = DBConnector.Connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT ID, PASSWORD FROM CLIENT WHERE EMAIL=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                userID = rs.getInt(1);
            }   
            rs.close();
            stmt.close();
            DBConnector.Disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AccountValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
    }
}
