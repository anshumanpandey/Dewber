package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessFoodItemDetailsActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.ViewFoodItemListAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFoodItemsFragment extends Fragment implements ReplaceFragment {

    private Context mContext;

    private RecyclerView view_all_item_recyclerview;
    public ViewFoodItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_view_food_items, container, false);
        view_all_item_recyclerview = view.findViewById(R.id.view_all_food_item_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view_all_item_recyclerview.setLayoutManager(linearLayoutManager);
        ViewFoodItemListAdapter adapter =
                new ViewFoodItemListAdapter(getActivity(), StaticData.getAllFoodItem(),this);
        view_all_item_recyclerview.setAdapter(adapter);
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Menu");
        //onNavigationItemSelected(navigationView.getMenu().getItem(0));

        Fragment fragment = ((AppCompatActivity)this.getActivity()).getSupportFragmentManager().findFragmentById(R.id.frame_food_item_details);
        if (fragment instanceof ViewFoodItemsFragment){
            ((BusinessFoodItemDetailsActivity)this.getActivity()).selectMenu();
        }
        return view;
    }
    public void onChangeFragment(Fragment fragment, boolean isBack) {
        ((BusinessFoodItemDetailsActivity)ViewFoodItemsFragment.this.getActivity()).replaceFragment(fragment,isBack);
    }

}
