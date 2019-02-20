package com.gvtechsolution.fooddeliveryathome.model;

public class PopularDishes {
    private int id;
    private String title;
    private float price;
    private String delivery_time;
    private String restaurant_name;
    private int restaurant_image;

    public PopularDishes(int id, String title, float price, String delivery_time, String restaurant_name,int restaurant_image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.delivery_time = delivery_time;
        this.restaurant_name =restaurant_name;
        this.restaurant_image = restaurant_image;

    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getDelivery_time() {
        return delivery_time;
    }
    public String getRestaurant_name() {
        return restaurant_name;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }
}




