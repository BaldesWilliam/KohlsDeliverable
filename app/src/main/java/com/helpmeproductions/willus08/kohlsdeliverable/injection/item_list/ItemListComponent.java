package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_list;

import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list.ItemList;

import dagger.Component;


@Component(modules = ItemListModule.class)
public interface ItemListComponent {
    void inject(ItemList itemList);
}
