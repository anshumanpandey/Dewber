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
public class EventOrderQuotationFragment extends Fragment {


    public EventOrderQuotationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_order_quotation, container, false);
        Button close_button = view.findViewById(R.id.event_order_quotation_close_button);
        close_button.setOnClickListener(close_listener);
        Button save_button = view.findViewById(R.id.event_quotation_save_button);
        save_button.setOnClickListener(save_listener);
        return view;

    }
    private View.OnClickListener close_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //((EventOrderActivity)EventOrderQuotationFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
            ((EventOrderActivity)EventOrderQuotationFragment.this.getActivity()).replaceFragment(new EventOrderNotificationFragment(),false);
        }
    };
    private View.OnClickListener save_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((EventOrderActivity)EventOrderQuotationFragment.this.getActivity()).finish();
        }
    };
}
