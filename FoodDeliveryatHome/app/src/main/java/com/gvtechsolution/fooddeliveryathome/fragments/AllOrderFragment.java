package com.gvtechsolution.fooddeliveryathome.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessOrderActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.BusinessOrderHistoryAdapter;
import com.gvtechsolution.fooddeliveryathome.data.StaticData;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment implements ReplaceFragment {

    private Context mContext;
    private RecyclerView business_order_recyclerview;
    public AllOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();
        View view= inflater.inflate(R.layout.fragment_all_order, container, false);
        business_order_recyclerview = view.findViewById(R.id.business_order_history_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        business_order_recyclerview.setLayoutManager(linearLayoutManager);

        BusinessOrderHistoryAdapter adapter = new BusinessOrderHistoryAdapter(getActivity(),StaticData.getBusinessOrderHistory(), this);
        business_order_recyclerview.setAdapter(adapter);
      /*  Button close_button = view.findViewById(R.id.order_history_close_button);
        close_button.setOnClickListener(close_order_history_listner);*/
        ((AppCompatActivity)this.getActivity()).getSupportActionBar().setTitle("View All Orders");
        return view;
    }
    @Override
    public void onChangeFragment(Fragment fragment, boolean isBack) {
        ((BusinessOrderActivity)AllOrderFragment.this.getActivity()).replaceOrderFragment(fragment,isBack);
    }

}
