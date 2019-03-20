package com.gvtechsolution.fooddeliveryathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAddProfileFragment extends Fragment {


    public UserAddProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add_profile, container, false);
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Add Profile");
        Button edit_profile =view.findViewById(R.id.user_add_profile_edit_button);
        edit_profile.setOnClickListener(edit_user_profile_listener);
        return view;
    }
    private View.OnClickListener edit_user_profile_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ProfileActivity)UserAddProfileFragment.this.getActivity()).replaceAccountFragment(new UserEditProfileFragment(),true);
        }
    };

}
