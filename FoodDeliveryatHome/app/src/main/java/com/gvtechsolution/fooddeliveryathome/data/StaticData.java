package com.gvtechsolution.fooddeliveryathome.data;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.model.CartChildFoodItem;
import com.gvtechsolution.fooddeliveryathome.model.CartParentFoodItem;
import com.gvtechsolution.fooddeliveryathome.model.FoodItemCategory;
import com.gvtechsolution.fooddeliveryathome.model.FoodItemMenu;
import com.gvtechsolution.fooddeliveryathome.model.NotificationDetails;
import com.gvtechsolution.fooddeliveryathome.model.UserOrderDetails;

import java.util.ArrayList;
import java.util.List;

public class StaticData {


    public static List<FoodItemMenu> getFoodItem(){
        List<FoodItemMenu> foodItemMenuList = new ArrayList<>();
        //adding some items to our list
        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Mutton Biriyani",
                        "rice with raita",
                        350,
                        R.drawable.mutton_biryani));
        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Chicken rezalla",
                        "rice with raita",
                        150,
                        R.drawable.chicken_rezalla));
        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Sorshebatta Illish",
                        "rice with raita",
                        400,
                        R.drawable.sorshebatta_illish));

        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Mutton Biriyani",
                        "rice with raita",
                        350,
                        R.drawable.mutton_biryani));
        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Chicken rezalla",
                        "rice with raita",
                        150,
                        R.drawable.chicken_rezalla));
        foodItemMenuList.add(
                new FoodItemMenu(
                        1,
                        "Sorshebatta Illish",
                        "rice with raita",
                        400,
                        R.drawable.sorshebatta_illish));

        return foodItemMenuList;
    }

    public static List<FoodItemCategory> getCategoryList(){
        List<FoodItemCategory> categories = new ArrayList<>();
        FoodItemCategory a = new FoodItemCategory(12,"AAAA");
        categories.add(a);
        FoodItemCategory b = new FoodItemCategory(12,"BBBBB");
        categories.add(b);
        FoodItemCategory c = new FoodItemCategory(12,"CCCCC");
        categories.add(c);
        FoodItemCategory d = new FoodItemCategory(12,"DDDDDDDD");
        categories.add(d);
        FoodItemCategory e = new FoodItemCategory(12,"EEEEE");
        categories.add(e);
        return categories;

    }
    public static List<CartParentFoodItem> getParentCartList(){
        List<CartParentFoodItem> parent_food_item = new ArrayList<>();
        CartParentFoodItem one = new CartParentFoodItem(2,R.drawable.resturant_zodiac,"XII ZODIAC","ChinarPark,Newtown,Rajarhat",590,60,20,670);
        parent_food_item.add(one);
        CartParentFoodItem two = new CartParentFoodItem(2, R.drawable.resturant_rollinout, "Roll In Out","ChinarPark,Newtown,Rajarhat", 190, 10, 20, 220);
        parent_food_item.add(two);
        return parent_food_item;
    }
    public static List<CartChildFoodItem> getChildCartList(){
        List<CartChildFoodItem> child_food_item = new ArrayList<>();
        CartChildFoodItem one = new CartChildFoodItem(3,"Mutton Biriyani",390);
        child_food_item.add(one);
        CartChildFoodItem two = new CartChildFoodItem(3,"Chicken Rezalla",200);
        child_food_item.add(two);
        return child_food_item;
    }
    public static List<NotificationDetails> getNotificationList(){
        List<NotificationDetails> notification_list  = new ArrayList<>();
        NotificationDetails one = new NotificationDetails(4,"02-Feb-2019 16:05","New Order #1941 Total amount:325$");
        notification_list.add(one);
        NotificationDetails two = new NotificationDetails(4,"07-Feb-2019 16:05","New Order #2059 Total amount:120$");
        notification_list.add(two);
        return notification_list;
    }
    public  static List<UserOrderDetails> getOrderHistoryList(){
        List<UserOrderDetails> order_list = new ArrayList<>();
        UserOrderDetails one = new UserOrderDetails(5,R.drawable.resturant_zodiac,"XII ZODIAC","ChinarPark,Newtown,Rajarhat","Mutton Biriyani","April 11,2018 at 12.57 PM","INR 500");
        order_list.add(one);
        UserOrderDetails two = new UserOrderDetails(5,R.drawable.resturant_zodiac,"Roll In Out","ChinarPark,Newtown,Rajarhat","Chicken Rezalla","Jan 20,2019 at 12.02 PM","INR 300");
        order_list.add(two);
        return order_list;
    }
}
