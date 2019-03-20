package com.gvtechsolution.fooddeliveryathome.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.SettingsActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.HomeActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.ChildCartAdapter;
import com.gvtechsolution.fooddeliveryathome.adapter.NotificationListAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;


public class NotificationsFragment extends Fragment {
    private Context mContext;

    private RecyclerView notification_list_recyclerview;
    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        /* set Adapter for Notification List */
        notification_list_recyclerview = view.findViewById(R.id.notification_list);
        TextView view_settings = view.findViewById(R.id.button_view_notification);
        view_settings.setOnClickListener(notification_clickListener);
        Button close_button = view.findViewById(R.id.notification_close_button);
        close_button.setOnClickListener(close_notification_listner);
        return view;
    }
    private View.OnClickListener notification_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((SettingsActivity)NotificationsFragment.this.getActivity()).replaceFragment(new NotificationSettingsFragment(),true);
        }
    };
    private View.OnClickListener close_notification_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);*/
            ((SettingsActivity)NotificationsFragment.this.getActivity()).finish();

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        notification_list_recyclerview.setLayoutManager(linearLayoutManager);
        NotificationListAdapter notification_list_adapter =
                new NotificationListAdapter(mContext, StaticData.getNotificationList());
        notification_list_recyclerview.setAdapter(notification_list_adapter);
    }
}
