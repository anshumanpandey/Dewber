package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.adapter.ParentCartAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView parent_cart_food_item_list = (RecyclerView) findViewById(R.id.parent_cart_list);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parent_cart_food_item_list.setLayoutManager(linearLayoutManagerVertical);
        ParentCartAdapter adapter = new ParentCartAdapter(this,StaticData.getParentCartList());
        parent_cart_food_item_list.setAdapter(adapter);
    }
}
