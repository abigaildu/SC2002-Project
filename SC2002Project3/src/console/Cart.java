package console;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems; // Correctly manage a list of CartItem objects
    private static int cartID = 1;
    private boolean dineIn;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // Add menu items to the cart, correctly handling CartItem creation
    public void addMenuItem(MenuItem menuItem, int quantity) {
        // Check if the item already exists in the cart
        boolean itemFound = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getMenuItem().equals(menuItem)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity); // Update quantity if item exists
                itemFound = true;
                break;
            }
        }
        // If the item doesn't exist, create a new CartItem and add it to the list
        if (!itemFound) {
            CartItem newCartItem = new CartItem(menuItem, quantity);
            cartItems.add(newCartItem);
        }
        System.out.println("Added " + quantity + " x " + menuItem.getItemName() + " to the cart.");
    }

    // Method to display all cart items with their quantities and subtotals
    public void displayCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nCart Items:");
            for (CartItem item : cartItems) {
                System.out.println(item.getMenuItem().getItemName() + " - Quantity: " + item.getQuantity() + ", Subtotal: $" + item.getSubtotal());
            }
        }
    }

    // Method to calculate the total cost of items in the cart
    public float totalCost() {
        float total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubtotal(); // Use getSubtotal() for each CartItem
        }
        return total;
    }

    // Checkout method, simplified to just clear the cart and increment cartID
    public void checkout() {
        System.out.println("Checkout completed. Total cost: $" + totalCost());
        clearCart(); // Clear cart after checkout
        cartID++;
    }

    // Method to clear the cart
    public void clearCart() {
        cartItems.clear();
        System.out.println("The cart has been cleared.");
    }

    // Getter for cartID
    public int getCartID() {
        return cartID;
    }

    // Correct naming for checking if dine-in is selected
    public boolean isDineIn() {
        return dineIn;
    }

    // Setter for dineIn
    public void setDineIn(boolean dineIn) {
        this.dineIn = dineIn;
    }
}
