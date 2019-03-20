package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.adapter.ParentFoodCategoryListAdapter;
import com.gvtechsolution.fooddeliveryathome.model.FoodItemMenu;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;

import java.util.List;

public class RestaurantHomeActivity extends AppCompatActivity {
    private Context mContext;
    List<FoodItemMenu> foodItemMenuList;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);
        RecyclerView parent_food_item_list = (RecyclerView) findViewById(R.id.restaurant_parent_recyclerview);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parent_food_item_list.setLayoutManager(linearLayoutManagerVertical);
        ParentFoodCategoryListAdapter adapter = new ParentFoodCategoryListAdapter(this,StaticData.getCategoryList());
        parent_food_item_list.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button change_location =findViewById(R.id.resturant_home_location_change_button);
        change_location.setOnClickListener(change_location_listener);
    }
    private View.OnClickListener change_location_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RestaurantHomeActivity.this, ChangeLocationActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
