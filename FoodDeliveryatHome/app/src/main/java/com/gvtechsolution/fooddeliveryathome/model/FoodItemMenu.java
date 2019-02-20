package com.gvtechsolution.fooddeliveryathome.model;

public class FoodItemMenu {
    private int id;
    private String title;
    private String description;
    private float price;
    private int restaurant_image;


    public FoodItemMenu(int id, String title, String description, float price, int restaurant_image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.restaurant_image = restaurant_image;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

}
