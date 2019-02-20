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
import com.gvtechsolution.fooddeliveryathome.activities.user.EditAddressActivity;
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
                        "Mommo",
                        180,
                        "15-30 Mins",
                        "Wow Mommo",
                        R.drawable.kareemz));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Hakka Noodles",
                        300,
                        "30-40 Mins",
                        "ChaowMan,Haldiram",
                        R.drawable.kfc));
        popularDishesList.add(
                new PopularDishes(
                        1,
                        "Ilish Paturi",
                        300,
                        "30-40 Mins",
                        "BhajohoriManna",
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
                        "XII ZODIAC",
                        "North Indian,Chicken Masala",
                        200,
                        "30-40Min",
                        4.5F,
                        R.drawable.resturant_zodiac));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Roll In Out",
                        "Chinese,Noodles",
                        100,
                        "20-25Min",
                        3.5F,
                        R.drawable.resturant_rollinout));

        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Zareen",
                        "Mughlai,Biriyani",
                        350,
                        "35-50Min",
                        4,
                        R.drawable.kareemz));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "The Farn",
                        "Pestries,Dessert",
                        50,
                        "10-15Min",
                        4.5F,
                        R.drawable.resturant_thefarn));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "The New Red Chilly",
                        "Mommo,Pasta",
                        70,
                        "10-15Min",
                        4,
                        R.drawable.kareemz));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "KFC",
                        "Burger,Lollypop",
                        200,
                        "20-30Min",
                        4.5F,
                        R.drawable.kfc));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Dominos",
                        "Pizza,Burger",
                        250,
                        "10-15Min",
                        4.5F,
                        R.drawable.dominos));
        resturantItemList.add(
                new ResturantItem(
                        1,
                        "Karimz",
                        "Biriyani,North Indian",
                        350,
                        "40-50Min",
                        4,
                        R.drawable.kareemz));

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
            startActivity(intent);
        }
    };
  private View.OnClickListener change_address_clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, EditAddressActivity.class);
            startActivity(intent);
        }

    };

}
