package com.gvtechsolution.fooddeliveryathome.activities.business;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.ViewSpecialMenuFragment;

public class BusinessSpecialmenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_specialmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        replaceFragment(new ViewSpecialMenuFragment(), false);
    }
    public void replaceFragment(Fragment fragment, boolean isBack){
        if (isBack){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_business_special_menu, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //navigationView.setChecked(true);
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_business_special_menu, fragment);
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
