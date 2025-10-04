package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;

public class SimpleRemoveMediaStrategy implements RemoveMediaStrategy {
    private Cart cart;

    public SimpleRemoveMediaStrategy(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void removeMedia(CartMedia media) {
        cart.getListCartMedia().remove(media);
    }
}
