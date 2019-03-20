package com.gvtechsolution.fooddeliveryathome.model;

public class SpecialMenuItem {
    int id;
    String image;
    String dish_name;
    String dish_description;
    float dish_price;
    public SpecialMenuItem(int id, String image, String dish_name, String dish_description, float dish_price) {
        this.id = id;
        this.image = image;
        this.dish_name = dish_name;
        this.dish_description = dish_description;
        this.dish_price = dish_price;

    }
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDish_name() {
        return dish_name;
    }

    public String getDish_description() {
        return dish_description;
    }

    public float getDish_price() {
        return dish_price;
    }
}
