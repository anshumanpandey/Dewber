package com.gvtechsolution.fooddeliveryathome.classes;

public class FoodItemCategory {
    private int id;
    private String item_category;

    public FoodItemCategory(int id, String item_category) {
        this.id = id;
        this.item_category = item_category;
    }

    public int getId() {
        return id;
    }

    public String getItem_category() {
        return item_category;
    }
}
