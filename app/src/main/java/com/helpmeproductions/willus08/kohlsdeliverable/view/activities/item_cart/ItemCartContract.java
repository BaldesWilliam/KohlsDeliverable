package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart;


import android.content.Context;

import com.helpmeproductions.willus08.kohlsdeliverable.BasePresenter;
import com.helpmeproductions.willus08.kohlsdeliverable.BaseView;
import com.helpmeproductions.willus08.kohlsdeliverable.model.CartItems;

import java.util.List;

public interface ItemCartContract {
    interface View extends BaseView{
        void setupAdapter();
    }

    interface Presenter extends BasePresenter<View>{
        String calculateCost(List<CartItems> items);
        List<CartItems> getCartItemsFromDatabase(Context context);
        void clearCart(Context context);
    }
}
