package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.adapter.HomeTabPagerAdapter;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.homeTabPager);

        tabLayout.addTab(tabLayout.newTab().setText("Delivery"));
        tabLayout.addTab(tabLayout.newTab().setText("Pick Up"));

        HomeTabPagerAdapter homeTabPagerAdapter = new HomeTabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeTabPagerAdapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        RecyclerView home_resturant_list = (RecyclerView) findViewById(R.id.home_resturant_list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        home_resturant_list.setLayoutManager(layoutManager);
//        String[] languages = {"Resturant1","Resturant2","Resturant3","Resturant4","Resturant5","Resturant6","Resturant6","Resturant6","Resturant6","Resturant6","Resturant6","Resturant6","Resturant6","Resturant6"};
//        home_resturant_list.setAdapter(new HomeScreenResturantHorizontalListAdapter(languages));
//
//        RecyclerView home_food_category_list = (RecyclerView) findViewById(R.id.home_food_category_list);
//        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
//        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
//        home_food_category_list.setLayoutManager(linearLayoutManagerVertical);
//
//        String[] foodcategory = {"XII ZODIAC","Roll In Out","Zareen","The Farn","The New Red Chilly","Subway","KFC","6 Bullygunge Place","cat1","cat1","cat1","cat1","cat1","cat1"};
//        home_food_category_list.setAdapter(new HomeScreenFoodCategoryVerticalListAdapter(getApplicationContext(),foodcategory));
//        ImageView home_banner = (ImageView)findViewById(R.id.home_screen_banner);
//        home_banner.setOnClickListener(resturant_list_clickListener);
//
//        EditText search_text = findViewById(R.id.home_search_text);
//        search_text.setOnClickListener(search_list_clickListener);



    }


    /*private View.OnClickListener resturant_list_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, FilterByFoodItemActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener search_list_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, SearchListActivity.class);
            startActivity(intent);
        }
    };*/

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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orderHistory) {

        } else if (id == R.id.nav_changePassword) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
