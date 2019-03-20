package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.PendingOrderFragment;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;
import com.gvtechsolution.fooddeliveryathome.model.BusinessOrderDetails;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BusinessOrderHistoryAdapter extends RecyclerView.Adapter<BusinessOrderHistoryAdapter.BusinessOrderHistoryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;
    //we are storing all the products in a listo
    private List<BusinessOrderDetails> businessOrderList;
    private ReplaceFragment listener;

    //getting the context and product list with constructor
    public BusinessOrderHistoryAdapter(Context mCtx, List<BusinessOrderDetails> businessOrderList,ReplaceFragment listener) {
        this.mCtx = mCtx;
        this.businessOrderList = businessOrderList;
        this.listener = listener;

    }

    @Override
    public BusinessOrderHistoryAdapter.BusinessOrderHistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.business_order_history_list,viewGroup,false);
        return new BusinessOrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BusinessOrderHistoryViewHolder businessOrderHistoryViewHolder, int i) {
        //getting the product of the specified position
        BusinessOrderDetails order_list = businessOrderList.get(i);

        //binding the data with the viewholder views
        businessOrderHistoryViewHolder.order_no.setText(order_list.getOrder_no());
        businessOrderHistoryViewHolder.status.setText(order_list.getOrder_status());
        businessOrderHistoryViewHolder.delivery_time.setText(order_list.getDelivery_time());
        businessOrderHistoryViewHolder.price.setText("$"+String.valueOf(order_list.getOrder_price()));
        Picasso.get().load(order_list.getImage()).resize(150,150).into(businessOrderHistoryViewHolder.imageView);
        //homeResturantListViewHolder.imageView.setImageResource(R.drawable.dominos);
        //resturant_home_food_item_image
        businessOrderHistoryViewHolder.status.setOnClickListener(pending_order_details_listener);
    }
    private View.OnClickListener pending_order_details_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onChangeFragment(new PendingOrderFragment(),true);
        }
    };
    @Override
    public int getItemCount() {
        return businessOrderList.size();
    }


    class BusinessOrderHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView order_no,price,status,delivery_time;
        RoundedImageView imageView;
        public BusinessOrderHistoryViewHolder(View itemView) {
            super(itemView);
            order_no = itemView.findViewById(R.id.business_order_history_order_no);
            price = itemView.findViewById(R.id.business_order_history_order_price);
            status = itemView.findViewById(R.id.business_order_history_status);
            delivery_time = itemView.findViewById(R.id.business_order_history_time);
            imageView = itemView.findViewById(R.id.business_order_history_restaurant_image);
        }
    }
}


