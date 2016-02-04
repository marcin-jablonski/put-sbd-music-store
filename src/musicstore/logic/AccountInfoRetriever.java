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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicstore.datastructures.AccountInfo;
import musicstore.datastructures.ProductInfo;

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
    
    public static List<ProductInfo> GetCartItems(int accountId){
        List<ProductInfo> products = new ArrayList<>();
        Connection conn = DBConnector.Connect();
        try {           
            PreparedStatement stmt = conn.prepareStatement("SELECT p.NAME, p.PRICE FROM GUITAR p JOIN CARTITEM i ON p.ID = i.GUITAR WHERE i.CLIENT = ?");
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ProductInfo product = new ProductInfo();
                product.setProductName(rs.getString(1));
                product.setProductPrice(rs.getFloat(2));
                products.add(product);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountInfoRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBConnector.Disconnect(conn);
        return products;
    }
}
