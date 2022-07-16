package com.example.grocerymanagement1;

public class productModel {
    String productName;
    String productPrice;
    String productDesc;
    String image;

    public productModel(){}

    public productModel(String productName, String productPrice, String productDesc, String image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
