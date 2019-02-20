package com.gvtechsolution.fooddeliveryathome.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;

public class AddAddressBottomSheet extends BottomSheetDialogFragment {
    //private BottomSheetListener mlistener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_address, container,false);
        Button button_close = v.findViewById(R.id.address_close_button);
        button_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             dismiss();
            }
        });
      /*  BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) View.getParent());
        mBehavior.setPeekHeight(screenUtils.getHeight());*/
        return v;
    }
   /* public interface BottomSheetListener {
        void onButtonClicked();

    }*/
}
