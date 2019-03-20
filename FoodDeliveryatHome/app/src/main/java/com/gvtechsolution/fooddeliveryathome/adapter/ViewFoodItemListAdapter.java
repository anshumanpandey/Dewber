package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.CustomizeItemFragment;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;
import com.gvtechsolution.fooddeliveryathome.model.BusinessFoodItemList;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ViewFoodItemListAdapter extends RecyclerView.Adapter<ViewFoodItemListAdapter.ViewFoodItemListViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<BusinessFoodItemList> foodItemList;
    private ReplaceFragment listener;

    //getting the context and product list with constructor
    public ViewFoodItemListAdapter(Context mCtx, List<BusinessFoodItemList> foodItemList,ReplaceFragment listener) {
        this.mCtx = mCtx;
        this.foodItemList = foodItemList;
        this.listener = listener;
    }

    @Override
    public ViewFoodItemListAdapter.ViewFoodItemListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_all_food_item_list,viewGroup,false);
        return new ViewFoodItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewFoodItemListViewHolder viewFoodItemListViewHolder, int i) {
        //getting the product of the specified position
        BusinessFoodItemList food_item = foodItemList.get(i);
        //binding the data with the viewholder views
        viewFoodItemListViewHolder.textViewItemName.setText(food_item.getItem_name());
        viewFoodItemListViewHolder.textViewItemDescription.setText(food_item.getItem_description());
        viewFoodItemListViewHolder.textViewItemPrice.setText("$"+String.valueOf(food_item.getPrice()));
        //viewFoodItemListViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(food_item.getImage()));
        Picasso.get().load(food_item.getImage()).into(viewFoodItemListViewHolder.imageView);
        //view_food_item_add
        viewFoodItemListViewHolder.textCustomize.setOnClickListener(customize_listener);
        viewFoodItemListViewHolder.add.setOnClickListener(add_listener);
    }
    private View.OnClickListener add_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onChangeFragment(new CustomizeItemFragment(),true);
        }
    };
    private View.OnClickListener customize_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onChangeFragment(new CustomizeItemFragment(),true);
        }
    };


    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    class ViewFoodItemListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName, textViewItemDescription, textViewItemPrice,textCustomize;
        RoundedImageView imageView;
        Button add;

        public ViewFoodItemListViewHolder(View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.view_food_item_name);
            textViewItemDescription = itemView.findViewById(R.id.view_food_item_description);
            textViewItemPrice = itemView.findViewById(R.id.view_food_item_price);
            textCustomize = itemView.findViewById(R.id.view_food_item_customize);
            imageView = itemView.findViewById(R.id.view_food_item_image);
            add = itemView.findViewById(R.id.view_food_item_add);
        }
    }
}


