package com.gvtechsolution.fooddeliveryathome.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.CartParentFoodItem;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ParentCartAdapter extends RecyclerView.Adapter<ParentCartAdapter.ParentCartViewHolder> {


    //this context we will use to inflate the layout
    private Activity mCtx;

    //we are storing all the products in a list
    private List<CartParentFoodItem> parentCartList;

    //getting the context and product list with constructor
    public ParentCartAdapter(Activity mCtx, List<CartParentFoodItem> parentCartList) {
        this.mCtx = mCtx;
        this.parentCartList = parentCartList;
    }

    @Override
    public ParentCartAdapter.ParentCartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.parent_cart_list,viewGroup,false);
        return new ParentCartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ParentCartViewHolder parentCartViewHolder, int i) {
        //getting the product of the specified position
        CartParentFoodItem food_item = parentCartList.get(i);

        //binding the data with the viewholder views
        parentCartViewHolder.name.setText(food_item.getName());
        parentCartViewHolder.location.setText(food_item.getLocation());
        parentCartViewHolder.subtotal.setText(String.valueOf(food_item.getSubtotal()));
        parentCartViewHolder.tax.setText(String.valueOf(food_item.getTax()));
        parentCartViewHolder.delivery_charge.setText(String.valueOf(food_item.getDelivery_charge()));
        parentCartViewHolder.grand_subtotal.setText(String.valueOf(food_item.getGrand_subtotal()));
        Picasso.get().load(food_item.getImage()).into(parentCartViewHolder.imageView);

        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mCtx);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        parentCartViewHolder.child_cart_food_item_list.setLayoutManager(
                linearLayoutManagerVertical);


        ChildCartAdapter child_food_item_list_adapter =
                new ChildCartAdapter(mCtx, StaticData.getChildCartList());
        parentCartViewHolder.child_cart_food_item_list.setAdapter(
                child_food_item_list_adapter);

    }


    @Override
    public int getItemCount() {
        return parentCartList.size();
    }


    class ParentCartViewHolder extends RecyclerView.ViewHolder {

        TextView name,location,subtotal,tax,delivery_charge,grand_subtotal;
        RoundedImageView imageView;
        RecyclerView child_cart_food_item_list;
        public ParentCartViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_restaurant_name);
            location = itemView.findViewById(R.id.cart_restaurant_location);
            subtotal = itemView.findViewById(R.id.cart_food_item_subtotal_amount);
            tax = itemView.findViewById(R.id.cart_food_item_tax_amount);
            delivery_charge = itemView.findViewById(R.id.cart_food_item_delivery_charge_amount);
            grand_subtotal = itemView.findViewById(R.id.cart_food_item_grand_subtotal_amount);
            imageView = itemView.findViewById(R.id.restaurant_cart_image);
            child_cart_food_item_list = (RecyclerView) itemView.findViewById(R.id.child_cart_list);


        }
    }
}

