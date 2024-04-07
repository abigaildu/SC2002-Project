package console;

public class CartItem {
    private MenuItem menuItem; // The menu item being added to the cart
    private int quantity; // The quantity of this item
    //private String branchName;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        //this.branchName = branchName;
    }

    // Getters and Setters
    
//    public String getBranchName() {
//    	return branchName;
//    }
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to get the subtotal for this cart item
    public float getSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    // Overriding toString() method to display cart item details
    @Override
    public String toString() {
        return menuItem.getItemName() + " - Quantity: " + quantity + ", Subtotal: $" + getSubtotal();
    }
}
