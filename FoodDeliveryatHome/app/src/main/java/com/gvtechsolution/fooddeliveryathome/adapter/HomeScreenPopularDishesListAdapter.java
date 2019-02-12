package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.PopularDishes;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class HomeScreenPopularDishesListAdapter extends RecyclerView.Adapter<HomeScreenPopularDishesListAdapter.HomeScreenPopularDishesListViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<PopularDishes> popularFoodItemList;

    //getting the context and product list with constructor
    public HomeScreenPopularDishesListAdapter(Context mCtx, List<PopularDishes> popularFoodItemList) {
        this.mCtx = mCtx;
        this.popularFoodItemList = popularFoodItemList;
    }

    @Override
    public HomeScreenPopularDishesListAdapter.HomeScreenPopularDishesListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.home_screen_popular_dishes_list,viewGroup,false);
        return new HomeScreenPopularDishesListViewHolder(view);
    }
    /*@Override
    public HomeScreenFoodCategoryVerticalListAdapter.HomeScreenFoodCategoryVerticalListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =inflater.inflate(R.layout.home_screen_food_category_vertical_list_item,viewGroup,false);
        return new HomeScreenFoodCategoryVerticalListViewHolder(view);
    }*/

    @Override
    public void onBindViewHolder(HomeScreenPopularDishesListViewHolder homeScreenPopularDishesListViewHolder, int i) {
        //getting the product of the specified position
        PopularDishes popular_dishes = popularFoodItemList.get(i);

        //binding the data with the viewholder views
        homeScreenPopularDishesListViewHolder.textViewTitle.setText(popular_dishes.getTitle());
        homeScreenPopularDishesListViewHolder.textViewPrice.setText(String.valueOf(popular_dishes.getPrice()));
        homeScreenPopularDishesListViewHolder.textViewDeliveryTime.setText(popular_dishes.getDelivery_time());
        homeScreenPopularDishesListViewHolder.textViewRestaurantName.setText(popular_dishes.getRestaurant_name());
        homeScreenPopularDishesListViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(popular_dishes.getRestaurant_image()));

    }


    @Override
    public int getItemCount() {
        return popularFoodItemList.size();
    }


    class HomeScreenPopularDishesListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle,textViewPrice,textViewDeliveryTime,textViewRestaurantName;
        RoundedImageView imageView;

        public HomeScreenPopularDishesListViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.popular_dishes_title);
            textViewPrice = itemView.findViewById(R.id.popular_dishes_price);
            textViewDeliveryTime = itemView.findViewById(R.id.popular_dishes_deliverytime);
            textViewRestaurantName = itemView.findViewById(R.id.popular_dishes_restaurant_name);
            imageView = itemView.findViewById(R.id.popular_dishes_image);
        }
    }
}
