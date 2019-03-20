package com.gvtechsolution.fooddeliveryathome.activities.business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.adapter.GalleryAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gallery");
       /* LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mCtx);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parentCartViewHolder.child_cart_food_item_list.setLayoutManager(
                linearLayoutManagerVertical);


        ChildCartAdapter child_food_item_list_adapter =
                new ChildCartAdapter(mCtx, StaticData.getChildCartList());
        parentCartViewHolder.child_cart_food_item_list.setAdapter(
                child_food_item_list_adapter);*/


        RecyclerView recyclerView = findViewById(R.id.gallery_item_list);

        GalleryAdapter galleryAdapter = new GalleryAdapter(this,StaticData.getGalleryItem());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(galleryAdapter);
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
