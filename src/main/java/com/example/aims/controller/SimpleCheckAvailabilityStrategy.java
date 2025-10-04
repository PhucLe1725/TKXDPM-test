package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;

public class SimpleCheckAvailabilityStrategy implements CheckAvailabilityStrategy {
    @Override
    public boolean checkAvailability(CartMedia media) {
        return media.checkAvailability();
    }
}
