package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.SettingsActivity;
import com.gvtechsolution.fooddeliveryathome.activities.business.EventOrderActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessNotificationFragment extends Fragment {


    public BusinessNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_notification, container, false);
        TextView view_settings = view.findViewById(R.id.business_button_view_notification);
        view_settings.setOnClickListener(notification_clickListener);
        TextView goToDetails =(TextView)view.findViewById(R.id.business_notification_category);
        goToDetails.setOnClickListener(notification_details_istener);
        Button close_button = view.findViewById(R.id.business_notification_close_button);
        close_button.setOnClickListener(close_notification_listner);
        return view;
    }
    private View.OnClickListener notification_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((SettingsActivity)BusinessNotificationFragment.this.getActivity()).replaceFragment(new NotificationSettingsFragment(),true);
        }
    };
    private View.OnClickListener notification_details_istener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), EventOrderActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener close_notification_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);*/
            ((SettingsActivity)BusinessNotificationFragment.this.getActivity()).finish();
        }
    };
}
