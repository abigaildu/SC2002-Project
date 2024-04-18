package controllers;

import java.util.ArrayList;
import java.util.List;

import models.CartItem;

public class CartController {
	private List<CartItem> cart; 

	public CartController() {
		this.cart = new ArrayList<CartItem>();
	}
	
	public void addCartItem(CartItem cartItem) {
		
	}
	
	public CartItem getCartItemByName(String name) {
		
	}

	public List<CartItem> getCart() {
		return cart;
	}
	
	public void updateCartItem(String name, int quantity) {
		
	}
	
	public void deleteCartItem(String name) {
		
	}
}
