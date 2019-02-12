package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.activities.user.RestaurantHomeActivity;
import com.gvtechsolution.fooddeliveryathome.classes.ResturantItem;
import com.makeramen.roundedimageview.RoundedImageView;
import com.gvtechsolution.fooddeliveryathome.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeResturantListAdapter extends RecyclerView.Adapter<HomeResturantListAdapter.HomeResturantListViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ResturantItem> resturantItemList;

    //getting the context and product list with constructor
    public HomeResturantListAdapter(Context mCtx, List<ResturantItem> resturantItemList) {
        this.mCtx = mCtx;
        this.resturantItemList = resturantItemList;
    }

    @Override
    public HomeResturantListAdapter.HomeResturantListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.home_screen_resturant_list,viewGroup,false);
        return new HomeResturantListViewHolder(view);
    }
    /*@Override
    public HomeScreenFoodCategoryVerticalListAdapter.HomeScreenFoodCategoryVerticalListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =inflater.inflate(R.layout.home_screen_food_category_vertical_list_item,viewGroup,false);
        return new HomeScreenFoodCategoryVerticalListViewHolder(view);
    }*/

    @Override
    public void onBindViewHolder(HomeResturantListViewHolder homeResturantListViewHolder, int i) {
        //getting the product of the specified position
        ResturantItem resturant_item = resturantItemList.get(i);

        //binding the data with the viewholder views
        homeResturantListViewHolder.textViewTitle.setText(resturant_item.getTitle());
        homeResturantListViewHolder.textViewCuisine.setText(resturant_item.getCuisine());
        homeResturantListViewHolder.textViewPrice.setText(String.valueOf(resturant_item.getPrice()));
        homeResturantListViewHolder.textViewDeliveryTime.setText(resturant_item.getDelivery_time());
        homeResturantListViewHolder.textViewRating.setRating(resturant_item.getRating());
        //Drawable d = mCtx.getResources().getDrawable(resturant_item.getResturant_image());
        Picasso.get().load(resturant_item.getResturant_image()).into(homeResturantListViewHolder.imageView);
        //homeResturantListViewHolder.imageView.setImageResource(R.drawable.dominos);
            homeResturantListViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(mCtx, RestaurantHomeActivity.class);
                    mCtx.startActivity(intent);
                }
            });
    }


    @Override
    public int getItemCount() {
        return resturantItemList.size();
    }


    class HomeResturantListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewCuisine, textViewPrice,textViewDeliveryTime;
        RatingBar textViewRating;
        RoundedImageView imageView;

        public HomeResturantListViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.popular_resturant_title);
            textViewCuisine = itemView.findViewById(R.id.popular_resturant_cuisine);
            textViewPrice = itemView.findViewById(R.id.popular_resturant_price);
            textViewDeliveryTime = itemView.findViewById(R.id.popular_resturant_delivery_time);
            textViewRating = itemView.findViewById(R.id.popular_resturant_rating);
            imageView = itemView.findViewById(R.id.popular_resturant_image);
        }
    }
}
