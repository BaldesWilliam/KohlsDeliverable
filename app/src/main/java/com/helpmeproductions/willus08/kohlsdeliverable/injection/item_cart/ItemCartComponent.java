package com.helpmeproductions.willus08.kohlsdeliverable.injection.item_cart;




import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart.ItemCart;

import dagger.Component;

@Component(modules = ItemCartModule.class)
public interface ItemCartComponent {
    void inject(ItemCart itemCart);
}
