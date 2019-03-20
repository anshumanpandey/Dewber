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

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.HomeActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.OrderActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.UserOrderHistoryAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserOrderHistoryFragment extends Fragment implements ReplaceFragment {
    private Context mContext;
    private RecyclerView order_history_recyclerview;
    public UserOrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_user_order_history, container, false);
        order_history_recyclerview = view.findViewById(R.id.user_order_history_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        order_history_recyclerview.setLayoutManager(linearLayoutManager);

        UserOrderHistoryAdapter adapter = new UserOrderHistoryAdapter(getActivity(),StaticData.getOrderHistoryList(), this);
        order_history_recyclerview.setAdapter(adapter);
        //Button close_button = view.findViewById(R.id.user_order_history_close_button);
        //close_button.setOnClickListener(close_order_history_listner);
        return view;
    }
   /* private View.OnClickListener close_order_history_listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
    };*/

    @Override
    public void onChangeFragment(Fragment fragment, boolean isBack) {
        ((OrderActivity)UserOrderHistoryFragment.this.getActivity()).replaceOrderFragment(fragment,isBack);
    }
}
