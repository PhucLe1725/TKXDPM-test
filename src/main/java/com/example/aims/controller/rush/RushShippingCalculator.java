package com.example.aims.controller.rush;

import com.example.aims.controller.OrderService;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;

import java.util.List;

public class RushShippingCalculator implements ShippingCalculator {

    // Tính phí vận chuyển rush
    @Override
    public int calculateShippingFee(List<CartMedia> cartMediaList, DeliveryInfo deliveryInfo) {
        double totalWeight = 0;
        int shippingFee = 0;

        for (CartMedia cm : cartMediaList) {
            if (cm.getMedia().isRushOrderAvailable()) {
                shippingFee += 10000; // Phí rush
                totalWeight += cm.getQuantity() * cm.getMedia().getWeight();
            }
        }

        // Tính phí cơ bản theo tỉnh và trọng lượng
        shippingFee += calculateBaseShippingFee(deliveryInfo, totalWeight);
        return shippingFee;
    }

    // Tính phí vận chuyển tiêu chuẩn (có truyền orderService vào)
    public int calculateStandardShippingFee(List<CartMedia> cartMediaList, DeliveryInfo deliveryInfo, OrderService orderService) {
        double totalWeight = 0;
        int shippingFee = 0;

        for (CartMedia cm : cartMediaList) {
            if (!cm.getMedia().isRushOrderAvailable()) {
                totalWeight += cm.getQuantity() * cm.getMedia().getWeight();
            }
        }

        // Tính phí cơ bản theo tỉnh và trọng lượng
        shippingFee += calculateBaseShippingFee(deliveryInfo, totalWeight);

        // Giảm giá phí vận chuyển nếu đơn hàng trên 1 triệu, tính từ orderService
        if (orderService.calculateSubtotal() > 1000000) {
            shippingFee = Math.max(shippingFee - 25000, 0);
        }

        return shippingFee;
    }

    // Tính phí cơ bản theo tỉnh và trọng lượng
    private int calculateBaseShippingFee(DeliveryInfo deliveryInfo, double totalWeight) {
        double adjustedWeight;
        int baseFee;

        if (deliveryInfo.getProvince().equals("Ho Chi Minh (TP)") || deliveryInfo.getProvince().equals("Ha Noi (TP)")) {
            adjustedWeight = Math.max(0, totalWeight - 3);
            baseFee = 22000;
        } else {
            adjustedWeight = Math.max(0, totalWeight - 0.5);
            baseFee = 30000;
        }

        return baseFee + (int) Math.ceil(adjustedWeight * 2) * 2500;
    }


}
