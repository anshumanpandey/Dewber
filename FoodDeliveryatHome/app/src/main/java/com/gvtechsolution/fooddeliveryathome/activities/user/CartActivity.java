package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.PaymentActivity;
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

        Button close_cart_button =findViewById(R.id.cart_close_button);
        close_cart_button.setOnClickListener(close_cart_listener);

        TextView add_more_button =(TextView)findViewById(R.id.cart_add_more_item_textView);
        add_more_button.setOnClickListener(add_more_item_listener);

        Button change_location =findViewById(R.id.cart_location_change_button);
        change_location.setOnClickListener(change_location_listener);
        Button change_payment_option =findViewById(R.id.cart_payment_mode_change_button);
        change_payment_option.setOnClickListener(payment_option_listener);
        Button pay_button = (Button) findViewById(R.id.proceed_pay_button);
        pay_button.setEnabled(false);

    }
    private View.OnClickListener close_cart_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener add_more_item_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener change_location_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CartActivity.this, ChangeLocationActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener payment_option_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            startActivity(intent);
        }
    };
}
