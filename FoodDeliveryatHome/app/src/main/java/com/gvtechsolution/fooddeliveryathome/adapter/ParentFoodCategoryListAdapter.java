package com.gvtechsolution.fooddeliveryathome.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.FoodItemCategory;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;

import java.util.List;

public class ParentFoodCategoryListAdapter extends RecyclerView.Adapter<ParentFoodCategoryListAdapter.ParentFoodCategoryListViewHolder> {


    //this context we will use to inflate the layout
    private Activity mCtx;

    //we are storing all the products in a list
    private List<FoodItemCategory> categoryNameList;

    //getting the context and product list with constructor
    public ParentFoodCategoryListAdapter(Activity mCtx, List<FoodItemCategory> categoryNameList) {
        this.mCtx = mCtx;
        this.categoryNameList = categoryNameList;
    }

    @Override
    public ParentFoodCategoryListAdapter.ParentFoodCategoryListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_parent_fooditem_list,viewGroup,false);
        return new ParentFoodCategoryListViewHolder(view);
    }
    /*@Override
    public HomeScreenFoodCategoryVerticalListAdapter.HomeScreenFoodCategoryVerticalListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view =inflater.inflate(R.layout.home_screen_food_category_vertical_list_item,viewGroup,false);
        return new HomeScreenFoodCategoryVerticalListViewHolder(view);
    }*/

    @Override
    public void onBindViewHolder(ParentFoodCategoryListViewHolder parentFoodCategoryListViewHolder, int i) {
        //getting the product of the specified position
        FoodItemCategory food_item = categoryNameList.get(i);

        //binding the data with the viewholder views
        parentFoodCategoryListViewHolder.textViewItemName.setText(food_item.getItem_category());


        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mCtx);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parentFoodCategoryListViewHolder.child_food_item_list.setLayoutManager(
                linearLayoutManagerVertical);


        ChildFoodItemListAdapter child_food_item_list_adapter =
                new ChildFoodItemListAdapter(mCtx, StaticData.getFoodItem());
        parentFoodCategoryListViewHolder.child_food_item_list.setAdapter(
                child_food_item_list_adapter);

    }


    @Override
    public int getItemCount() {
        return categoryNameList.size();
    }


    class ParentFoodCategoryListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName;
        RecyclerView child_food_item_list;

        public ParentFoodCategoryListViewHolder(View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.food_item_category);
            child_food_item_list = (RecyclerView) itemView.findViewById(R.id.restaurant_child_recyclerview);

        }
    }
}

