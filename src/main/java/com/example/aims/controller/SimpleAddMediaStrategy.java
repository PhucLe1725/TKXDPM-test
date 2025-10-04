package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

public class SimpleAddMediaStrategy implements AddMediaStrategy {
    private Cart cart;

    public SimpleAddMediaStrategy(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addMedia(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException {
        cart.addMediaToCart(media);
    }
}
