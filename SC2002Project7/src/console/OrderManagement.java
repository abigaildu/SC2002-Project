package console;

import java.util.HashMap;
import java.util.Map;

public class OrderManagement {
    private Map<Integer, Cart> orders; // Maps order ID to Cart

    public OrderManagement() {
        this.orders = new HashMap<>();
    }

    // Method to add a new order
    public void addOrder(int orderId, Cart cart) {
    	cart.setCartID(orderId);
        this.orders.put(orderId, cart);
    }

    // Method to get and display order details by ID
    public String getOrderDetailsById(int orderId) {
        Cart cart = orders.get(orderId);
        if (cart == null) {
            return "Order with ID " + orderId + " not found.";
        }
        
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Dining Option: ").append(cart.getIsDineIn() ? "Dine-In" : "Takeaway").append("\n");
        details.append("Items:\n");
        
        for (CartItem item : cart.getCartItems()) {
            details.append(item.toString()).append("\n");
        }
        
        details.append("Total Cost: $").append(cart.totalCost());
        return details.toString();
    }
    
    public void clearAllOrders() {
        orders.clear(); // This clears the map of orders
    }

    
}
