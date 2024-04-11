package console;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import console.Cart;


public class OrderManagement {
    private Map<Integer, Cart> orders; // Maps order ID to Cart
    private static OrderManagement instance;

    public OrderManagement() {
    	
        this.orders = new HashMap<>();
    }
    
    public static synchronized OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }

    // Method to add a new order
    public void addOrder(int orderId, Cart cart) {
    	cart.setCartID(orderId);
        this.orders.put(orderId, cart);
    }

    // Method to get and display order details by ID
//    public String getOrderDetailsById(int orderId) {
//    	
//        Cart cart = orders.get(orderId);
//        if (cart == null) {
//            return "Order with ID " + orderId + " not found.";
//        }
//        
//        StringBuilder details = new StringBuilder();
//        details.append("Order ID: ").append(orderId).append("\n");
//        details.append("Dining Option: ").append(cart.getIsDineIn() ? "Dine-In" : "Takeaway").append("\n");
//        details.append("Items:\n");
//        
//        for (CartItem item : cart.getCartItems()) {
//            details.append(item.toString()).append("\n");
//        }
//        
//        details.append("Total Cost: $").append(cart.totalCost());
//        return details.toString();
//    }
    
    public String getOrderDetailsById(int orderId) {
        String cartDetailsFilePath = "Cart.txt"; // The file where cart details are stored
        StringBuilder details = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(cartDetailsFilePath))) {
            String line;
            boolean isCurrentOrder = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Cart ID: ")) {
                    int currentOrderId = Integer.parseInt(line.substring(line.indexOf(":") + 2));
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

    
    public void clearAllOrders() {
        orders.clear(); // This clears the map of orders
    }
    
}