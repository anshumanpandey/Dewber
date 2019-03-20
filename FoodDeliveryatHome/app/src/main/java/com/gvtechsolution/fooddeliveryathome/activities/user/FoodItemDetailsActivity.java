package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;

public class FoodItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_details);
        Button close_button = (Button)findViewById(R.id.item_details_close_button);
        close_button.setOnClickListener(close_button_listner);
        Button add_to_cart = (Button)findViewById(R.id.add_to_cart_button);
        add_to_cart.setOnClickListener(cart_listner);
    }
    private View.OnClickListener close_button_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener cart_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FoodItemDetailsActivity.this, CartActivity.class);
            startActivity(intent);
        }
    };
}
