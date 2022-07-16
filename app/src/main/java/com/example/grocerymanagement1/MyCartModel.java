package com.example.grocerymanagement1;

public class MyCartModel {
    String productName,productImage;
    int TotalPrice;
    String Quantity,productPrice;

    public MyCartModel(){}

    public MyCartModel(String productName, String productImage, String productPrice, String quantity, int totalPrice) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        Quantity = quantity;
        TotalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }
}
