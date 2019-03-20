package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.PaymentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentOptionsFragment extends Fragment {


    public PaymentOptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_options, container, false);
        Button goToCardDetails = view.findViewById(R.id.button_payment_options);
        goToCardDetails.setOnClickListener(payment_details_listner);
        Button close_ = view.findViewById(R.id.close_payment_options);
        close_.setOnClickListener(close_listner);
        return view;
    }
    private View.OnClickListener payment_details_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((PaymentActivity)PaymentOptionsFragment.this.getActivity()).replaceFragment(new PaymentCardDetailsFragment(),true);
        }
    };
    private View.OnClickListener close_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((PaymentActivity)PaymentOptionsFragment.this.getActivity()).finish();
        }
    };

}
