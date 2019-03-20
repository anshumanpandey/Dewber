package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.ChangeLocationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAddressFragment extends Fragment {


    public AddAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        Button close_button = view.findViewById(R.id.add_address_close_button);
        close_button.setOnClickListener(close_address_listner);
        Button save_address = view.findViewById(R.id.save_address_button);
        save_address.setOnClickListener(save_address_listener);
        return view;
    }
    private View.OnClickListener close_address_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ChangeLocationActivity)AddAddressFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };
    private View.OnClickListener save_address_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ChangeLocationActivity)AddAddressFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };
}
