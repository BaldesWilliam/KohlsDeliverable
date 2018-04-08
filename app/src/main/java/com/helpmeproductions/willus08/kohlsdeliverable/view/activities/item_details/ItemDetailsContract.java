package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details;


import android.content.Intent;

import com.helpmeproductions.willus08.kohlsdeliverable.BasePresenter;
import com.helpmeproductions.willus08.kohlsdeliverable.BaseView;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.List;


// sets up methods for the view and its presenter
interface ItemDetailsContract {
    interface View extends BaseView {
        void setupDetailedList(List<Item> itemList, int position);
    }
    interface Presenter extends BasePresenter<View> {
        void extractIntent(Intent intent);
        void loadItems(int Start);
    }
}
