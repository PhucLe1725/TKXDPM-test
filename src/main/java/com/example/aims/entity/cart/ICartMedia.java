package com.example.aims.entity.cart;

import com.example.aims.entity.media.Media;

public interface ICartMedia {
    Media getMedia();            // Trả về đối tượng Media
    int getQuantity();           // Lấy số lượng của sản phẩm trong giỏ
    int getPrice();              // Lấy giá của sản phẩm
    boolean isChecked();         // Kiểm tra xem sản phẩm có được chọn không
    boolean checkAvailability(); // Kiểm tra khả năng có sẵn của sản phẩm
    void toggleChecked();        // Đảo trạng thái checked của sản phẩm
}
