package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list;


import android.support.annotation.NonNull;
import android.util.Log;

import com.helpmeproductions.willus08.kohlsdeliverable.data.remote.RetrofitHelper;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;
import com.helpmeproductions.willus08.kohlsdeliverable.model.ScannedItem;
import com.helpmeproductions.willus08.kohlsdeliverable.model.WalMartItemsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class ItemListPresenter implements ItemListContract.Presenter{
    public ItemListContract.View view;

    public List<Item> itemList = new ArrayList<>();
    @Override
    public void addView(ItemListContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public String generateRandomID() {

        int number =  new Random().nextInt(5);
        switch (number){
            case 0:
                return "55455993";
            case 1:
                return "53733588";
            case 2:
                return "44517682";
            case 3:
                return "28829865";
            case 4:
                return "15570903";
            default:
                return "54151848";
        }

    }

    @Override
    public void scannedCall(String barcode) {
        itemList.clear();
        Call<ScannedItem> scannedItemCall = RetrofitHelper.walmartScanedItemCall(barcode);
        scannedItemCall.enqueue(new retrofit2.Callback<ScannedItem>() {
            @Override
            public void onResponse(@NonNull Call<ScannedItem> call, @NonNull Response<ScannedItem> response) {
                Log.d(TAG, "onResponse: " + response.body());
                Item item = new Item(""+response.body().getItemId(),
                                response.body().getName(),
                               ""+ response.body().getSalePrice(),
                                response.body().getThumbnailImage(),
                                response.body().getLargeImage(),
                                response.body().getCustomerRating(),
                                response.body().getStock());
                itemList.add(item);
                view.setupList(itemList,-1);

            }

            @Override
            public void onFailure(@NonNull Call<ScannedItem> call, @NonNull Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void getItems(String item, final int start) {
        // clears the list when a new item is searched for
        if(start == 1){
        itemList.clear();
        }
        // makes the rest call for lazy the initial list and lazy loading
        Call<WalMartItemsList> walMartItemsListCall = RetrofitHelper.walMartItemsListCall(item,start);
        walMartItemsListCall.enqueue(new retrofit2.Callback<WalMartItemsList>() {

            @Override
            public void onResponse(@NonNull Call<WalMartItemsList> call, @NonNull Response<WalMartItemsList> response) {
                // adds items to the list to be passed back to the view
                for (int i = 0; i < response.body().getItems().size() ; i++) {
                    itemList.add(response.body().getItems().get(i));
                }
                view.setupList(itemList,start);
            }

            @Override
            public void onFailure(@NonNull Call<WalMartItemsList> call, @NonNull Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

}
