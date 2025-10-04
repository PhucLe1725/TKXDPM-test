package com.example.aims.service.impl;

import com.example.aims.service.DeliveryInfoValidator;
import com.example.aims.utils.Utils;

public class DefaultDeliveryValidator implements DeliveryInfoValidator {
    @Override
    public void validateInfo(String name, String email, String address, String phone)
            throws RuntimeException {
        Utils.processUserInfo(name, email, address, phone);
    }
}