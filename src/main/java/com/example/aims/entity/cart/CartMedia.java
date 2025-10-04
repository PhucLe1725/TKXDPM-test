package com.example.aims.entity.cart;

import com.example.aims.entity.media.Media;


//Implements Interface
public class CartMedia implements ICartMedia {
    private Media media;
    private int quantity;
    private int price;
    private boolean checked = true;

    public CartMedia(Media media, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    //Chuyển từ CartController vào CartMedia
    public boolean checkAvailability() {
        return quantity <= media.getQuantity();
    }

    public void toggleChecked() {
        this.checked = !this.checked;
    }


}