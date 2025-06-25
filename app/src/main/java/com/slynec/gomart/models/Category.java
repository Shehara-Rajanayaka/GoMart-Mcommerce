package com.slynec.gomart.models;

public class Category {
    private String name,image;

    public Category() { } // Needed for Firebase

    public Category(String name,String image) {
        this.name = name;
        this.image=image;
    }

    public String getName() { return name; }
    public String getimage() { return image; }
}
