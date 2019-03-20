package com.gvtechsolution.fooddeliveryathome.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.EventOrderActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventOrderNotificationFragment extends Fragment {


    public EventOrderNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_order_notification, container, false);
        Button accept_button = view.findViewById(R.id.event_order_accept_btn);
        accept_button.setOnClickListener(accept_listner);
        Button decline_button = view.findViewById(R.id.event_order_decline_btn);
        decline_button.setOnClickListener(decline_listner);
        Button close_button = view.findViewById(R.id.event_order_close_button);
        close_button.setOnClickListener(close_listner);
        return view;
    }
    private View.OnClickListener accept_listner = new View.OnClickListener() {
        @Override
       public void onClick(View v) {
            ((EventOrderActivity)EventOrderNotificationFragment.this.getActivity()).replaceFragment(new EventOrderQuotationFragment(),false);
        }
    };
    private View.OnClickListener decline_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((EventOrderActivity)EventOrderNotificationFragment.this.getActivity()).finish();
        }
    };
    private View.OnClickListener close_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((EventOrderActivity)EventOrderNotificationFragment.this.getActivity()).finish();
        }
    };

}
