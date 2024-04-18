package console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cart {
    private List<CartItem> cartItems; // Correctly manage a list of CartItem objects
    //private static int nextCartId = 1;
    private int cartID;
    private boolean isDineIn;
    private static final String CART_DETAILS_FILE_PATH = "Cart.txt";
    
    public Cart(int CartID) {
        if (CartID <= 0) { // Check if a valid ID is passed
            // Assuming generateUniqueCartID() is a static method in CustomerPage
            this.cartID = CustomerPage.generateUniqueCartID();
        } else {
            this.cartID = CartID; // Use the passed ID if valid
        }
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
    
    public void checkout(String branchName) {
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
                    System.out.println("Checkout completed.");
                    this.cartID = CustomerPage.generateUniqueCartID();
                    System.out.println("Your receipt: $" + totalCost() +" for Cart ID " + getCartID());
                    // Save cart details to file after successful payment
                    
                    saveCartToFile(branchName);
                    clearCart();
                    //this.cartID = generateUniqueCartID(); // Generate new cart ID for the next order
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } 
        }
    }



    // Method to clear the cart
    public void clearCart() {
        cartItems.clear();
        System.out.println("The cart has been cleared.");
    }
  //  public String getBranchName() {
   //     return branchName;
   // }

    //public void setBranchName(String branchName) {
    //    this.branchName = branchName;
    //}

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
    public static void removeCartDetails(int orderId) {
        try {
            // Read the existing cart details file
            List<String> lines = Files.readAllLines(Paths.get(CART_DETAILS_FILE_PATH));

            // Create a new list to store modified lines
            List<String> newLines = new ArrayList<>();

            // Flag to determine if the current line belongs to the cart being collected
            boolean removeNext = false;

            // Iterate over each line in the file
            for (String line : lines) {
                // Check if the line contains the cart ID
                if (line.startsWith("Cart ID:")) {
                    int cartId = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    // If the cart ID matches the collected order ID, set the flag to remove subsequent lines
                    if (cartId == orderId) {
                        removeNext = true;
                    }
                }
                // If the flag is set, skip adding this line to the new list
                if (!removeNext) {
                    newLines.add(line);
                }
                // Reset the flag after removing the cart details
                if (line.isEmpty()) {
                    removeNext = false;
                }
            }

            // Write the modified lines back to the file
            Files.write(Paths.get(CART_DETAILS_FILE_PATH), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("An error occurred while removing cart details: " + e.getMessage());
        }
    }


   

    
    public void saveCartToFile(String branchName) {
        try (FileWriter writer = new FileWriter(CART_DETAILS_FILE_PATH, true)) { // Enable append mode by adding true
            // Write the cart ID and dining option at the beginning of the file
            String diningOption = this.isDineIn ? "Dine-in" : "Takeaway";
            writer.write( "Cart ID :" + this.cartID + System.lineSeparator());
            writer.write("Dining Option : " + diningOption + System.lineSeparator());
            writer.write("Branch Name : " + branchName + System.lineSeparator());
            writer.write("Cart Items :" + System.lineSeparator());
            // Write the details of each cart item
            for (CartItem item : this.cartItems) {
                // Assuming each CartItem has a method to get a string representation
                writer.write(item.toString() + System.lineSeparator());
            }
            writer.write(System.lineSeparator()); // Add an empty line for separation between carts
        } catch (IOException e) {
            System.err.println("An error occurred while saving the cart: " + e.getMessage());
        }
    }


    // Method to load the cart from a file
    public static Cart loadCartFromFile(int cartID) {
        Cart cart = new Cart(cartID);
        try (BufferedReader reader = new BufferedReader(new FileReader(CART_DETAILS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to create a CartItem and add it to the cart
                // This parsing logic will depend on the format used in saveCartToFile
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String itemName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    float price = Float.parseFloat(parts[2]);
                    MenuItem menuItem = new MenuItem(itemName, price); // Assuming constructor exists
                    cart.addMenuItem(menuItem, quantity);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading the cart: " + e.getMessage());
        }
        return cart;
    }


}