/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicstore.datastructures.FilterInfo;
import musicstore.datastructures.ProductInfo;

/**
 *
 * @author jablo
 */
public class ProductsRetriever {
    public static List<ProductInfo> GetProducts(FilterInfo filters){
        List<ProductInfo> products = new ArrayList<>();
        Connection conn = DBConnector.Connect();
        
        PreparedStatement stmt;
        try{
            if(filters == null){
                stmt = conn.prepareStatement("SELECT BODY, PRICE, NAME, DESCRIPTION, STORAGESTATE, ID FROM GUITAR");
            }
            else if (!"".equals(filters.getProductName())) {
                stmt = conn.prepareStatement("SELECT BODY, PRICE, NAME, DESCRIPTION, STORAGESTATE, ID FROM GUITAR WHERE BODY = ? AND PRICE BETWEEN ? AND ? AND NAME LIKE ?");
                stmt.setString(1, filters.getBody());
                stmt.setFloat(2, filters.getPriceMin());
                stmt.setFloat(3, filters.getPriceMax());
                stmt.setString(4, filters.getProductName());
            }
            else{
                stmt = conn.prepareStatement("SELECT BODY, PRICE, NAME, DESCRIPTION, STORAGESTATE, ID FROM GUITAR WHERE BODY = ? AND PRICE BETWEEN ? AND ?");
                stmt.setString(1, filters.getBody());
                stmt.setFloat(2, filters.getPriceMin());
                stmt.setFloat(3, filters.getPriceMax());
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ProductInfo info = new ProductInfo();
                info.setId(rs.getInt(6));
                info.setProductAvailability((rs.getInt(5) > 0));
                info.setProductDescription(rs.getString(4));
                info.setProductName(rs.getString(3));
                info.setProductPrice(rs.getFloat(2));
                info.setProductStorageState(rs.getInt(5));
                info.setProductImagePath("src/resources/productImages/sample.jpg");
                products.add(info);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            Logger.getLogger(ProductsRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBConnector.Disconnect(conn);
        return products;
    }
}
