package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_details;

import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details.ItemDetails;

import dagger.Component;


@Component(modules = ItemDetailsModule.class)
public interface ItemDetailsComponent {
    void inject(ItemDetails itemDetails);
}
