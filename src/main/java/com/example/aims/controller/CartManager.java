package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

import java.util.List;

public class CartManager {
    private Cart cart;
    private List<CartMedia> listCartMedia;

    public CartManager(Cart cart) {
        this.cart = cart;
        this.listCartMedia = cart.getListCartMedia();
    }

    public List<CartMedia> getListCartMedia() {
        return listCartMedia;
    }

    public void addMediaToCart(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException {
        cart.addMediaToCart(media);
    }

    public void removeMediaFromCart(CartMedia media) {
        listCartMedia.remove(media);
    }

    public void updateQuantityOfMedia(CartMedia media, int quantity) {
        listCartMedia.get(getMediaIndex(media)).setQuantity(quantity);
    }

    public int getAmount() {
        return cart.getAmount();
    }

    public int getMediaIndex(CartMedia cartMedia) {
        for (int i = 0; i < listCartMedia.size(); i++) {
            if (listCartMedia.get(i).getMedia().getId() == cartMedia.getMedia().getId()) {
                return i;
            }
        }
        return -1;
    }
}
