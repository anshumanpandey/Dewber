package com.gvtechsolution.fooddeliveryathome.model;

public class NotificationDetails  {
    int id;
    String notification_date;
    String notification_category;
    String notification_description;

    public NotificationDetails(int id, String notification_date,String notification_category, String notification_description) {
        this.id = id;
        this.notification_date = notification_date;
        this.notification_category = notification_category;
        this.notification_description = notification_description;
    }

    public int getId() {
        return id;
    }

    public String getNotification_date() {
        return notification_date;
    }

    public String getNotification_category() {
        return notification_category;
    }
    public String getNotification_description() {
        return notification_description;
    }
}
