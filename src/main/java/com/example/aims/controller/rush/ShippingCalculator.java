package com.example.aims.controller.rush;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;

import java.util.List;

public interface ShippingCalculator {
    int calculateShippingFee(List<CartMedia> cartMediaList, DeliveryInfo deliveryInfo);
}
