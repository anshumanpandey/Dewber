package com.gvtechsolution.fooddeliveryathome.model;

public class BusinessOrderDetails {
    int id;
    int image;
    String order_no;
    float order_price;
    String order_status;
    String delivery_time;

    public BusinessOrderDetails(int id, int image, String order_no, float order_price, String order_status, String delivery_time) {
        this.id = id;
        this.image = image;
        this.order_no = order_no;
        this.order_price = order_price;
        this.order_status = order_status;
        this.delivery_time = delivery_time;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getOrder_no() {
        return order_no;
    }

    public float getOrder_price() {
        return order_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getDelivery_time() {
        return delivery_time;
    }
}
