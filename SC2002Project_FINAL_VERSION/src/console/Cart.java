package console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
Representing a Cart.
*/
public class Cart {
	/**
	* The items of this Cart.
	*/
    private List<CartItem> cartItems; // Correctly manage a list of CartItem objects
    /**
	* The id of this Cart.
	*/
    private int cartID;
    /**
	* The dining option of this Cart.
	*/
    private boolean isDineIn;
    /**
	* The file path of text file storing cart detail.
	*/
    private static final String CART_DETAILS_FILE_PATH = "Cart.txt";
    /**
	* The file path of text file storing card details.
	*/
    private final String CARD_DETAILS_FILE_PATH = "card_details_storage.txt";
    
    /**
     * Creating a new Cart with the given information.
     * @param CartID Cart's id.
     */
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

    /**
     * Adding a new MenuItem into Cart.
     * @param menuItem New MenuItem.
     * @param quantity New quantity.
     */
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

    /**
     * Displaying all cart items.
     */
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

    /**
     * Calculating total cost of this Cart.
     * @return Total cost of this Cart.
     */
    public float calculateTotalCost() {
        float total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubtotal(); // Use getSubtotal() for each CartItem
        }
        return total;
    }

    /**
     * Saving card details to text file.
     * @param cardNumber Card number.
     * @param cvv CVV.
     * @param branchName Branch name.
     */
    private void saveCardDetailsFile(String cardNumber, String cvv, String branchName) {
        try {
            FileWriter writer = new FileWriter(CARD_DETAILS_FILE_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Card Number: " + cardNumber + ", CVV: " + cvv + ", Branch Name: " + branchName);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checking out.
     * @param branchName Branch name.
     */
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
                    System.out.println((index++) + ". " + method);
                }
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                if (choice >= 1 && choice <= methods.size()) {
                    String selectedMethod = methods.get(choice - 1);
                    boolean checkCardPayment = CheckCardPayment(choice);
                    if (checkCardPayment) {
                        System.out.print("Enter card number: ");
                        String cardNumber = scanner.nextLine();
                        System.out.print("Enter CVV: ");
                        String cvv = scanner.nextLine();
                        // Save card details to file
                        saveCardDetailsFile(cardNumber, cvv, branchName);
                    }
                    System.out.println("Your payment by " + selectedMethod + " has been successfully processed.");
                    System.out.println("Checkout completed.");
                    this.cartID = CustomerPage.generateUniqueCartID();
                    System.out.println("Your receipt: $" + calculateTotalCost() + " for Cart ID " + getCartID());
                    // Save cart details to file after successful payment
                    saveCartToFile(branchName);
                    clearCart();
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    /**
     * Clearing cart.
     */
    public void clearCart() {
        cartItems.clear();
        System.out.println("The cart has been cleared.");
    }

    /**
     * Getting the id of this Cart.
     * @return This Cart's id.
     */
    public int getCartID() {
        return cartID;
    }
    
    /**
     * Changing the id of this Cart.
     * @param cartID This Cart's new id.
     */
    public void setCartID(int cartID) {
    	this.cartID = cartID;
    }

    /**
     * Getting the dining option of this Cart.
     * @return This Cart's dining option.
     */
    public boolean getIsDineIn() {
        return isDineIn;
    }

    /**
     * Changing the dining option of this Cart.
     * @param isDineIn This Cart's new dining option.
     */
    public void setIsDineIn(boolean isDineIn) {
        this.isDineIn = isDineIn;
    }
    
    /**
     * Getting all CartItems.
     * @return All CartItems.
     */
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }
    
    /**
     * Removing cart details.
     * @param orderId Cart's orderId.
     */
    public void removeCartDetails(int orderId) {
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
    
    /**
     * Checking card payment.
     * @param choice Choice of payment method.
     * @return True if it is Credit/Debit Card, otherwise False.
     */
    private boolean CheckCardPayment(int choice) {
        try (BufferedReader reader = new BufferedReader(new FileReader("payment_methods.txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                index++;
                if (index == choice) {
                    // Check if the selected method is a card payment
                    return line.equalsIgnoreCase("Credit/Debit Card");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Default to false if any error occurs
    }

    /**
     * Saving cart to text file.
     * @param branchName Branch name.
     */
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


    /**
     * Loading cart from text file.
     * @param cartID Cart's id.
     * @return Cart.
     */
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