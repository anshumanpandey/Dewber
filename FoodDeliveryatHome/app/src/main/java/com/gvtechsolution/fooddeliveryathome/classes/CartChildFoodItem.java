package com.gvtechsolution.fooddeliveryathome.classes;

public class CartChildFoodItem {
    int id;
    String item_name;
    float item_amount;

    public CartChildFoodItem(int id, String item_name, float item_amount) {
        this.id = id;
        this.item_name = item_name;
        this.item_amount = item_amount;
    }

    public int getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }
    public float getItem_amount() {
        return item_amount;
    }
}
