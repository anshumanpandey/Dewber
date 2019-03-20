package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessSpecialmenuActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.ViewSpecialMenuAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewSpecialMenuFragment extends Fragment implements ReplaceFragment {

    private Context mContext;

    private RecyclerView view_all_item_recyclerview;
    public ViewSpecialMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_view_special_menu, container, false);
        view_all_item_recyclerview = view.findViewById(R.id.view_special_menu_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view_all_item_recyclerview.setLayoutManager(linearLayoutManager);
        ViewSpecialMenuAdapter adapter =
                new ViewSpecialMenuAdapter(getActivity(), StaticData.getSpecialMenuItem(),this);
        view_all_item_recyclerview.setAdapter(adapter);
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("Special Menu");
        //onNavigationItemSelected(navigationView.getMenu().getItem(0));
        Fragment fragment = ((AppCompatActivity)this.getActivity()).getSupportFragmentManager().findFragmentById(R.id.frame_business_special_menu);
        Button add_item = (Button)view.findViewById(R.id.add_special_menu_button);
        add_item.setOnClickListener(edit_menu_listener);
        return view;
    }
    private View.OnClickListener edit_menu_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((BusinessSpecialmenuActivity)ViewSpecialMenuFragment.this.getActivity()).replaceFragment(new EditSpecialMenuFragment(),true);
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void onChangeFragment(Fragment fragment, boolean isBack) {
        ((BusinessSpecialmenuActivity)ViewSpecialMenuFragment.this.getActivity()).replaceFragment(fragment,isBack);
    }
}
