package com.example.aims.controller.rush;

import com.example.aims.exception.RushDeliveryAddressException;

public class RushDeliveryInfoValidator {
    public void validateDeliveryInstruction(String deliveryInstruction) throws RushDeliveryAddressException {
        if (deliveryInstruction == null || deliveryInstruction.trim().isEmpty()) {
            throw new RushDeliveryAddressException("Delivery Instruction should not be blank");
        }
    }
}

