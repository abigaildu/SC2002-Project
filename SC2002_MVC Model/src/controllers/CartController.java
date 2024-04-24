package controllers;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import models.CartItem;

public class CartController {
	private List<CartItem> cart; 
    private boolean isDineIn;

	public CartController() {
		this.cart = new ArrayList<CartItem>();
	}
	
	public void addCartItem(CartItem cartItem) {
		
	}
	
	public CartItem getCartItemByName(String name) {
		
	}
	
	public List<CartItem> getCartById (int cartId) {
		
	}

	public List<CartItem> getCart() {
		return cart;
	}
	
	public void updateCartItem(String name, int quantity) {
		
	}
	
	public void deleteCartItem(String name) {
		
	}
}
