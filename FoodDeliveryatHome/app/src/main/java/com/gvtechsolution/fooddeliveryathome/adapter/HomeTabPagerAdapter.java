package com.gvtechsolution.fooddeliveryathome.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gvtechsolution.fooddeliveryathome.fragments.HomeFragment;

public class HomeTabPagerAdapter extends FragmentStatePagerAdapter {

    public HomeTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();

            case 1:
                return new HomeFragment();

        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
