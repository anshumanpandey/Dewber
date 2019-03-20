package com.gvtechsolution.fooddeliveryathome.data;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.model.BusinessFoodItemList;
import com.gvtechsolution.fooddeliveryathome.model.BusinessOrderDetails;
import com.gvtechsolution.fooddeliveryathome.model.BusinessSpecialMenuItem;
import com.gvtechsolution.fooddeliveryathome.model.CartChildFoodItem;
import com.gvtechsolution.fooddeliveryathome.model.CartParentFoodItem;
import com.gvtechsolution.fooddeliveryathome.model.FoodItemCategory;
import com.gvtechsolution.fooddeliveryathome.model.FoodItemMenu;
import com.gvtechsolution.fooddeliveryathome.model.Gallery;
import com.gvtechsolution.fooddeliveryathome.model.NotificationDetails;
import com.gvtechsolution.fooddeliveryathome.model.SpecialMenuItem;
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
                        "Mutton Biryani",
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
        CartChildFoodItem one = new CartChildFoodItem(3,"Mutton Biryani",390);
        child_food_item.add(one);
        CartChildFoodItem two = new CartChildFoodItem(3,"Chicken Rezalla",200);
        child_food_item.add(two);
        return child_food_item;
    }
    public static List<NotificationDetails> getNotificationList(){
        List<NotificationDetails> notification_list  = new ArrayList<>();
        NotificationDetails one = new NotificationDetails(4,"07-Feb-2019 16:05","New Order","New Order #1941 Total amount:325$");
        notification_list.add(one);
        NotificationDetails two = new NotificationDetails(4,"04-Feb-2019 16:05","Special Menu","Home special: Check special bengali cuisine");
        notification_list.add(two);
        NotificationDetails three = new NotificationDetails(4,"02-Feb-2019 16:05","New Quotation","New Quotation: Check new quotation details");
        notification_list.add(three);
        return notification_list;
    }
    public  static List<UserOrderDetails> getOrderHistoryList(){
        List<UserOrderDetails> order_list = new ArrayList<>();
        UserOrderDetails one = new UserOrderDetails(5,R.drawable.resturant_zodiac,"XII ZODIAC","ChinarPark,Newtown,Rajarhat","Mutton Biryani","April 11,2018 at 12.57 PM","$500","Accepted");
        order_list.add(one);
        UserOrderDetails two = new UserOrderDetails(5,R.drawable.resturant_zodiac,"Roll In Out","ChinarPark,Newtown,Rajarhat","Chicken Rezalla","Jan 20,2019 at 12.02 PM","$300","Delivered");
        order_list.add(two);
        return order_list;
    }
    public static List<BusinessFoodItemList> getAllFoodItem(){
        List<BusinessFoodItemList> item_list = new ArrayList<>();
        BusinessFoodItemList one = new BusinessFoodItemList(6,"Mutton Biryani","rice with raita",320,R.drawable.mutton_biryani);
        item_list.add(one);
        BusinessFoodItemList two = new BusinessFoodItemList(6,"Sorshe Illish","Illish with spicy sorshe curry",390,R.drawable.sorshebatta_illish);
        item_list.add(two);
        return item_list;
    }
    public static List<BusinessOrderDetails> getBusinessOrderHistory(){
        List<BusinessOrderDetails> order_list = new ArrayList<>();
        BusinessOrderDetails one = new BusinessOrderDetails(7,R.drawable.chicken_rezalla,"Order No:1350",350,"pending","21.35");
        order_list.add(one);
        BusinessOrderDetails two = new BusinessOrderDetails(7,R.drawable.mutton_biryani,"Order No:1349",300,"accepted","16.05");
        order_list.add(two);
        BusinessOrderDetails three = new BusinessOrderDetails(7,R.drawable.sorshebatta_illish,"Order No:1348",250,"accepted","14.40");
        order_list.add(three);
        BusinessOrderDetails four = new BusinessOrderDetails(7,R.drawable.sorshebatta_illish,"Order No:1347",250,"delivered","14.05");
        order_list.add(four);
        BusinessOrderDetails five = new BusinessOrderDetails(7,R.drawable.mutton_biryani,"Order No:1346",300,"delivered","14.00");
        order_list.add(five);
        return order_list;
    }
    public static List<Gallery> getGalleryItem(){
        List<Gallery> gallery_item_list = new ArrayList<>();
        Gallery one = new Gallery("8","http://2.bp.blogspot.com/-jruypSe-SuM/VFoMFhpFvVI/AAAAAAAAAvQ/OEczVH1yxn8/s1600/quick-boiled-egg-fry%2B(1).jpg");
        gallery_item_list.add(one);
        Gallery two = new Gallery("8","http://khanakhazana.com/recipes/images/35565_6924.png");
        gallery_item_list.add(two);
        Gallery three = new Gallery("8","http://khanakhazana.com/recipes/images/90747_6020.png");
        gallery_item_list.add(three);
        Gallery four = new Gallery("8","http://khanakhazana.com/recipes/images/80330_6645.png");
        gallery_item_list.add(four);
        return gallery_item_list;
    }
    public static List<SpecialMenuItem> getSpecialMenu(){
        List<SpecialMenuItem> special_menu_list = new ArrayList<>();
        SpecialMenuItem one = new SpecialMenuItem(9,"http://2.bp.blogspot.com/-jruypSe-SuM/VFoMFhpFvVI/AAAAAAAAAvQ/OEczVH1yxn8/s1600/quick-boiled-egg-fry%2B(1).jpg","Boiled Egg Masala","boiled egg with capsicum,tomatoe and potatoe mix",35);
        special_menu_list.add(one);
        SpecialMenuItem two = new SpecialMenuItem(9,"http://khanakhazana.com/recipes/images/35565_6924.png","Katla Fish Curry","katla with hot chili curry",65);
        special_menu_list.add(two);
        SpecialMenuItem three = new SpecialMenuItem(9,"http://khanakhazana.com/recipes/images/90747_6020.png","Aloo Gobhi Tadka","aloo gobhi with curry leaf",25);
        special_menu_list.add(three);
        SpecialMenuItem four = new SpecialMenuItem(9,"http://khanakhazana.com/recipes/images/80330_6645.png","Chicken Dopiaza","chicken curry with capsicum,onion",55);
        special_menu_list.add(four);
        return special_menu_list;
    }
    public static List<BusinessSpecialMenuItem> getSpecialMenuItem(){
        List<BusinessSpecialMenuItem> item_list = new ArrayList<>();
        BusinessSpecialMenuItem one = new BusinessSpecialMenuItem("10","Boiled Egg Masala","boiled egg with capsicum,tomatoe and potatoe mix\"",35,"http://2.bp.blogspot.com/-jruypSe-SuM/VFoMFhpFvVI/AAAAAAAAAvQ/OEczVH1yxn8/s1600/quick-boiled-egg-fry%2B(1).jpg");
        item_list.add(one);
        BusinessSpecialMenuItem two = new BusinessSpecialMenuItem("10","Katla Fish Curry","katla with hot chili curry",65,"http://khanakhazana.com/recipes/images/35565_6924.png");
        item_list.add(two);
        BusinessSpecialMenuItem three = new BusinessSpecialMenuItem("10","Aloo Gobhi Tadka","aloo gobhi with curry leaf",25,"http://khanakhazana.com/recipes/images/90747_6020.png");
        item_list.add(three);
        BusinessSpecialMenuItem four = new BusinessSpecialMenuItem("10","Chicken Dopiaza","chicken curry with capsicum,onion",55,"http://khanakhazana.com/recipes/images/80330_6645.png");
        item_list.add(four);
        return item_list;
    }
}
