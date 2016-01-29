/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicstore.datastructures.AccountInfo;

/**
 *
 * @author jablo
 */
public class AccountInfoRetriever {
    public static AccountInfo GetAccountInfo(int userID)
    {
        AccountInfo info = new AccountInfo();
        Connection conn = DBConnector.Connect();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT FIRSTNAME, LASTNAME FROM CLIENT WHERE ID = ?");
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                info.setFirstName(rs.getString(1));
                info.setLastName(rs.getString(2));
            }
            rs.close();
            stmt.close();
            
            stmt = conn.prepareStatement("SELECT PRICE FROM CARTITEM JOIN GUITAR ON CARTITEM.GUITAR = GUITAR.ID WHERE CARTITEM.CLIENT = ?");
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            info.setCartState(0);
            info.setTotalPrice((float) 0.0);
            while(rs.next()){
                info.setCartState(info.getCartState() + 1);
                info.setTotalPrice(info.getTotalPrice() + rs.getFloat(1));
            }
            rs.close();
            stmt.close();
        } catch(Exception e) {
            Logger.getLogger(AccountInfoRetriever.class.getName()).log(Level.SEVERE, null, e);
        }
        
        DBConnector.Disconnect(conn);
        info.setAvatarPath("src/resources/avatars/sample.jpg");
        info.setId(userID);
        return info;
    }
}
