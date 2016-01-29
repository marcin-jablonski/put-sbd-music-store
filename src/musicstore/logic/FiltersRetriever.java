/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jablo
 */
public class FiltersRetriever {
    public static Map<String, List<String>> GetFilters(){
        Map<String, List<String>> filters = new HashMap<>();
        try {        
            List<String> bodystyles = new ArrayList<>();
            
            Connection conn = DBConnector.Connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT BODY FROM GUITAR");
            while(rs.next()){
                bodystyles.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            filters.put("Body", bodystyles);
            DBConnector.Disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(FiltersRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filters;
    }
}
