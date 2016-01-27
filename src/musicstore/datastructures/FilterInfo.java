/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.datastructures;

/**
 *
 * @author jablo
 */
public class FilterInfo {
    private String productName;
    private float priceMin;
    private float priceMax;
    private String body;

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the priceMin
     */
    public float getPriceMin() {
        return priceMin;
    }

    /**
     * @param priceMin the priceMin to set
     */
    public void setPriceMin(float priceMin) {
        this.priceMin = priceMin;
    }

    /**
     * @return the priceMax
     */
    public float getPriceMax() {
        return priceMax;
    }

    /**
     * @param priceMax the priceMax to set
     */
    public void setPriceMax(float priceMax) {
        this.priceMax = priceMax;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
