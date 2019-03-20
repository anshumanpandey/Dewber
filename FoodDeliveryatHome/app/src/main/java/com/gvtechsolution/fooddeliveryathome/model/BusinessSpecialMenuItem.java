package com.gvtechsolution.fooddeliveryathome.model;

public class BusinessSpecialMenuItem {
    String id;
    String item_name;
    String item_description;
    float price;
    String image;

    public BusinessSpecialMenuItem(String id, String item_name, String item_description, float price,String image) {
        this.id = id;
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.image = image;
    }

    public String getId() {
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
    public String getImage() {
        return image;
    }
}
