package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.ChangeLocationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditAddressFragment extends Fragment {


    public EditAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        TextView add_address = view.findViewById(R.id.add_address_textview);
        add_address.setOnClickListener(add_address_listener);
        Button close_button = view.findViewById(R.id.edit_address_close_button);
        close_button.setOnClickListener(close_location_listner);
        return view;
    }
    private View.OnClickListener add_address_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ChangeLocationActivity)EditAddressFragment.this.getActivity()).replaceFragment(new AddAddressFragment(),true);
        }
    };
    private View.OnClickListener close_location_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ChangeLocationActivity)EditAddressFragment.this.getActivity()).finish();

        }
    };
}
