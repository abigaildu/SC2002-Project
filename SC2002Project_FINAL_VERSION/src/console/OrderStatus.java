package console;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

/**
Representing an OrderStatus.
*/
public class OrderStatus {
	/**
	* The orders.
	*/
    private List<OrderItem> orders = new ArrayList<>();
    /**
	* The file path of text file storing order details.
	*/
    private static final String ORDER_DETAILS_FILE_PATH = "orders.txt";
    /**
	* The file path of text file storing cancelled orders.
	*/
    private static final String CANCELLED_DETAILS_FILE_PATH = "cancelled_orders.txt";
    /**
	* The file path of text file storing completed orders.
	*/
    private static final String COMPLETED_DETAILS_FILE_PATH = "completed_orders.txt";
    /**
	* The timeout duration.
	*/
    private static final long TIMEOUT_DURATION = 15 * 1000;
    /**
	* The staff branch.
	*/
    private String staffBranch;
    
    /**
     * Creating a new OrderStatus.
     */
    public OrderStatus() {
    	loadOrderDetails();
    }
    
    /**
     * Adding cancelled order to the text file.
     * @param cancelledOrder Cancelled order.
     */
    private void logCancelledOrder(OrderItem cancelledOrder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CANCELLED_DETAILS_FILE_PATH, true))) {
            writer.write("Order ID: " + cancelledOrder.getOrderID() + ", Status: " + cancelledOrder.getOrderStatus());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error logging cancelled order: " + e.getMessage());
        }
    }
    
    /**
     * Changing the staff branch.
     * @param branch New branch.
     */
    public void setStaffBranch(String branch) {
        this.staffBranch = branch;
    }

    /**
     * Adding a new order.
     * @param order Order item.
     * @param branchName Branch name.
     */
    public void addNewOrder(OrderItem order, String branchName) {
        order.setBranchName(branchName); // Set the branch name for the order
        orders.add(order);
        saveOrderDetails(); // Save orders whenever a new order is added
    }

    /**
     * Marking the order as ready to collect.
     * @param orderID Order's ID.
     */
    public void markOrderAsReadyToCollect(int orderID) {
    	for (OrderItem order : orders) {
            if (order.getOrderID() == orderID) {
                order.setOrderStatus(OrderItem.Status.READY_TO_COLLECT);
                order.setTimeout(System.currentTimeMillis() + TIMEOUT_DURATION); // Set timeout
                startTimeoutMonitorThread(); // Start timeout monitor thread
                saveOrderDetails(); // Save orders whenever an order is processed
                return;
            }
        }
        System.out.println("Order ID " + orderID + " not found.");
    }

    /**
     * Marking the order as completed.
     * @param orderID Order's ID.
     */
    public void markOrderAsCompleted(int orderID) {
        for (OrderItem order : orders) {
            if (order.getOrderID() == orderID) {
                order.setOrderStatus(OrderItem.Status.COMPLETED);
                saveOrderDetails(); // Save orders whenever an order is processed
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMPLETED_DETAILS_FILE_PATH, true))) { // Append mode enabled
                    writer.write(order.getOrderID() + "," + order.getOrderStatus() + "," + order.getBranchName());
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println("Error saving order details: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Order ID " + orderID + " not found.");
    }
    
    /**
     * Displaying all new orders.
     */
    public void displayNewOrders() {
        if (staffBranch == null) {
            System.out.println("Staff branch is not set. Unable to display new orders.");
            return;
        }
        
        for (OrderItem order : orders) {
            if (CheckOrderForBranch(order, staffBranch)) {
                if (order.getOrderStatus() == OrderItem.Status.NEW_ORDER) {
                    System.out.println(order);
                }
            }
        }
    }

    /**
     * Check whether the order is in a particular branch.
     * @param order Order item.
     * @param staffBranch Staff branch.
     * @return State to indicate whether the order is in the staff branch.
     */
    private boolean CheckOrderForBranch(OrderItem order, String staffBranch) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_DETAILS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].trim().equals(staffBranch)) {
                    int orderIDFromFile = Integer.parseInt(parts[0].trim());
                    if (order.getOrderID() == orderIDFromFile) {
                        return true;
                    }
                }
            }
            // If the loop completes without finding any matching order, return false
            return false;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error checking order details: " + e.getMessage());
        }
        // Return false in case of any exception
        return false;
    }
    
    /**
     * Displaying all orders that are ready to pickup.
     */
    public void displayReadyForPickupOrders() {
        System.out.println("\nOrders Ready for Pickup: ");
        for (OrderItem order : orders) {
        	 if (CheckOrderForBranch(order, staffBranch)) {
        		 if (order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT) {
                    System.out.println(order);
                 }
        	 }
        }
    }      

    /**
     * Displaying all cancelled orders.
     */
    public void displayCancelledOrders() {
        System.out.println("\nCancelled Orders: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(CANCELLED_DETAILS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error displaying cancelled orders: " + e.getMessage());
        }
    }

    /**
     * Displaying all completed orders.
     */
    public void displayCompletedOrders() {
        System.out.println("\nCompleted Orders: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("completed_orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error in completed orders: " + e.getMessage());
        }
    }
    
    /**
     * Clearing all orders.
     */
    public void clearAllOrders() {
    	orders.clear();
    	saveOrderDetails();
    }
    
    /**
     * Tracking the order.
     * @param orderId Order's id.
     */
    public void trackOrder(int orderId) {
        boolean found = false;
        for (OrderItem order : orders) {
            if (order.getOrderID() == orderId) {
                System.out.println("Order found:");
                System.out.println(order);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }
    
    /**
     * Collecting the order.
     * @param orderId Order's id.
     * @return State to indicate whether collecting the order is successful.
     */
    public boolean collectOrder(int orderId) {
    	Iterator<OrderItem> iterator = orders.iterator();
        while (iterator.hasNext()) {
            OrderItem order = iterator.next();
            if (order.getOrderID() == orderId && order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT) {
                markOrderAsCompleted(orderId);
                iterator.remove(); // Remove the order using the iterator
                saveOrderDetails(); // Save the updated order list to file
                return true; // Indicate the order was successfully collected
            }
        }
        return false; // Indicate the order was not found or not ready to collect
    }

    /**
     * Saving order details to text file.
     */
    private void saveOrderDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_DETAILS_FILE_PATH))) {
            for (OrderItem order : orders) {
                writer.write(order.getOrderID() + "," + order.getOrderStatus() + "," + order.getBranchName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving order details: " + e.getMessage());
        }
    }

    /**
     * Loading order details from text file.
     */
    public void loadOrderDetails() {
        File file = new File(ORDER_DETAILS_FILE_PATH);
        if (!file.exists()) {
            return; // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] parts = line.split(",");
                int orderID = Integer.parseInt(parts[0]);
                OrderItem.Status status = OrderItem.Status.valueOf(parts[1]);
                String branchName = parts[2];
                OrderItem order = new OrderItem(orderID, status, branchName); // Use new constructor
                orders.add(order);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading order details: " + e.getMessage());
        }
    }
    
    /**
     * Starting time the order when its status has changed to READY_TO_COLLECT.
     */
    private void startTimeoutMonitorThread() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cancelDueToTimeout();
            }
        }, TIMEOUT_DURATION, TIMEOUT_DURATION); // Schedule the task to run every TIMEOUT_DURATION milliseconds
    }

    /**
     * Cancelling the order that has timed out.
     */
    private synchronized void cancelDueToTimeout() {
        long currentTime = System.currentTimeMillis();
        Iterator<OrderItem> iterator = orders.iterator();
        while (iterator.hasNext()) {
            OrderItem order = iterator.next();
            if (order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT && order.getTimeout() <= currentTime) {
                order.setOrderStatus(OrderItem.Status.CANCELLED);
                logCancelledOrder(order); // Log the cancelled order
                iterator.remove();
            }
        }
        saveOrderDetails(); // Save the updated order list to file
    }
}