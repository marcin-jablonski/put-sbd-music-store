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
public class ProductInfo {
    private int id;
    private String productName;
    private float productPrice;
    private String productDescription;
    private boolean productAvailability;
    private int productStorageState;
    private String productImagePath;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * @return the productPrice
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the productAvailability
     */
    public boolean isProductAvailability() {
        return productAvailability;
    }

    /**
     * @param productAvailability the productAvailability to set
     */
    public void setProductAvailability(boolean productAvailability) {
        this.productAvailability = productAvailability;
    }

    /**
     * @return the productStorageState
     */
    public int getProductStorageState() {
        return productStorageState;
    }

    /**
     * @param productStorageState the productStorageState to set
     */
    public void setProductStorageState(int productStorageState) {
        this.productStorageState = productStorageState;
    }

    /**
     * @return the productImagePath
     */
    public String getProductImagePath() {
        return productImagePath;
    }

    /**
     * @param productImagePath the productImagePath to set
     */
    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }
}
