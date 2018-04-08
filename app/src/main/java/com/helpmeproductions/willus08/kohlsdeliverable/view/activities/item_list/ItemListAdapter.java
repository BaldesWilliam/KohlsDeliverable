package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private List<Item> itemsList = new ArrayList<>();
    ItemListAdapter(List<Item> itemList) {
        this.itemsList = itemList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // sets up the layout for the items in the recycler view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item walMartItem = itemsList.get(position);
        // fills in values for the layout
        String name = "Name: "+ walMartItem.getName();
        String price ="Price: $"+ walMartItem.getSalePrice();
        holder.itemName.setText(name);
        holder.itemPrice.setText(price);
        Glide.with(holder.itemView.getContext()).load(walMartItem.getThumbnailImage()).into(holder.itemImage);

        // sets up the listener for when an item is clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + walMartItem.getMsrp());
                Intent broadcast = new Intent("items");
                broadcast.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) itemsList);
                broadcast.putExtra("position",holder.getAdapterPosition());
                holder.itemView.getContext().sendBroadcast(broadcast);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //instantiates the views of the layout
        TextView itemName;
        TextView itemPrice;
        ImageView itemImage;
        ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.tvItemName);
            itemPrice = (TextView) itemView.findViewById(R.id.tvItemPrice);
            itemImage = (ImageView) itemView.findViewById(R.id.ivItemImage);
        }
    }
}
