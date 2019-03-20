package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.FeedbackFragment;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;
import com.gvtechsolution.fooddeliveryathome.model.UserOrderDetails;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserOrderHistoryAdapter extends RecyclerView.Adapter<UserOrderHistoryAdapter.UserOrderHistoryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<UserOrderDetails> orderList;
    private ReplaceFragment listener;

    //getting the context and product list with constructor
    public UserOrderHistoryAdapter(Context mCtx, List<UserOrderDetails> orderList,ReplaceFragment listener) {
        this.mCtx = mCtx;
        this.orderList = orderList;
        this.listener = listener;
    }

    @Override
    public UserOrderHistoryAdapter.UserOrderHistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.user_order_history_list,viewGroup,false);
        return new UserOrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserOrderHistoryViewHolder userOrderHistoryViewHolder, int i) {
        //getting the product of the specified position
        UserOrderDetails orders = orderList.get(i);
        userOrderHistoryViewHolder.restaurant_name.setText(orders.getRestaurant_name());
        userOrderHistoryViewHolder.restaurant_location.setText(orders.getRestaurant_address());
        userOrderHistoryViewHolder.item_description.setText(orders.getItem_name());
        userOrderHistoryViewHolder.order_date.setText(orders.getOrder_date());
        userOrderHistoryViewHolder.total_amount.setText(orders.getTotal_amount());
        userOrderHistoryViewHolder.order_status.setText(orders.getOrder_status());
        Picasso.get().load(orders.getRestaurant_image()).resize(150,150).into(userOrderHistoryViewHolder.restaurant_image);
        //userOrderHistoryViewHolder.feedback_button.setOnClickListener(feedback_listener);
    }
    /*private View.OnClickListener feedback_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onChangeFragment(new FeedbackFragment(),true);
        }
    };*/

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class UserOrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant_name,restaurant_location,item_description,order_date,total_amount,order_status;
        RoundedImageView restaurant_image;
        Button feedback_button;

        public UserOrderHistoryViewHolder(View itemView) {
            super(itemView);
            restaurant_image = itemView.findViewById(R.id.order_history_restaurant_image);
            restaurant_name = itemView.findViewById(R.id.order_history_restaurant_name);
            restaurant_location = itemView.findViewById(R.id.order_history_restaurant_location);
            item_description = itemView.findViewById(R.id.order_item_description);
            order_date = itemView.findViewById(R.id.order_date_description);
            total_amount = itemView.findViewById(R.id.order_total_amount);
            order_status = itemView.findViewById(R.id.order_status);
            //feedback_button = itemView.findViewById(R.id.feedback_button);
        }
    }
}
