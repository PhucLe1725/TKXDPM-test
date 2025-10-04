package com.example.aims.service.impl;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.service.ShippingFeeCalculator;
import java.util.List;

public class DefaultShippingCalculator implements ShippingFeeCalculator {
    private static final int BASE_FEE_MAJOR_CITY = 22000;
    private static final int BASE_FEE_OTHER = 30000;
    private static final double MAJOR_CITY_WEIGHT_THRESHOLD = 3.0;
    private static final double OTHER_WEIGHT_THRESHOLD = 0.5;
    private static final int ADDITIONAL_FEE_PER_HALF_KG = 2500;
    private static final int FREE_SHIPPING_DISCOUNT = 25000;
    private static final int FREE_SHIPPING_THRESHOLD = 1000000;

    @Override
    public int calculateFee(List<CartMedia> items, String province) {
        double totalWeight = calculateTotalWeight(items);
        int amount = calculateTotalAmount(items);
        int shippingFee = calculateBaseFee(province, totalWeight);

        if (amount > FREE_SHIPPING_THRESHOLD) {
            shippingFee = Math.max(shippingFee - FREE_SHIPPING_DISCOUNT, 0);
        }

        return shippingFee;
    }

    private double calculateTotalWeight(List<CartMedia> items) {
        return items.stream()
                .mapToDouble(item -> item.getMedia().getWeight() * item.getQuantity())
                .sum();
    }

    private int calculateTotalAmount(List<CartMedia> items) {
        return items.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private int calculateBaseFee(String province, double totalWeight) {
        if (isMajorCity(province)) {
            totalWeight -= MAJOR_CITY_WEIGHT_THRESHOLD;
            return calculateFeeWithThreshold(totalWeight, BASE_FEE_MAJOR_CITY);
        } else {
            totalWeight -= OTHER_WEIGHT_THRESHOLD;
            return calculateFeeWithThreshold(totalWeight, BASE_FEE_OTHER);
        }
    }

    private boolean isMajorCity(String province) {
        return province.equals("Ho Chi Minh (TP)") ||
                province.equals("Ha Noi (TP)");
    }

    private int calculateFeeWithThreshold(double weight, int baseFee) {
        if (weight <= 0) return baseFee;
        int additionalWeight = (int) Math.ceil(weight * 2);
        return baseFee + (ADDITIONAL_FEE_PER_HALF_KG * additionalWeight);
    }
}