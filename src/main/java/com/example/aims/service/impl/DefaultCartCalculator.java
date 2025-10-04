package com.example.aims.service.impl;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.service.CartCalculator;
import java.util.List;

public class DefaultCartCalculator implements CartCalculator {
    @Override
    public int calculateSubtotal(List<CartMedia> items) {
        return items.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}