package controllers;

import java.util.ArrayList;
import java.util.List;

import models.CartItem;

public class CartController {
	private List<CartItem> cartItems;
	private boolean isDineIn;
	
	public CartController(){
		this.cartItems = new ArrayList<CartItem>();
		this.isDineIn = true;
	}
	
	public CartController(boolean isDineIn){
		this.cartItems = new ArrayList<CartItem>();
		this.isDineIn = isDineIn;
	}
	
	public boolean isDineIn() {
		return this.isDineIn;
	}
	
	public void setDineIn(boolean DineIn) {
		this.isDineIn = DineIn;
	}
	
	public float totalCost() {
		float total = 0;
		for(CartItem cartItem: cartItems) {
			total += cartItem.getSubtotal();
		}
		return total;
	}
	
	public CartItem getCartItemByName(String itemName) {
		for(CartItem cartItem : cartItems) {
			if(cartItem.getName().equalsIgnoreCase(itemName.trim())){
				return cartItem; 
			}
		}
		return null; //cart item not found
	}

	public void addCartItem(CartItem itemToAdd){
		CartItem cartItem = this.getCartItemByName(itemToAdd.getName());
		if(cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity()+itemToAdd.getQuantity());
			return; //if cart item already inside cart
		}
		cartItems.add(itemToAdd);
		return;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public boolean updateCartItem(String itemToEdit, int quantity) {
		CartItem cartItem = this.getCartItemByName(itemToEdit);
		if(cartItem != null) {
			cartItem.setQuantity(quantity);
			if(quantity == 0) {
				cartItems.remove(cartItem);
			}
			return true;
		}
		return false; //if itemToEdit is not found
	}
	
	public boolean deleteCartItem(String itemToRemove) {
		return updateCartItem(itemToRemove, 0);
	}
}
