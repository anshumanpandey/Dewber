package com.gvtechsolution.fooddeliveryathome.model;

public class Gallery {
        private String id;
        private String cover_image;

    public Gallery(String id, String cover_image) {
        this.id = id;
        this.cover_image = cover_image;
    }

    public String getId() {
        return id;
    }

    public String getCover_image() {
        return cover_image;
    }
}
