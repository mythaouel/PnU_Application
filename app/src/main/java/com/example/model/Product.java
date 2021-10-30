package com.example.model;

public class Product {
    private int productThumbnail;
    private String productName;
    private double productPrice;

    public Product(int productThumbnail, String productName, double productPrice) {
        this.productThumbnail = productThumbnail;
        this.productName = productName;
        this.productPrice = productPrice;
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
}
