package com.helpmeproductions.willus08.kohlsdeliverable.model;



public class CartItems {
 private Item item;
 private int amount;

    public CartItems(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
