package com.example.aims.entity.cart;

import com.example.aims.exception.cart.CartAlreadyInCartException;
import com.example.aims.exception.media.NotEnoughMediaException;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private static Cart cart;
	private static List<CartMedia> listCartMedia = new ArrayList<CartMedia>();

	public static Cart getCart() {
		if(cart == null) {
			cart = new Cart();
		}
		return cart;
	}
	public List<CartMedia> getListCartMedia() {
		return listCartMedia;
	}

	//Chuyển từ CartController vào lớp Cart
	public int getAmount() {
		return listCartMedia.stream()
				.filter(CartMedia::isChecked)
				.mapToInt(cartMedia -> cartMedia.getQuantity() * cartMedia.getPrice())
				.sum();
	}

	public boolean isMediaInCart(CartMedia media) {
		return listCartMedia.stream()
				.anyMatch(cartMedia -> cartMedia.getMedia().getId() == media.getMedia().getId());
	}

	//Chuyển từ CartController sang Cart
	public void addMediaToCart(CartMedia media) throws CartAlreadyInCartException, NotEnoughMediaException {
		if (isMediaInCart(media)) {
			throw new CartAlreadyInCartException();
		}

		if (!media.checkAvailability()) {
			throw new NotEnoughMediaException("Not enough media");
		}

		listCartMedia.add(media);
	}


}