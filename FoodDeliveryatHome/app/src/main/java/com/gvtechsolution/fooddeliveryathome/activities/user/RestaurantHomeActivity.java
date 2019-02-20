package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

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
//

        RecyclerView parent_food_item_list = (RecyclerView) findViewById(R.id.restaurant_parent_recyclerview);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parent_food_item_list.setLayoutManager(linearLayoutManagerVertical);
        ParentFoodCategoryListAdapter adapter = new ParentFoodCategoryListAdapter(this,StaticData.getCategoryList());
        parent_food_item_list.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
