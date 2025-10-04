
package com.example.aims.service;

import com.example.aims.entity.cart.CartMedia;
import java.util.List;

public interface CartCalculator {
    int calculateSubtotal(List<CartMedia> items);
}