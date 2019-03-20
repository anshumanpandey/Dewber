package com.gvtechsolution.fooddeliveryathome.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.BusinessAddProfileFragment;
import com.gvtechsolution.fooddeliveryathome.fragments.UserAddProfileFragment;
//import com.gvtechsolution.fooddeliveryathome.fragments.BusinessEditProfileFragment;
//import com.gvtechsolution.fooddeliveryathome.fragments.UserEditProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String account_fragment = intent.getStringExtra("account_fragment");
        if(account_fragment.equals("user")){
            replaceAccountFragment(new UserAddProfileFragment(), false);
        }
        else if(account_fragment.equals("business")){
            replaceAccountFragment(new BusinessAddProfileFragment(), false);
        }
    }
    public void replaceAccountFragment(Fragment fragment, boolean isBack){
        if (isBack){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.user_account_details_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.user_account_details_fragment, fragment);
            fragmentTransaction.commit();
        }

    }
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
