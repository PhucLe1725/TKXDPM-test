package com.example.aims.service;

import com.example.aims.entity.cart.CartMedia;
import java.util.List;

public interface ShippingFeeCalculator {
    int calculateFee(List<CartMedia> items, String province);
}