package com.example.aims.controller;

import com.example.aims.controller.rush.RushDeliveryInfoValidator;
import com.example.aims.controller.rush.RushShippingCalculator;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.exception.RushDeliveryAddressException;

import java.util.HashMap;
import java.util.List;

public class RushOrderController {

    private final OrderService orderService;
    private RushShippingCalculator rushShippingCalculator;
    private RushDeliveryInfoValidator rushDeliveryInfoValidator;

    public RushOrderController(OrderService orderService) {
        this.orderService = orderService;
        this.rushShippingCalculator = new RushShippingCalculator();
        this.rushDeliveryInfoValidator = new RushDeliveryInfoValidator();
    }

    public OrderService getOrderService() {
        return orderService;
    }

    // Phương thức xử lý thông tin giao hàng nhanh
    public void processRushDeliveryInfo(HashMap<String, String> rushDeliveryInfo) throws RushDeliveryAddressException {
        String deliveryInstruction = rushDeliveryInfo.get("DeliveryInstruction");
        rushDeliveryInfoValidator.validateDeliveryInstruction(deliveryInstruction);  // Xác thực
    }

    // Tính phí vận chuyển
    public int[] calculateRushShippingFee() {
        List<CartMedia> cartMediaList = orderService.getCartMediaList();
        DeliveryInfo deliveryInfo = orderService.getDeliveryInfo();

        int rushShippingFee = rushShippingCalculator.calculateShippingFee(cartMediaList, deliveryInfo);
        int standardShippingFee = rushShippingCalculator.calculateStandardShippingFee(cartMediaList, deliveryInfo, orderService);
        return new int[]{standardShippingFee ,rushShippingFee};
    }
}

