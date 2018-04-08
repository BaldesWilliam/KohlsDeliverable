package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_cart;

import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart.ItemCartPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemCartModule {
    @Provides
    public ItemCartPresenter itemCartPresenterProvider(){
        return new ItemCartPresenter();
    }
}
