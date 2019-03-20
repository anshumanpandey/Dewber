package com.gvtechsolution.fooddeliveryathome.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.model.Gallery;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Gallery> galleryItem;

    //getting the context and product list with constructor
    public GalleryAdapter(Context mCtx, List<Gallery> galleryItem) {
        this.mCtx = mCtx;
        this.galleryItem = galleryItem;
    }

    @Override
    public GalleryAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.gallery_item_list,viewGroup,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder galleryViewHolder, int i) {
        //getting the product of the specified position
        Gallery gallery_item_list = galleryItem.get(i);
       /* Picasso.with(mContext)
                .load(url).
                placeholder(R.drawable.demo_user)
                .resize(200,200)
                .centerCrop()
                .into(holder.imageView);*/
        String url = gallery_item_list.getCover_image();
        Log.d("url",url);
        Picasso.get().load(url).resize(200,200).centerCrop().into(galleryViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return galleryItem.size();
    }


    class GalleryViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imageView;
        ImageView imgAdd,imgDelete;
        public GalleryViewHolder(View itemView) {
            super(itemView);
            imageView = (RoundedImageView)itemView.findViewById(R.id.gallery_image);
            imgAdd = itemView.findViewById(R.id.img_add);
            imgDelete = itemView.findViewById(R.id.img_delete);

        }
    }
}
