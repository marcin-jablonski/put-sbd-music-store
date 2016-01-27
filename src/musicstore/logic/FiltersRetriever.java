/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jablo
 */
public class FiltersRetriever {
    public static Map<String, List<String>> GetFilters(){
        Map<String, List<String>> filters = new HashMap<>();
        
        List<String> bodystyles = new ArrayList<>();
        bodystyles.add("Les Paul");
        bodystyles.add("Stratocaster");
        bodystyles.add("Flying V");
        
        filters.put("Body", bodystyles);
        
        return filters;
    }
}
