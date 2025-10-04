package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;

public class CartValidator {
    public boolean checkAvailabilityOfSpecificProduct(CartMedia media) {
        return media.checkAvailability();
    }
}
