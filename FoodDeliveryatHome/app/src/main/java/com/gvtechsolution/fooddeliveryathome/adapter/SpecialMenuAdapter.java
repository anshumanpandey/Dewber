package com.gvtechsolution.fooddeliveryathome.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.model.SpecialMenuItem;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
public class SpecialMenuAdapter extends RecyclerView.Adapter<SpecialMenuAdapter.SpecialMenuViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<SpecialMenuItem> specialMenuItemList;

    //getting the context and product list with constructor
    public SpecialMenuAdapter(Context mCtx, List<SpecialMenuItem> specialMenuItemList) {
        this.mCtx = mCtx;
        this.specialMenuItemList = specialMenuItemList;
    }

    @Override
    public SpecialMenuAdapter.SpecialMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.special_menu_list,viewGroup,false);
        return new SpecialMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecialMenuViewHolder specialMenuViewHolder, int i) {
        //getting the product of the specified position
        SpecialMenuItem special_menu = specialMenuItemList.get(i);
        specialMenuViewHolder.item_name.setText(special_menu.getDish_name());
        specialMenuViewHolder.item_description.setText(special_menu.getDish_description());
        specialMenuViewHolder.item_amount.setText("$"+String.valueOf(special_menu.getDish_price()));
        String url = special_menu.getImage();
        Picasso.get().load(url).resize(130,135).centerCrop().into(specialMenuViewHolder.item_image);

    }
    @Override
    public int getItemCount() {
        return specialMenuItemList.size();
    }
    class SpecialMenuViewHolder extends RecyclerView.ViewHolder {
        TextView item_name,item_amount,item_description;
        RoundedImageView item_image;
        public SpecialMenuViewHolder(View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.special_menu_dish_image);
            item_name = itemView.findViewById(R.id.special_menu_dish_name);
            item_description = itemView.findViewById(R.id.special_menu_dish_description);
            item_amount = itemView.findViewById(R.id.special_menu_dish_price);
        }
    }
}

