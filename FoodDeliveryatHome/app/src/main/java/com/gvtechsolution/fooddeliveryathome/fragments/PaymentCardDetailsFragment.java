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
public class PaymentCardDetailsFragment extends Fragment {


    public PaymentCardDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_card_details, container, false);
        Button close_button = view.findViewById(R.id.close_Card_details);
        close_button.setOnClickListener(close_listner);
        return view;
    }
    private View.OnClickListener close_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((PaymentActivity)PaymentCardDetailsFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };

}
