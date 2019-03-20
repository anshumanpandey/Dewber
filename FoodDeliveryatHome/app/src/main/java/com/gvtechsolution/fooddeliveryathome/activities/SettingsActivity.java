package com.gvtechsolution.fooddeliveryathome.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.BusinessNotificationFragment;
import com.gvtechsolution.fooddeliveryathome.fragments.NotificationsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        String account_fragment = intent.getStringExtra("notification_fragment");
        if(account_fragment.equals("user")){
            replaceFragment(new NotificationsFragment(), false);
        }
        else if(account_fragment.equals("business")){
            replaceFragment(new BusinessNotificationFragment(), false);
        }
        //replaceFragment(new NotificationsFragment(), false);
    }

    public void replaceFragment(Fragment fragment, boolean isBack){
        if (isBack){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_settings, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_settings, fragment);
            fragmentTransaction.commit();
        }


    }
}
