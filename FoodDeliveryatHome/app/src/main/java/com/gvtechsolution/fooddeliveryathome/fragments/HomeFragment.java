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
import android.widget.EditText;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.user.ChangeLocationActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.FilterByFoodItemActivity;
import com.gvtechsolution.fooddeliveryathome.adapter.HomeResturantListAdapter;
import com.gvtechsolution.fooddeliveryathome.adapter.HomeScreenPopularDishesListAdapter;
import com.gvtechsolution.fooddeliveryathome.model.PopularDishes;
import com.gvtechsolution.fooddeliveryathome.model.ResturantItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    List<ResturantItem> resturantItemList;
    List<PopularDishes> popularDishesList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //View resturantListView = inflater.inflate(R.layout.home_screen_resturant_list, container, false);

        /*onclick event start*/
        EditText search_text = view.findViewById(R.id.home_search_text);
        search_text.setOnClickListener(search_list_clickListener);
        Button change_location_button =view.findViewById(R.id.home_screen_change_button);
        change_location_button.setOnClickListener(change_address_clicklistener);
        /*onclick event end*/

        RecyclerView home_popular_dishes_list = (RecyclerView) view.findViewById(R.id.home_popular_dishes_recyclerview);
        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(mContext);
        layoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_popular_dishes_list.setLayoutManager(layoutManagerHorizontal);


        popularDishesList =new ArrayList<>();
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Mutton Biriyani",
                        300,
                        "30-40 Mins",
                        "Arsalan,Chinarpark",
                        R.drawable.mutton_biryani));

        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Kabab Roll",
                        80,
                        "10-20 Mins",
                        "Roll In Out,Chinarpark",
                        R.drawable.resturant_rollinout));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Momo",
                        180,
                        "15-30 Mins",
                        "Wow Momo",
                        R.drawable.kareems));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Hakka Noodles",
                        300,
                        "30-40 Mins",
                        "ChowMan,Haldiram",
                        R.drawable.kfc));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Ilish Paturi",
                        300,
                        "30-40 Mins",
                        "Bhojohori Manna",
                        R.drawable.sorshebatta_illish));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Chicken Rezalla",
                        150,
                        "20-30 Mins",
                        "Aminia,Newtown",
                        R.drawable.chicken_rezalla));


        //creating recyclerview adapter
        HomeScreenPopularDishesListAdapter popular_dishes_adapter = new HomeScreenPopularDishesListAdapter(mContext, popularDishesList);
        home_popular_dishes_list.setAdapter(popular_dishes_adapter);

        //getting the recyclerview from xml
        RecyclerView home_popular_resturant_list = (RecyclerView) view.findViewById(R.id.home_popular_restaurant_recyclerview);
        home_popular_resturant_list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(mContext);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        home_popular_resturant_list.setLayoutManager(linearLayoutManagerVertical);
        resturantItemList = new ArrayList<>();

        //adding some items to our list
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "XII Zodiac",
                        "North Indian,Chicken Masala",
                        200,
                        "30-40 Min",
                        4.5F,
                        "2 km",
                        R.drawable.resturant_zodiac));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Roll In Out",
                        "Chinese,Noodles",
                        100,
                        "20-25 Min",
                        3.5F,
                        "2.5 km",
                        R.drawable.resturant_rollinout));

        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Zaareen",
                        "Mughlai,Biryani",
                        350,
                        "35-50 Min",
                        4,
                        "3 km",
                        R.drawable.kareems));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "The Fern",
                        "Pastries,Dessert",
                        50,
                        "10-15 Min",
                        4.5F,
                        "3 km",
                        R.drawable.resturant_thefarn));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "The New Red Chilly",
                        "Momo,Pasta",
                        70,
                        "10-15 Min",
                        4,
                        "2.5 km",
                        R.drawable.kareems));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "KFC",
                        "Burger,Lollipop",
                        200,
                        "20-30 Min",
                        4.5F,
                        "3.5 km",
                        R.drawable.kfc));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Domino's Pizza",
                        "Pizza,Burger",
                        250,
                        "10-15 Min",
                        4.5F,
                        "4.5 km",
                        R.drawable.dominos));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Kareem's",
                        "Biryani,North Indian",
                        350,
                        "40-50 Min",
                        4,
                        "4 km",
                        R.drawable.kareems));

        //creating recyclerview adapter
        HomeResturantListAdapter adapter = new HomeResturantListAdapter(mContext, resturantItemList);

        //setting adapter to recyclerview
        home_popular_resturant_list.setAdapter(adapter);
        return view;
    }

    private View.OnClickListener search_list_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, FilterByFoodItemActivity.class);
            startActivity(intent); }
    };
  private View.OnClickListener change_address_clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ChangeLocationActivity.class);
            startActivity(intent);
        }

    };

}
