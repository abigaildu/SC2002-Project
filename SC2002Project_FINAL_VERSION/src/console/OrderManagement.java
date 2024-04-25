package console;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import console.Cart;

/**
Representing an OrderManagement.
*/
public class OrderManagement {
	/**
	* The orders.
	*/
    private Map<Integer, Cart> orders; // Maps order ID to Cart
    /**
	* The OrderManagement object.
	*/
    private static OrderManagement instance;

    /**
     * Creating a new OrderManagement.
     */
    public OrderManagement() {
        this.orders = new HashMap<>();
    }
    
    /**
     * Getting OrderManagement object synchronously.
     * @return OrderManagement object.
     */
    public static synchronized OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }

    /**
     * Adding an order.
     * @param orderId Order's id.
     * @param cart Cart object.
     */
    public void addOrder(int orderId, Cart cart) {
        cart.setCartID(orderId);
        this.orders.put(orderId, cart);
    }

    /**
     * Getting order details by id.
     * @param orderId Order's id.
     * @return Order details.
     */
    public String getOrderDetailsById(int orderId) {
        String cartDetailsFilePath = "Cart.txt"; // The file where cart details are stored
        StringBuilder details = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(cartDetailsFilePath))) {
            String line;
            boolean isCurrentOrder = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Cart ID :")) {
                    int currentOrderId = Integer.parseInt(line.substring(line.indexOf(":")+ 1));
                    
                    isCurrentOrder = (currentOrderId == orderId);
                }
                if (isCurrentOrder) {
                    details.append(line).append("\n");
                    if (line.isEmpty()) { // Assuming an empty line marks the end of an order's details
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading the order: " + e.getMessage());
            return "An error occurred.";
        }

        if (details.length() == 0) {
            return "Order with ID " + orderId + " not found.";
        } else {
            return details.toString();
        }
    }

    /**
     * Clearing all orders.
     */
    public void clearAllOrders() {
        orders.clear(); // This clears the map of orders
    }
}
