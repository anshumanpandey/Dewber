package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessFoodItemDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomizeItemFragment extends Fragment {


    public CustomizeItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Add Food Items");
        //add_food_item
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customize_item, container, false);
        Button add_item =view.findViewById(R.id.add_food_item);
        add_item.setOnClickListener(add_itemlistener);
        return view;
    }
    private View.OnClickListener add_itemlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((BusinessFoodItemDetailsActivity)CustomizeItemFragment.this.getActivity()).replaceFragment(new ViewFoodItemsFragment(),true);
        }
    };
}
