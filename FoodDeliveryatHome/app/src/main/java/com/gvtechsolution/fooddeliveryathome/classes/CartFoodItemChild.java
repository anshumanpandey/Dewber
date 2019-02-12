package com.gvtechsolution.fooddeliveryathome.classes;

public class CartFoodItemChild {
    int id;
    String item_name;
    String item_amount;

    public CartFoodItemChild(int id, String item_name, String item_amount) {
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

    public String getItem_amount() {
        return item_amount;
    }
}
