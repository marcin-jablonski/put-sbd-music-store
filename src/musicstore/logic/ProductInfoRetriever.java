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
import java.util.logging.Level;
import java.util.logging.Logger;
import musicstore.datastructures.ProductInfo;

/**
 *
 * @author jablo
 */
public class ProductInfoRetriever {
    public static ProductInfo getProductInfo(int productId)
    {
        ProductInfo info = new ProductInfo();
        Connection conn = DBConnector.Connect();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT BODY, PRICE, NAME, DESCRIPTION, STORAGESTATE FROM GUITAR WHERE ID = ?");
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                info.setId(productId);
                info.setProductAvailability(rs.getInt(5) > 0 ? true : false);
                info.setProductDescription(rs.getString(4));
                info.setProductName(rs.getString(3));
                info.setProductPrice(rs.getFloat(2));
                info.setProductStorageState(rs.getInt(5));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfoRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        DBConnector.Disconnect(conn);
        info.setProductImagePath("src/resources/productImages/sample.jpg");
        
        return info;     
    }
}
