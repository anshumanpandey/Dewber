package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.RestaurantHomeActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.SpecialMenuAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSpecialMenuFragment extends Fragment {
    private Context mContext;

    public UserSpecialMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_user_special_menu, container, false);
        RecyclerView special_menu = (RecyclerView)view.findViewById(R.id.special_menu_list);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mContext);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        special_menu.setLayoutManager(linearLayoutManagerVertical);
        SpecialMenuAdapter adapter = new SpecialMenuAdapter(getActivity(),StaticData.getSpecialMenu());
        special_menu.setAdapter(adapter);
        TextView goToRestaurantHome = (TextView)view.findViewById(R.id.special_menu_restaurant);
        goToRestaurantHome.setOnClickListener(restaurant_listener);
        Button close =view.findViewById(R.id.special_menu_close_button);
        close.setOnClickListener(close_listener);
        return view;
    }
    private View.OnClickListener restaurant_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, RestaurantHomeActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener close_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };

}
