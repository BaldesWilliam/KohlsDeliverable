package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details;


import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.data.local.SQLHelper;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder>{
    private List<Item> itemsList = new ArrayList<>();

    ItemDetailsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // sets up the layout for when the device is in landscape mode
        if(parent.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_items_list_land,parent,false);
            return new ViewHolder(view);

        }else{
            // sets up the layout for when the device is in portrait mode
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_items_list,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //sets up all the views in the layout
        final Item detailedItem = itemsList.get(position);
        String itemName = "Item: " + detailedItem.getName();
        String itemPrice = "Price: $"+ detailedItem.getSalePrice();
        String itemStock = "Stock: " + detailedItem.getStock();

        holder.name.setText(itemName);
        holder.price.setText(itemPrice);
        holder.stock.setText(itemStock);
        if (detailedItem.getCustomerRating() != null){
            holder.itemRating.setRating(Float.parseFloat(detailedItem.getCustomerRating()));
        }else {
            holder.itemRating.setVisibility(View.GONE);
        }
        // prevents the user from acidently changing the raiting on their device
        holder.itemRating.setEnabled(false);

        Glide.with(holder.itemView.getContext())
                .load(detailedItem.getLargeImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.missing_item_picture)
                        .override(600,600)
                        .fitCenter())
                .into(holder.itemImage);


        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLHelper db = new SQLHelper(holder.itemView.getContext());
                Log.d(TAG, "onClick: add item");
                db.addItem(detailedItem,1);
                Toast.makeText(holder.itemView.getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                db.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //instantiates all the views for the layouts
        TextView name;
        TextView price;
        TextView stock;
        ImageView itemImage;
        RatingBar itemRating;
        Button addToCart;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvDetailedName);
            price = (TextView) itemView.findViewById(R.id.tvDetailedPrice);
            stock = (TextView) itemView.findViewById(R.id.tvStock);
            itemImage = (ImageView) itemView.findViewById(R.id.ivDetailedItemImage);
            itemRating =  itemView.findViewById(R.id.rbItemRating);
            addToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
