package console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<CartItem> cartItems; // Correctly manage a list of CartItem objects
    //private static int nextCartId = 1;
    private int cartID;
    private boolean isDineIn;

    public Cart(int CartID) { //int CartID
    	this.cartID = CartID;
        this.cartItems = new ArrayList<>();
        this.isDineIn = true;
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
        	String diningOption = this.isDineIn ? "Dine-in" : "Takeaway";
        	System.out.println("\nDining Option: " + diningOption);
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
//    public void checkout(OrderStatus orderStatus) {
//    	PaymentManagement paymentMethods = new PaymentManagement();
//    	List<String> methods = paymentMethods.getPaymentMethods();
//    	if (this.cartItems.isEmpty()) {
//    		System.out.println("Please select the item!!!");
//    	} else {
//    		this.displayCartItems();
//        	Scanner scanner = new Scanner(System.in);
//        	
//        	while (true) {
//        		System.out.println("\n--- Payment Option ---");
//        		int index = 1;
//    	        for (String method : methods) {
//    	        	System.out.println((index++)+ ". " + method);
//    	        }
//    	        System.out.print("Select an option: ");
//                int choice = scanner.nextInt();
//                scanner.nextLine(); // Consume newline left-over
//                
//                if (choice >= 1 && choice <= methods.size()) {
//                	String method = methods.get(choice - 1);
//                	System.out.println("Your payment by " + method + " has been successfully processed.");
//                	System.out.println(this.getCartID());
//                	OrderItem order = new OrderItem(this.getCartID());
//                	order.toString();
//                	orderStatus.addNewOrders(order);
//                	break;
//                } else {
//                	System.out.println("Invalid option. Please try again.");
//                }
//            } 
////        	
//            System.out.println("Checkout completed.");
////          Print receipt.... (havent done)
//            System.out.println("Your receipt: " + totalCost()); 
//            clearCart(); // Clear cart after checkout
//            cartID++;
//    	}
//    }
    
    public void checkout() { //public int checkout()
    	//int orderID = cartID;
    	PaymentManagement paymentMethods = new PaymentManagement();
    	List<String> methods = paymentMethods.getPaymentMethods();
    	if (this.cartItems.isEmpty()) {
    		System.out.println("Please select the item!!!");
    	} else {
    		this.displayCartItems();
        	Scanner scanner = new Scanner(System.in);
        	
        	while (true) {
        		System.out.println("\n--- Payment Option ---");
        		int index = 1;
    	        for (String method : methods) {
    	        	System.out.println((index++)+ ". " + method);
    	        }
    	        System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                
                if (choice >= 1 && choice <= methods.size()) {
                	String method = methods.get(choice - 1);
                	System.out.println("Your payment by " + method + " has been successfully processed.");
//                	System.out.println(this.getCartID());
                	break;
                } else {
                	System.out.println("Invalid option. Please try again.");
                }
            } 
//        	
            System.out.println("Checkout completed.");
//          Print receipt.... (havent done)
            System.out.println("Your receipt: $" + totalCost() +" for Cart ID " + getCartID()); 
            //clearCart(); // Clear cart after checkout
            //cartID++;
    	}
    	//return orderID;
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
    
    public void setCartID(int cartID) {
    	this.cartID = cartID;
    }

    // Correct naming for checking if dine-in is selected
    public boolean getIsDineIn() {
        return isDineIn;
    }

    // Setter for isDineIn
    public void setIsDineIn(boolean isDineIn) {
        this.isDineIn = isDineIn;
    }
    
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

}