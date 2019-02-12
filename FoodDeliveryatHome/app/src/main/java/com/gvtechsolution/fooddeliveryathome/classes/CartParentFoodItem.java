package com.gvtechsolution.fooddeliveryathome.classes;

public class CartParentFoodItem {
    int id;
    int image;
    String name;
    String location;
    float subtotal;
    float tax;
    float delivery_charge;
    float grand_subtotal;

    public CartParentFoodItem(int id, int image, String name, String location, float subtotal, float tax, float delivery_charge, float grand_subtotal) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.location = location;
        this.subtotal = subtotal;
        this.tax = tax;
        this.delivery_charge = delivery_charge;
        this.grand_subtotal = grand_subtotal;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public float getTax() {
        return tax;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public float getGrand_subtotal() {
        return grand_subtotal;
    }
}
