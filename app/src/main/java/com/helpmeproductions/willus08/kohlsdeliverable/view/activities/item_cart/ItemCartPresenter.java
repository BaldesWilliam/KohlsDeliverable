package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart;


import android.content.Context;

import com.helpmeproductions.willus08.kohlsdeliverable.data.local.SQLHelper;
import com.helpmeproductions.willus08.kohlsdeliverable.model.CartItems;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.List;

public class ItemCartPresenter implements ItemCartContract.Presenter {
    private ItemCartContract.View view;

    @Override
    public void addView(ItemCartContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public String calculateCost(List<CartItems> items) {
       double totalCost = 0;
        for (CartItems i: items) {
            totalCost += Double.parseDouble(i.getItem().getSalePrice())* i.getAmount();
        }
        return String.format("Total cost is: $ %.2f",totalCost);
    }

    @Override
    public List<CartItems> getCartItemsFromDatabase(Context context) {
        SQLHelper db = new SQLHelper(context);
        List<CartItems> items = db.getCartItems();
        db.close();
        return items;

    }

    @Override
    public void clearCart(Context context) {
        SQLHelper db = new SQLHelper(context);
        db.clear();
        view.showError("Thank you for your Purchase");
        db.close();
    }
}
