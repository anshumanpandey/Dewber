package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.FilterByFoodItemActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.FoodItemDetailsActivity;
import com.gvtechsolution.fooddeliveryathome.classes.FoodItemCategory;
import com.gvtechsolution.fooddeliveryathome.classes.FoodItemMenu;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildFoodItemListAdapter extends RecyclerView.Adapter<ChildFoodItemListAdapter.ChildFoodItemListViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<FoodItemMenu> foodItemList;

    //getting the context and product list with constructor
    public ChildFoodItemListAdapter(Context mCtx, List<FoodItemMenu> foodItemList) {
        this.mCtx = mCtx;
        this.foodItemList = foodItemList;
    }

    @Override
    public ChildFoodItemListAdapter.ChildFoodItemListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_child_fooditem_list,viewGroup,false);
        return new ChildFoodItemListViewHolder(view);
    }
    /*@Override
    public HomeScreenFoodCategoryVerticalListAdapter.HomeScreenFoodCategoryVerticalListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =inflater.inflate(R.layout.home_screen_food_category_vertical_list_item,viewGroup,false);
        return new HomeScreenFoodCategoryVerticalListViewHolder(view);
    }*/

    @Override
    public void onBindViewHolder(ChildFoodItemListViewHolder childFoodItemListViewHolder, int i) {
        //getting the product of the specified position
        FoodItemMenu food_item = foodItemList.get(i);

        //binding the data with the viewholder views
        childFoodItemListViewHolder.textViewItemName.setText(food_item.getTitle());
        childFoodItemListViewHolder.textViewItemDescription.setText(food_item.getDescription());
        childFoodItemListViewHolder.textViewItemPrice.setText(String.valueOf(food_item.getPrice()));
        Picasso.get().load(food_item.getRestaurant_image()).into(childFoodItemListViewHolder.imageView);
        //homeResturantListViewHolder.imageView.setImageResource(R.drawable.dominos);
        //resturant_home_food_item_image
        childFoodItemListViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(mCtx, FoodItemDetailsActivity.class);
                mCtx.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodItemList.size();
    }


    class ChildFoodItemListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName, textViewItemDescription, textViewItemPrice;
        RoundedImageView imageView;

        public ChildFoodItemListViewHolder(View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.restaurant_home_food_item_name);
            textViewItemDescription = itemView.findViewById(R.id.restaurant_home_food_item_description);
            textViewItemPrice = itemView.findViewById(R.id.restaurant_home_food_item_price);
            imageView = itemView.findViewById(R.id.resturant_home_food_item_image);
        }
    }
}
