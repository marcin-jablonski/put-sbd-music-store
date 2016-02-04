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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicstore.datastructures.AccountInfo;
import musicstore.datastructures.ProductInfo;

/**
 *
 * @author jablo
 */
public class AccountUpdater {
    public static void AddToCart(ProductInfo product, AccountInfo account){
        try {
            Connection conn = DBConnector.Connect();
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CARTITEM (CLIENT, GUITAR) VALUES (?, ?)");
            stmt.setInt(1, account.getId());
            stmt.setInt(2, product.getId());
            int changes = stmt.executeUpdate();
            stmt.close();
            
            DBConnector.Disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AccountUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void FinalizePurchase(AccountInfo account){
        Connection conn = DBConnector.Connect();
        try{
            ArrayList<Integer> guitarsInCart = new ArrayList<>();
            int purchaseID = 0;
            java.util.Date now = new java.util.Date();
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO PURCHASE (CLIENT, PURCHASEDATE, TOTALPRICE) VALUES(?, ?, (SELECT SUM(PRICE) FROM GUITAR g JOIN CARTITEM i ON i.GUITAR = g.ID WHERE i.CLIENT = ?))");
            stmt.setInt(1, account.getId());
            stmt.setDate(2, new java.sql.Date(now.getTime()));
            stmt.setInt(3, account.getId());
            int changes = stmt.executeUpdate();
            stmt.close();
            
            stmt = conn.prepareStatement("SELECT last_number FROM user_sequences WHERE sequence_name = 'PURCHASE_SEQ'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                purchaseID = rs.getInt(1) - 1;
            }
            rs.close();
            stmt.close();
            
            stmt = conn.prepareStatement("SELECT GUITAR FROM CARTITEM WHERE CLIENT = ?");
            stmt.setInt(1, account.getId());
            rs = stmt.executeQuery();
            while(rs.next()){
                guitarsInCart.add(rs.getInt(1));
            }
            rs.close();
            stmt.close();
            
            stmt = conn.prepareStatement("INSERT INTO GUITAR_PURCHASE (GUITAR, PURCHASE) VALUES(?, ?)");
            for(int i=0;i<guitarsInCart.size();i++){
                stmt.setInt(1, guitarsInCart.get(i));
                stmt.setInt(2, purchaseID);
                stmt.executeUpdate();
            }
            stmt.close();
            
            //decrement storage state
            stmt = conn.prepareStatement("UPDATE GUITAR SET STORAGESTATE = ? WHERE ID = ?");
            PreparedStatement stmt2 = conn.prepareStatement("SELECT STORAGESTATE FROM GUITAR WHERE ID = ? FOR UPDATE");
            for(int i=0;i<guitarsInCart.size();i++){
                int storageState = 0;
                stmt2.setInt(1, guitarsInCart.get(i));
                rs = stmt2.executeQuery();
                while(rs.next()){
                    storageState = rs.getInt(1);
                }
                stmt.setInt(1, storageState-1);
                stmt.setInt(2, guitarsInCart.get(i));
                changes = stmt.executeUpdate();
            }
            rs.close();
            stmt2.close();
            stmt.close();
            
            //remove cartitem rows
            stmt = conn.prepareStatement("DELETE FROM CARTITEM WHERE CLIENT = ? AND GUITAR = ?");
            for(int i = 0;i<guitarsInCart.size();i++){
                stmt.setInt(1, account.getId());
                stmt.setInt(2, guitarsInCart.get(i));
                stmt.executeUpdate();
            }
            stmt.close();
            
            stmt = conn.prepareStatement("COMMIT");
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            try {
                PreparedStatement stmt = conn.prepareStatement("ROLLBACK");
                stmt.execute();
                stmt.close();
                
                Logger.getLogger(AccountUpdater.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AccountUpdater.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        DBConnector.Disconnect(conn);
    }
}
