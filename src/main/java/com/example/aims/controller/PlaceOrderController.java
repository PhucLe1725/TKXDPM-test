package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.exception.RuntimeException;
import com.example.aims.service.ShippingFeeCalculator;
import com.example.aims.service.DeliveryInfoValidator;
import com.example.aims.service.RushOrderValidator;
import com.example.aims.service.CartCalculator;
import com.example.aims.service.impl.DefaultShippingCalculator;
import com.example.aims.service.impl.DefaultDeliveryValidator;
import com.example.aims.service.impl.DefaultRushOrderValidator;
import com.example.aims.service.impl.DefaultCartCalculator;

import java.util.HashMap;
import java.util.List;


public class PlaceOrderController implements OrderService {

    private List<CartMedia> cartMediaList;
    private DeliveryInfo deliveryInfo;
    private final ShippingFeeCalculator shippingCalculator;
    private final DeliveryInfoValidator deliveryValidator;
    private final RushOrderValidator rushOrderValidator;
    private final CartCalculator cartCalculator;

    public PlaceOrderController(List<CartMedia> cartMediaList) {
        this.cartMediaList = cartMediaList;
        this.shippingCalculator = new DefaultShippingCalculator();
        this.deliveryValidator = new DefaultDeliveryValidator();
        this.rushOrderValidator = new DefaultRushOrderValidator();
        this.cartCalculator = new DefaultCartCalculator();
    }

    public List<CartMedia> getCartMediaList() {
        return cartMediaList;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public int calculateShippingFee(String province) {
        return shippingCalculator.calculateFee(cartMediaList, province);
    }

    public void processDeliveryInfo(HashMap<String, String> info) throws RuntimeException {
        deliveryValidator.validateInfo(
                info.get("name"),
                info.get("email"),
                info.get("address"),
                info.get("phone")
        );

        deliveryInfo = DeliveryInfo.builder()
                .name(info.get("name"))
                .province(info.get("province"))
                .address(info.get("address"))
                .phone(info.get("phone"))
                .email(info.get("email"))
                .build();
    }

    public boolean checkRushOrderAddress() {
        return rushOrderValidator.isEligible(deliveryInfo.getProvince());
    }

    public int calculateSubtotal() {
        return cartCalculator.calculateSubtotal(cartMediaList);
    }
}