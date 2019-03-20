package com.gvtechsolution.fooddeliveryathome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.fragments.EditSpecialMenuFragment;
import com.gvtechsolution.fooddeliveryathome.listener.ReplaceFragment;
import com.gvtechsolution.fooddeliveryathome.model.BusinessSpecialMenuItem;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
public class ViewSpecialMenuAdapter extends RecyclerView.Adapter<ViewSpecialMenuAdapter.SpecialMenuViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;
    //we are storing all the products in a list
    private List<BusinessSpecialMenuItem> specialMenu;
    private ReplaceFragment listener;
    //getting the context and product list with constructor
    public ViewSpecialMenuAdapter(Context mCtx, List<BusinessSpecialMenuItem> specialMenu,ReplaceFragment listener ) {
        this.mCtx = mCtx;
        this.specialMenu = specialMenu;
        this.listener = listener;
    }

    @Override
    public ViewSpecialMenuAdapter.SpecialMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_special_menu_list,viewGroup,false);
        return new SpecialMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecialMenuViewHolder specialMenuViewHolder, int i) {
        //getting the product of the specified position
        BusinessSpecialMenuItem food_item = specialMenu.get(i);
        //binding the data with the viewholder views
        specialMenuViewHolder.textViewItemName.setText(food_item.getItem_name());
        specialMenuViewHolder.textViewItemDescription.setText(food_item.getItem_description());
        specialMenuViewHolder.textViewItemPrice.setText("$"+String.valueOf(food_item.getPrice()));
        String url = food_item.getImage();
        Picasso.get().load(url).resize(100,100).centerCrop().into(specialMenuViewHolder.imageView);

        specialMenuViewHolder.edit.setOnClickListener(edit_listener);
    }
   private View.OnClickListener edit_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onChangeFragment(new EditSpecialMenuFragment(),true);
        }
    };

    @Override
    public int getItemCount() {
        return specialMenu.size();
    }

    class SpecialMenuViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName, textViewItemDescription, textViewItemPrice;
        RoundedImageView imageView;
        Button edit;

        public SpecialMenuViewHolder(View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.view_special_menu_item_name);
            textViewItemDescription = itemView.findViewById(R.id.view_special_menu_item_description);
            textViewItemPrice = itemView.findViewById(R.id.view_special_menu_item_price);
            imageView = itemView.findViewById(R.id.view_special_menu_image);
            edit = itemView.findViewById(R.id.view_special_menu_item_edit);
        }
    }
}
