package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_list;

import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list.ItemListPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class ItemListModule {
    @Provides
    public ItemListPresenter itemListPresenterProvider(){
        return new ItemListPresenter();
    }
}
