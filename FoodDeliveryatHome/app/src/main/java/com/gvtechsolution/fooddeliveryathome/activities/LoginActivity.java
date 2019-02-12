package com.gvtechsolution.fooddeliveryathome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessRegistrationActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.HomeActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button user_registration = (Button)findViewById(R.id.login_register_button);
        user_registration.setOnClickListener(clickListener);
        Button business_registration = (Button)findViewById(R.id.login_business_link_button);
        business_registration.setOnClickListener(business_registration_clickListener);
        Button user_home = (Button)findViewById(R.id.login_button);
        user_home.setOnClickListener(login_clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener business_registration_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, BusinessRegistrationActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener login_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };
}
