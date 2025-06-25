package com.slynec.gomart.models;

public class Product {
    private String name, price, weight, discount,ImageUrl,Description;


    public Product() { } // Needed for Firebase

    public Product(String name, String price, String weight, String discount,String ImageUrl,String Description) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
        this.ImageUrl=ImageUrl;
        this.Description=Description;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getWeight() { return weight; }
    public String getDiscount() { return discount; }
    public String getImageUrl() { return ImageUrl; }
    public String getDescription() {
        return Description;
    }
}
