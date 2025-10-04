package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;

import java.util.List;

public interface OrderService {
    List<CartMedia> getCartMediaList();
    DeliveryInfo getDeliveryInfo();
    int calculateSubtotal();
}

