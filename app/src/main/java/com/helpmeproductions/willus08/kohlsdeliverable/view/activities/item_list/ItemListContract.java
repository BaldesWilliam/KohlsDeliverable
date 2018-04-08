package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list;


import com.helpmeproductions.willus08.kohlsdeliverable.BasePresenter;
import com.helpmeproductions.willus08.kohlsdeliverable.BaseView;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.List;



// sets up methods for the view and its presenter
interface ItemListContract {
    interface View extends BaseView {
        void setupList(List<Item> itemList, int posiiton);
        void goToDetailedItems(int position, List<Item> itemList);
    }
    interface Presenter extends BasePresenter<View> {
        String generateRandomID();
        void scannedCall(String barcode);
        void getItems(String item, int start);
    }
}
