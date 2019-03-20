package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
                        "Biryani House",
                        "North Indian,Biriyani",
                        200,
                        "30-40Min",
                        4.5F,
                        "1.3 km",
                        R.drawable.resturant_zodiac));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biryani Corner",
                        "Biryani,Chicken Masala",
                        100,
                        "20-25Min",
                        3.5F,
                        "1.5 km",
                        R.drawable.resturant_rollinout));

        resturantList.add(
                new ResturantItem(
                        1,
                        "Arsalan",
                        "Mughlai,Biryani",
                        350,
                        "35-50Min",
                        4,
                        "2 km",
                        R.drawable.kareems));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biryani House",
                        "North Indian,Biryani",
                        50,
                        "10-15Min",
                        4.5F,
                        "2.5 km",
                        R.drawable.resturant_thefarn));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biryani Corner",
                        "Biryani,Chicken Masala",
                        70,
                        "10-15Min",
                        4,
                        "2 km",
                        R.drawable.kareems));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Arsalan",
                        "Mughlai,Biryani",
                        200,
                        "20-30Min",
                        4.5F,
                        "3.1 km",
                        R.drawable.kfc));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biryani House",
                        "Biryani,Chicken Masala",
                        250,
                        "10-15Min",
                        4.5F,
                        "3.8 km",
                        R.drawable.dominos));
        resturantList.add(
                new ResturantItem(
                        1,
                        "Biryani Corner",
                        "Biryani,North Indian",
                        350,
                        "40-50Min",
                        4,
                        "4 km",
                        R.drawable.kareems));

        //creating recyclerview adapter
        HomeResturantListAdapter resturant_list_adapter = new HomeResturantListAdapter(mContext, resturantList);
        //setting adapter to recyclerview
        restaurant_list_search_by_food_item.setAdapter(resturant_list_adapter);
        //ActionBar actionBar = getActionBar();
        getSupportActionBar().setTitle("Biryani");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button change_location =findViewById(R.id.filter_by_food_item_user_location_change_button);
        change_location.setOnClickListener(location_listener);
    }
    private View.OnClickListener location_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FilterByFoodItemActivity.this, ChangeLocationActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
