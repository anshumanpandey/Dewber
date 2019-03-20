package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.OrderActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingOrderFragment extends Fragment {


    public PendingOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Pending Order Details");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order,container, false);
        Button accept_button = view.findViewById(R.id.notification_close_button);
        accept_button.setOnClickListener(accept_listner);
        Button decline_button = view.findViewById(R.id.notification_close_button);
        decline_button.setOnClickListener(decline_listner);
        Button close_button = view.findViewById(R.id.notification_close_button);
        close_button.setOnClickListener(close_listner);
        return view;
    }
    private View.OnClickListener accept_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OrderActivity)PendingOrderFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };
    private View.OnClickListener decline_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OrderActivity)PendingOrderFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }

    };
    private View.OnClickListener close_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OrderActivity)PendingOrderFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }

    };
}
