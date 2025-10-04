package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

import java.util.List;

public class CartController {
    //Tách trạng thái tĩnh
    private Cart cart;
    private CartManager cartManager;
    private CartValidator cartValidator;
    //Chiến lược dùng để thêm, xóa và ktra tính khả dụng của sp mà k cần thay đổi code
    private AddMediaStrategy addMediaStrategy;
    private RemoveMediaStrategy removeMediaStrategy;
    private CheckAvailabilityStrategy checkAvailabilityStrategy;

    //Constructor nhận Cart như tham số
    public CartController(Cart cart) {
        this.cart = cart;
        this.cartManager = new CartManager(cart);
        this.cartValidator = new CartValidator();
        this.addMediaStrategy = new SimpleAddMediaStrategy(cart);
        this.removeMediaStrategy = new SimpleRemoveMediaStrategy(cart);
        this.checkAvailabilityStrategy = new SimpleCheckAvailabilityStrategy();
    }

    public Cart getCart() {
        return cart;
    }

    public List<CartMedia> getListCartMedia() {
        return cartManager.getListCartMedia();
    }

    public boolean checkAvailabilityOfSpecificProduct(CartMedia media) {
        return cartValidator.checkAvailabilityOfSpecificProduct(media);
    }

    public void addMediaToCart(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException {
        cartManager.addMediaToCart(media);
    }

    public void removeMediaFromCart(CartMedia media) {
        cartManager.removeMediaFromCart(media);
    }

    public void updateQuantityOfMedia(CartMedia media, int quantity) {
        cartManager.updateQuantityOfMedia(media, quantity);
    }

    //Sử dụng phương thức phụ trợ có trong CartMedia
    public void updateMediaCheck(CartMedia media) {
        media.toggleChecked();
    }

    public int getAmount() {
        return cartManager.getAmount();
    }

    public List<CartMedia> requestToPlaceOder() {
        List<CartMedia> cartMediaList = cart.getListCartMedia()
                .stream()
                .filter(CartMedia::isChecked)
                .toList();
        if (!cartMediaList.isEmpty()) return cartMediaList;

        return null;
    }
    // Có thể thêm phương thức để thay đổi chiến lược khi cần thiết
    public void setAddMediaStrategy(AddMediaStrategy strategy) {
        this.addMediaStrategy = strategy;
    }

    public void setRemoveMediaStrategy(RemoveMediaStrategy strategy) {
        this.removeMediaStrategy = strategy;
    }

    public void setCheckAvailabilityStrategy(CheckAvailabilityStrategy strategy) {
        this.checkAvailabilityStrategy = strategy;
    }
}
