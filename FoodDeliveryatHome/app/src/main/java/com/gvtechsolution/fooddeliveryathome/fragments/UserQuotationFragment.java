package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.CartActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.RestaurantHomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserQuotationFragment extends Fragment {
    private Context mContext;
    public UserQuotationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_quotation, container, false);
        Button close =view.findViewById(R.id.user_quotation_close_button);
        close.setOnClickListener(close_listener);
        TextView GoToRestaurantHome =(TextView)view.findViewById(R.id.user_quotation_restaurant);
        GoToRestaurantHome.setOnClickListener(restaurant_home_listener);
        Button accept =view.findViewById(R.id.user_quotation_accept_btn);
        accept.setOnClickListener(accept_listener);
        Button decline =view.findViewById(R.id.user_quotation_decline_btn);
        decline.setOnClickListener(decline_listener);
        return view;
    }
    private View.OnClickListener close_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };
    private View.OnClickListener accept_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, CartActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener restaurant_home_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, RestaurantHomeActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener decline_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };
}
