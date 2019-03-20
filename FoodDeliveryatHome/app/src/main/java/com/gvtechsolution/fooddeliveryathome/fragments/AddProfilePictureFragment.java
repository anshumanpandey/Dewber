package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvtechsolution.fooddeliveryathome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProfilePictureFragment extends Fragment {


    public AddProfilePictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Add Profile Picture");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_profile_picture, container, false);
    }

}
