package com.example.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productId;
    private int productThumbnail;
    private String productName;
    private double productPrice;
    private String productDescription;

    public Product(String productId, int productThumbnail, String productName, double productPrice, String productDescription) {
        this.productId = productId;
        this.productThumbnail = productThumbnail;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(int productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription(){
        return  productDescription;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
}
