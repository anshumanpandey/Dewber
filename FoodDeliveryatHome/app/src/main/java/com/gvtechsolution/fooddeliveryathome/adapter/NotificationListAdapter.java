package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.NotificationDetailsActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.OrderActivity;
import com.gvtechsolution.fooddeliveryathome.model.NotificationDetails;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationListViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<NotificationDetails> notificationList;

    //getting the context and product list with constructor
    public NotificationListAdapter(Context mCtx, List<NotificationDetails> notificationList) {
        this.mCtx = mCtx;
        this.notificationList = notificationList;
    }

    @Override
    public NotificationListAdapter.NotificationListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.notification_list,viewGroup,false);
        return new NotificationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationListViewHolder notificationListViewHolder, int i) {
        //getting the product of the specified position
        final NotificationDetails notifications = notificationList.get(i);
        notificationListViewHolder.notification_datetime.setText(notifications.getNotification_date());
        notificationListViewHolder.notification_category.setText("***"+notifications.getNotification_category()+"***");
        notificationListViewHolder.notification_description.setText(notifications.getNotification_description());
        //Log.d("notification_category",notification_desc);
            notificationListViewHolder.notification_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String notification_desc = notifications.getNotification_category();
                    if(notification_desc.equals("New Order")) {
                        Intent intent = new Intent(mCtx, OrderActivity.class);
                        mCtx.startActivity(intent);
                    }
                    else if(notification_desc.equals("Special Menu")){
                        Intent intent = new Intent(mCtx, NotificationDetailsActivity.class);
                        intent.putExtra("notification_details_fragment","special_menu");
                        mCtx.startActivity(intent);
                    }
                    else if(notification_desc.equals("New Quotation")){
                        Intent intent = new Intent(mCtx, NotificationDetailsActivity.class);
                        intent.putExtra("notification_details_fragment","new_quotation");
                        mCtx.startActivity(intent);
                    }
                }
            });


      /*  LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mCtx);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        notificationListViewHolder.notification_list.setLayoutManager(
                linearLayoutManagerVertical);


        ChildFoodItemListAdapter child_food_item_list_adapter =
                new ChildFoodItemListAdapter(mCtx, StaticData.getFoodItem());
        parentFoodCategoryListViewHolder.child_food_item_list.setAdapter(
                child_food_item_list_adapter);*/

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class NotificationListViewHolder extends RecyclerView.ViewHolder {

        TextView notification_datetime,notification_category,notification_description;

        public NotificationListViewHolder(View itemView) {
            super(itemView);
            notification_datetime = itemView.findViewById(R.id.notification_datetime);
            notification_category = itemView.findViewById(R.id.notification_category);
            notification_description = itemView.findViewById(R.id.notification_description);
        }
    }
}

