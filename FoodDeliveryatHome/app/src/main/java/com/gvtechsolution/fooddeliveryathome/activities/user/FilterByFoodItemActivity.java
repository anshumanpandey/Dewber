package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.adapter.HomeResturantListAdapter;
import com.gvtechsolution.fooddeliveryathome.model.ResturantItem;

import java.util.ArrayList;
import java.util.List;

public class FilterByFoodItemActivity extends AppCompatActivity {
    private Context mContext;
    List<ResturantItem> resturantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_food_item);
        RecyclerView restaurant_list_search_by_food_item = (RecyclerView) findViewById(R.id.home_popular_restaurant_recyclerview);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        restaurant_list_search_by_food_item.setLayoutManager(linearLayoutManagerVertical);

        resturantList = new ArrayList<>();

        //adding some items to our list
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biriyani House",
                        "North Indian,Biriyani",
                        200,
                        "30-40Min",
                        4.5F,
                        R.drawable.resturant_zodiac));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Briyani Corner",
                        "Biriyani,Chicken Masala",
                        100,
                        "20-25Min",
                        3.5F,
                        R.drawable.resturant_rollinout));

        resturantList.add(
                new ResturantItem(
                        1,
                        "Arsalan",
                        "Mughlai,Biriyani",
                        350,
                        "35-50Min",
                        4,
                        R.drawable.kareemz));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biriyani House",
                        "North Indian,Biriyani",
                        50,
                        "10-15Min",
                        4.5F,
                        R.drawable.resturant_thefarn));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Briyani Corner",
                        "Biriyani,Chicken Masala",
                        70,
                        "10-15Min",
                        4,
                        R.drawable.kareemz));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Arsalan",
                        "Mughlai,Biriyani",
                        200,
                        "20-30Min",
                        4.5F,
                        R.drawable.kfc));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biriyani House",
                        "Biriyani,Chicken Masala",
                        250,
                        "10-15Min",
                        4.5F,
                        R.drawable.dominos));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Briyani Corner",
                        "Biriyani,North Indian",
                        350,
                        "40-50Min",
                        4,
                        R.drawable.kareemz));

        //creating recyclerview adapter
        HomeResturantListAdapter resturant_list_adapter = new HomeResturantListAdapter(mContext, resturantList);

        //setting adapter to recyclerview
        restaurant_list_search_by_food_item.setAdapter(resturant_list_adapter);
        //ActionBar actionBar = getActionBar();
        getSupportActionBar().setTitle("Biriyani");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
