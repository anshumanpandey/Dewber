package com.gvtechsolution.fooddeliveryathome.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.SettingsActivity;


public class NotificationSettingsFragment extends Fragment {

    public NotificationSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification_settings, container, false);
        Button close_button = view.findViewById(R.id.notification_settings_close_button);
        close_button.setOnClickListener(close_listner);
                return view;
    }

    private View.OnClickListener close_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((SettingsActivity)NotificationSettingsFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };

}
