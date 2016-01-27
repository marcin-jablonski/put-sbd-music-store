/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import java.util.ArrayList;
import java.util.List;
import musicstore.datastructures.FilterInfo;
import musicstore.datastructures.ProductInfo;

/**
 *
 * @author jablo
 */
public class ProductsRetriever {
    public static List<ProductInfo> GetProducts(FilterInfo filters){
        List<ProductInfo> mockInfo = new ArrayList<>();
        ProductInfo mock1 = new ProductInfo();
        ProductInfo mock2 = new ProductInfo();
        ProductInfo mock3 = new ProductInfo();
        
        //TODO: fill mockInfo
        
        mock1.setProductName("Gibson Les Paul Custom 2015 VS");
        mock1.setProductAvailability(true);
        mock1.setProductDescription("One and only, Gibson LP Custom!");
        mock1.setProductPrice((float) 11999.99);
        mock1.setProductStorageState(30);
        mock1.setProductImagePath("src/resources/productImages/sample.jpg");
        
        mock2.setProductName("Gibson Les Paul Custom 2015 VS");
        mock2.setProductAvailability(true);
        mock2.setProductDescription("One and only, Gibson LP Custom!");
        mock2.setProductPrice((float) 11999.99);
        mock2.setProductStorageState(30);
        mock2.setProductImagePath("src/resources/productImages/sample.jpg");
        
        mock3.setProductName("Gibson Les Paul Custom 2015 VS");
        mock3.setProductAvailability(true);
        mock3.setProductDescription("One and only, Gibson LP Custom!");
        mock3.setProductPrice((float) 11999.99);
        mock3.setProductStorageState(30);
        mock3.setProductImagePath("src/resources/productImages/sample.jpg");
        
        mockInfo.add(mock1);
        mockInfo.add(mock2);
        mockInfo.add(mock3);
        
        return mockInfo;
    }
}
