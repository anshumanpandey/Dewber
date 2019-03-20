package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.UserQuotationFragment;
import com.gvtechsolution.fooddeliveryathome.fragments.UserSpecialMenuFragment;

public class NotificationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Intent intent = getIntent();
        String account_fragment = intent.getStringExtra("notification_details_fragment");

        if(account_fragment.equals("special_menu")){
            replaceFragment(new UserSpecialMenuFragment(), false);
        }
        else if(account_fragment.equals("new_quotation")){
            replaceFragment(new UserQuotationFragment(), false);
        }
    }
    public void replaceFragment(Fragment fragment, boolean isBack){
        if (isBack){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_notification_details, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_notification_details, fragment);
            fragmentTransaction.commit();
        }

    }
}
