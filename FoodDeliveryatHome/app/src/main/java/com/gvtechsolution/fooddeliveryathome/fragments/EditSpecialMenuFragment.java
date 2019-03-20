package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessSpecialmenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditSpecialMenuFragment extends Fragment {


    public EditSpecialMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_special_menu, container, false);
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Edit Special Menu Item");
        Button add_item =view.findViewById(R.id.edit_special_menu_add_button);
        add_item.setOnClickListener(view_menu_listener);
        return view;
    }
    private View.OnClickListener view_menu_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((BusinessSpecialmenuActivity)EditSpecialMenuFragment.this.getActivity()).getSupportFragmentManager().popBackStack();
        }
    };


}
