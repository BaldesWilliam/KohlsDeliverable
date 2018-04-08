package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.model.CartItems;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;


import java.util.ArrayList;
import java.util.List;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {
    private List<CartItems> itemsList = new ArrayList<>();

    ItemCartAdapter(List<CartItems> itemList) {
        this.itemsList = itemList;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // sets up the layout for the items in the recycler view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_list,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CartItems items = itemsList.get(position);
        Item walMartItem = items.getItem();
        int amount = items.getAmount();
        // fills in values for the layout
        String name = "Name: "+ walMartItem.getName();
        String price ="Price: $"+ walMartItem.getSalePrice();

        holder.itemName.setText(name);
        holder.itemPrice.setText(price);
        holder.amount.setText(""+amount);
        Glide.with(holder.itemView.getContext()).load(walMartItem.getThumbnailImage()).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //instantiates the views of the layout
        TextView itemName;
        TextView itemPrice;
        TextView amount;
        ImageView itemImage;

        ViewHolder(View itemView) {
            super(itemView);
            itemName =  itemView.findViewById(R.id.tvCartItemName);
            itemPrice =  itemView.findViewById(R.id.tvCartItemPrice);
            itemImage = itemView.findViewById(R.id.ivCartItemImage);
            amount = itemView.findViewById(R.id.tvCartItemAmount);
        }
    }
}
