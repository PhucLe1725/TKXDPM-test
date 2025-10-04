package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;

public interface CheckAvailabilityStrategy {
    boolean checkAvailability(CartMedia media);
}
