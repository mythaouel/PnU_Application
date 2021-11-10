package com.example.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productThumbnail;
    private String productName;
    private double productPrice;
    private String productDescription;

    public Product(int productThumbnail, String productName, double productPrice, String productDescription) {
        this.productThumbnail = productThumbnail;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
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
