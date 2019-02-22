package com.gvtechsolution.fooddeliveryathome.activities.business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;

public class BusinessRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_registration);

        Button business_login =findViewById(R.id.business_register_button);
        business_login.setOnClickListener(business_login_clicklistener);
    }

    private View.OnClickListener business_login_clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BusinessRegistrationActivity.this, BusinessFoodItemDetailsActivity.class);
            startActivity(intent);
        }
    };
}
