package com.gvtechsolution.fooddeliveryathome.data;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.CartFoodItemParent;
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
    public static List<CartFoodItemParent> getParentCartList(){
        List<CartFoodItemParent> parent_food_item = new ArrayList<>();
        //CartFoodItemParent one =new CartFoodItemParent(id)
    }


}
