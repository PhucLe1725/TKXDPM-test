package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

public interface AddMediaStrategy {
    void addMedia(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException;
}
