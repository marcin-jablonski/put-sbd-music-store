/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.logic;

import musicstore.datastructures.ProductInfo;

/**
 *
 * @author jablo
 */
public class ProductInfoRetriever {
    public static ProductInfo getProductInfo(int productId)
    {
        ProductInfo mockInfo = new ProductInfo();
        
        mockInfo.setProductName("Gibson Les Paul Custom 2015 VS");
        mockInfo.setProductAvailability(true);
        mockInfo.setProductDescription("One and only, Gibson LP Custom!");
        mockInfo.setProductPrice((float) 11999.99);
        mockInfo.setProductStorageState(30);
        mockInfo.setProductImagePath("src/resources/productImages/sample.jpg");
        
        return mockInfo;     
    }
}
