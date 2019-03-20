package com.gvtechsolution.fooddeliveryathome.model;

public class ResturantItem {
    private int id;
    private String title;
    private String cuisine;
    private float price;
    private String delivery_time;
    private float rating;
    private String distance;
    private int resturant_image;




    public ResturantItem(int id, String title, String cuisine, float price, String delivery_time, float rating,String distance,int resturant_image ) {
        this.id = id;
        this.title = title;
        this.cuisine = cuisine;
        this.price = price;
        this.delivery_time = delivery_time;
        this.rating = rating;
        this.distance = distance;
        this.resturant_image = resturant_image;


    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCuisine() {
        return cuisine;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }public String getDelivery_time() {
        return delivery_time;
    }

    public String getDistance() {
        return distance;
    }
    public int getResturant_image() {
        return resturant_image;
    }


}
