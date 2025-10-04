package com.example.aims.service.impl;

import com.example.aims.service.RushOrderValidator;

public class DefaultRushOrderValidator implements RushOrderValidator {
    private static final String RUSH_ORDER_PROVINCE = "Ha Noi (TP)";

    @Override
    public boolean isEligible(String province) {
        return RUSH_ORDER_PROVINCE.equals(province);
    }
}