package com.example.grocerymanagement1;

import java.util.ArrayList;

public class ProductModelClass {
    private static ArrayList<ProductModelClass>cart_list = new ArrayList<>();

    private int productImage;
    private String productName;
    private String productprice;
    private String Description;


    public ProductModelClass(){}


    ProductModelClass(int productImage, String productName, String productprice, String Description) {
        this.productImage = productImage;
        this.productName = productName;
        this.productprice = productprice;
        this.Description = Description;
    }
    ProductModelClass(int productImage,String productName,String productprice){
        this.productImage=productImage;
        this.productName=productName;
        this.productprice=productprice;
    }

    public int getproductImage() {
        return productImage;
    }

    public String getproductName() {
        return productName;
    }

    public String getDescription() {
        return Description;
    }

    public String getproductpricee() {
        return productprice;
    }


    public static ArrayList<ProductModelClass> getcart_list() {
        return cart_list;
    }

    public static void setProducArrayList(ProductModelClass p) {
        ProductModelClass.cart_list.add(p);
    }
}
