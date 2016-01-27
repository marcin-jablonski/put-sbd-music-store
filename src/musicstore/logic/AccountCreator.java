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
import musicstore.datastructures.AccountInfo;

/**
 *
 * @author jablo
 */
public class AccountCreator {
    public enum AccountCreationResult{
        Ok, EmailTaken
    }
    
    public static AccountCreationResult CreateAccount(AccountInfo account)
    {
        boolean emailTaken = false;
        int changes = 0;
        Connection conn = DBConnector.Connect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EMAIL FROM CLIENT");
            while(rs.next()){
                if (rs.getString(1).equals(account.getEmail()))
                    emailTaken=true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(emailTaken) return AccountCreationResult.EmailTaken;
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CLIENT (FIRSTNAME, LASTNAME, EMAIL, PASSWORD) VALUES (?, ?, ? ,?)");
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getPassword());
            changes = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBConnector.Disconnect(conn);
        return AccountCreationResult.Ok;
    }
}
