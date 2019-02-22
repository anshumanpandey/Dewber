package com.gvtechsolution.fooddeliveryathome.model;

public class BusinessFoodItemList {
    int id;
    String item_name;
    String item_description;
    float price;
    int image;

    public BusinessFoodItemList(int id, String item_name, String item_description, float price, int image) {
        this.id = id;
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public float getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
