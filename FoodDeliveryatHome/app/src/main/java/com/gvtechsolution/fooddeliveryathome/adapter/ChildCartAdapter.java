package com.gvtechsolution.fooddeliveryathome.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.model.CartChildFoodItem;

import java.util.List;

public class ChildCartAdapter extends RecyclerView.Adapter<ChildCartAdapter.ChildCartViewHolder> {


    //this context we will use to inflate the layout
    private Activity mCtx;

    //we are storing all the products in a list
    private List<CartChildFoodItem> cartChildFoodItemList;

    //getting the context and product list with constructor
    public ChildCartAdapter(Activity mCtx, List<CartChildFoodItem> cartChildFoodItemList) {
        this.mCtx = mCtx;
        this.cartChildFoodItemList = cartChildFoodItemList;
    }

    @Override
    public ChildCartAdapter.ChildCartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.child_cart_list,viewGroup,false);
        return new ChildCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChildCartViewHolder childCartViewHolder, int i) {
        //getting the product of the specified position
        CartChildFoodItem food_item_list = cartChildFoodItemList.get(i);

        //binding the data with the viewholder views
        childCartViewHolder.item_name.setText(food_item_list.getItem_name());
        childCartViewHolder.item_amount.setText(String.valueOf(food_item_list.getItem_amount()));

    }

    @Override
    public int getItemCount() {
        return cartChildFoodItemList.size();
    }


    class ChildCartViewHolder extends RecyclerView.ViewHolder {
        TextView item_name,item_amount;
        public ChildCartViewHolder(View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.child_cart_item_name);
            item_amount = itemView.findViewById(R.id.child_cart_item_amount);

        }
    }
}

