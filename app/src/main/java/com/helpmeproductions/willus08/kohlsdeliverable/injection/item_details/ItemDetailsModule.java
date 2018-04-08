package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_details;


import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details.ItemDetailsPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class ItemDetailsModule {
    @Provides
    public ItemDetailsPresenter itemDetailsPresenterProvider(){
        return new ItemDetailsPresenter();
    }
}
