package com.gvtechsolution.fooddeliveryathome.model;

public class UserOrderDetails {
    int id;
    int restaurant_image;
    String restaurant_name;
    String restaurant_address;
    String item_name;
    String order_date;
    String total_amount;

    public UserOrderDetails(int id,int restaurant_image, String restaurant_name, String restaurant_address, String item_name, String order_date, String total_amount) {
        this.id = id;
        this.restaurant_image = restaurant_image;
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.item_name = item_name;
        this.order_date = order_date;
        this.total_amount = total_amount;
    }

    public int getId() {
        return id;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getTotal_amount() {
        return total_amount;
    }
}
