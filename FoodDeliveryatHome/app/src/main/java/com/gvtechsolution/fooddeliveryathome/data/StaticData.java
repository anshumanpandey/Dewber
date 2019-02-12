package com.gvtechsolution.fooddeliveryathome.data;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.CartChildFoodItem;
import com.gvtechsolution.fooddeliveryathome.classes.CartParentFoodItem;
import com.gvtechsolution.fooddeliveryathome.classes.FoodItemCategory;
import com.gvtechsolution.fooddeliveryathome.classes.FoodItemMenu;

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


}
