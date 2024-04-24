package models;

public class CartItem extends MenuItem{
	private int quantity;

	public CartItem(MenuItem menuItem, int quantity) {
		super(menuItem.getName(), menuItem.getPrice(), menuItem.isAvail(), menuItem.getCategory());
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getSubtotal() {
		return this.getPrice() * quantity;
	}
}
