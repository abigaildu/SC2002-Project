package console;

/**
Representing a CartItem.
*/
public class CartItem {
	/**
	* The MenuItem object.
	*/
    private MenuItem menuItem; // The menu item being added to the cart
    /**
	* The quantity of this item.
	*/
    private int quantity; // The quantity of this item

    /**
     * Creating a new CartItem with the given information.
     * @param menuItem MenuItem object.
     * @param quantity Quantity of this item.
     */
    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    /**
     * Getting the MenuItem of this CartItem.
     * @return This CartItem's MenuItem.
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Changing the MenuItem of this CartItem.
     * @param id This CartItem's new MenuItem.
     */
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * Getting the quantity of this CartItem.
     * @return This CartItem's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Changing the quantity of this CartItem.
     * @param id This CartItem's new quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getting the subtotal of this CartItem.
     * @return The CartItem's subtotal.
     */
    // Method to get the subtotal for this cart item
    public float getSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    /**
     * Getting CartItem details.
     * @return CartItem details.
     */
    @Override
    public String toString() {
        return menuItem.getItemName() + " - Quantity: " + quantity + ", Subtotal: $" + getSubtotal();
    }
    
}