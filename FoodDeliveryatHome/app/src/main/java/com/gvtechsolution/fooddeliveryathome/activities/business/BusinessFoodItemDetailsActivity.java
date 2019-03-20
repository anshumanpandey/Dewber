package com.gvtechsolution.fooddeliveryathome.activities.business;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.ProfileActivity;
import com.gvtechsolution.fooddeliveryathome.activities.SettingsActivity;
import com.gvtechsolution.fooddeliveryathome.fragments.ViewFoodItemsFragment;

public class BusinessFoodItemDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_food_item_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new ViewFoodItemsFragment(), false);
    }
    public void replaceFragment(Fragment fragment, boolean isBack){
        if (isBack){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_food_item_details, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //navigationView.setChecked(true);
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_food_item_details, fragment);
            fragmentTransaction.commit();
        }


    }

    public void selectMenu(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_food_item_details);
        if (fragment instanceof ViewFoodItemsFragment){
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        selectMenu();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business_food_item_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.business_notification) {
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            intent.putExtra("notification_fragment","business");
            startActivity(intent);
            return true;
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view_all_items) {
            replaceFragment(new ViewFoodItemsFragment(), false);
        } else if (id == R.id.nav_add_cover_picture) {
            //replaceFragment(new AddProfilePictureFragment(), true);
            Intent intent = new Intent(BusinessFoodItemDetailsActivity.this, GalleryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile_details) {
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            intent.putExtra("account_fragment","business");
            startActivity(intent);
        }else if (id == R.id.nav_special_menu) {
            Intent intent = new Intent(getApplicationContext(),BusinessSpecialmenuActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_working_days) {
            Intent intent = new Intent(getApplicationContext(),WorkingDaysActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_business_order_history) {
            Intent intent = new Intent(getApplicationContext(),BusinessOrderActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_change_profile_password) {
            final Dialog change_password_dialog = new Dialog(context);
            change_password_dialog.setContentView(R.layout.change_password);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(change_password_dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            Button cancel = (Button) change_password_dialog.findViewById(R.id.change_password_cancel_button);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_password_dialog.dismiss();
                }
            });
            Button confirm = (Button) change_password_dialog.findViewById(R.id.change_password_confirm_button);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change_password_dialog.dismiss();
                }
            });
            change_password_dialog.show();
            change_password_dialog.getWindow().setAttributes(lp);

        }
        else if (id == R.id.nav_profile_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
