//package console;
//
//import java.util.*;
//import java.io.*;
//import java.util.*;
//
//import console.OrderItem.Status;
//
////Orders are automatically canceled and removed from the " ready to pickup"
////list if not picked up within a specified timeframe.
//
////Real-time updates for customers on the status of their orders, from new
////order to completed order.
//
////Process order: select order to process, update the status of the
////processed order from a new order to be “Ready to pickup”.
////New order
////Order Processing
////Ready for Pickup
////Completed/Cancelled
//
//public class OrderStatus {
//	private List<OrderItem> newOrders;
//	private List<OrderItem> processingOrders;
//	private List<OrderItem> readyForPickupOrders;
//	private List<OrderItem> completedOrders;
//	private List<OrderItem> cancelledOrders;
//    private static final String ORDER_DETAILS_FILE_PATH = "orders.txt"; // File path for saving orders
//
//	
//	public OrderStatus() {
//		this.newOrders = new ArrayList<OrderItem>();
//		this.processingOrders = new ArrayList<OrderItem>();
//		this.readyForPickupOrders = new ArrayList<OrderItem>();
//		this.completedOrders = new ArrayList<OrderItem>();
//		this.cancelledOrders = new ArrayList<OrderItem>();
//		loadOrderDetails();
//	}
//	
////	add order and get orders
//
//	public void addNewOrders(int orderID) {
//		OrderItem newOrder = new OrderItem(orderID);
//		this.newOrders.add(newOrder);
//		saveOrderDetails();
//	}
//
//	public List<OrderItem> getProcessingOrders() {
//		return processingOrders;
//	}
//	
//	public void addProcessingOrders(int orderID) {
//		for (int i = 0; i < this.newOrders.size(); i++) {
//			if (this.newOrders.get(i).getOrderID() == orderID);
//			OrderItem processingOrder = this.newOrders.remove(i);
//			processingOrder.setOrderStatus(Status.ORDER_PROCESSING);
//			this.processingOrders.add(processingOrder);
//			break;
//		}
////		need to validate the orderID...(havent done)
//	}
//
//	public List<OrderItem> getReadyForPickupOrders() {
//		return readyForPickupOrders;
//	}
//
//	public synchronized void addReadyForPickupOrders(int orderID) {
//		for (int i = 0; i < this.processingOrders.size(); i++) {
//			if (this.processingOrders.get(i).getOrderID() == orderID);
//			OrderItem readyForPickupOrder = this.processingOrders.remove(i);
//			readyForPickupOrder.setOrderStatus(Status.READY_FOR_PICKUP);
//			//Orders are automatically canceled and removed from the " ready to pickup" after 15s
//			readyForPickupOrder.setTimeout(System.currentTimeMillis() + 15000); 
//			this.readyForPickupOrders.add(readyForPickupOrder);
//			break;
//		}
////		need to validate the orderID...(havent done)
//	}
//
//	public List<OrderItem> getCompletedOrders() {
//		return completedOrders;
//	}
//
//	public void addCompletedOrders(int orderID) {
//		for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
//			if (this.readyForPickupOrders.get(i).getOrderID() == orderID);
//			OrderItem completedOrder = this.readyForPickupOrders.remove(i);
//			completedOrder.setOrderStatus(Status.COMPLETED);
//			this.readyForPickupOrders.add(completedOrder);
//			break;
//		}
////		need to validate the orderID...(havent done)
//	}
//
//	public List<OrderItem> getCancelledOrders() {
//		return cancelledOrders;
//	}
//	
//	public void addCancelledOrders(int orderID) {
//		for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
//			if (this.readyForPickupOrders.get(i).getOrderID() == orderID);
//			OrderItem cancelledOrder = this.readyForPickupOrders.remove(i);
//			cancelledOrder.setOrderStatus(Status.CANCELLED);
//			this.cancelledOrders.add(cancelledOrder);
//			break;
//		}
////		need to validate the orderID...(havent done)
//	}
//	
//
////	cancel order
//	public synchronized void cancelDueToTimedOut() {
//        long currentTime = System.currentTimeMillis();
//        for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
//        	if (this.readyForPickupOrders.get(i).getTimeout() <= currentTime) {
//        		OrderItem cancelledOrder = this.readyForPickupOrders.remove(i);
//    			cancelledOrder.setOrderStatus(Status.CANCELLED);
//    			this.cancelledOrders.add(cancelledOrder);
//    			break;
//        	}
//        }
//    }
//	
//	// Background thread to monitor and remove timed-out elements
//    private void startTimeoutMonitorThread() {
//        Thread timeoutMonitorThread = new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000); // Check every second
//                    cancelDueToTimedOut();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        timeoutMonitorThread.setDaemon(true);
//        timeoutMonitorThread.start();
//    }
////	add more cancel order method (havent done)...
//	
////	display orders
//	public void displayNewOrders() {
//		System.out.println("\nNew orders: ");
//		for (OrderItem order : newOrders) {
////			System.out.println("Order new here!!!");
//			System.out.println(order.toString());
//		}
//	}
//	
//	public void displayProcessingOrders() {
//		System.out.println("\nOrder Processing: ");
//		for (OrderItem order : processingOrders) {
////			System.out.println("Order processing here!!!");
//			System.out.println(order.toString());
//		}
//	}
//	
//	public void displayReadyForPickupOrders() {
//		System.out.println("\nReady for Pickup: ");
//		for (OrderItem order : readyForPickupOrders) {
////			System.out.println("Order ready here!!!");
//			System.out.println(order.toString());
//		}
//	}
//	
//	public void displayCompletedOrders() {
//		System.out.println("\nCompleted Orders: ");
//		for (OrderItem order : completedOrders) {
////			System.out.println("Order completed here!!!");
//			System.out.println(order.toString());
//		}
//	}
//	
//	public void displayCancelledOrders() {
//		System.out.println("\nCancelled Orders: ");
//		for (OrderItem order : cancelledOrders) {
////			System.out.println("Order cancelled here!!!");
//			System.out.println(order.toString());
//		}
//	}
//	
//	public void displayOrderStatus() {
//		this.displayNewOrders();
//		this.displayProcessingOrders();
//		this.displayReadyForPickupOrders();
//		this.displayCompletedOrders();
//		this.displayCancelledOrders();
//	}
//	
//	private void saveOrderDetails() {
//        try (PrintWriter out = new PrintWriter(new FileWriter(ORDER_DETAILS_FILE_PATH))) {
//            for (OrderItem order : newOrders) {
//                out.println(order.getOrderID() + "," + order.getOrderStatus());
//            }
//            // Include similar lines for other lists (processingOrders, readyForPickupOrders, etc.) if necessary
//        } catch (IOException e) {
//            System.err.println("Error saving order details: " + e.getMessage());
//        }
//    }
//
//    // Method to load order details from a file
//    private void loadOrderDetails() {
//        File file = new File(ORDER_DETAILS_FILE_PATH);
//        if (!file.exists()) {
//            return; // No file to load from
//        }
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                int orderID = Integer.parseInt(parts[0]);
//                OrderItem.Status status = OrderItem.Status.valueOf(parts[1]);
//                OrderItem order = new OrderItem(orderID);
//                order.setOrderStatus(status);
//                // Assuming that new orders are what we're loading
//                newOrders.add(order);
//            }
//        } catch (IOException | NumberFormatException e) {
//            System.err.println("Error loading order details: " + e.getMessage());
//        }
//    }
//    
//    public boolean changeOrderStatus(int orderID, OrderItem.Status newStatus) {
//        // Example for changing status within newOrders, extend logic for other lists as necessary
//        for (OrderItem order : newOrders) {
//            if (order.getOrderID() == orderID) {
//                order.setOrderStatus(newStatus);
//                saveOrderDetails(); // Save the state to file after modification
//                return true; // Status update successful
//            }
//        }
//        // Similar loops for processingOrders, readyForPickupOrders, etc.
//
//        return false; // Order ID not found
//    }
//}

package console;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

public class OrderStatus {
    private List<OrderItem> orders = new ArrayList<>();
    private static final String ORDER_DETAILS_FILE_PATH = "orders.txt";
    private static final long TIMEOUT_DURATION = 15 * 1000;
   
    private String staffBranch;
   // private BranchManagement branchManagement;
    public OrderStatus() {
    	
    	loadOrderDetails(); // Load orders from file on initialization
       
         // Load orders from file on initialization
        
    }
    private void logCancelledOrder(OrderItem cancelledOrder, String branchName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cancelled_orders.txt", true))) {
            writer.write("Order ID: " + cancelledOrder.getOrderID() + ", Status: " + cancelledOrder.getOrderStatus() + ", Branch: " + branchName);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error logging cancelled order: " + e.getMessage());
        }
    }
    public void setStaffBranch(String branch) {
        this.staffBranch = branch;
    }

    public void addNewOrder(OrderItem order, String branchName) {
        order.setBranchName(branchName); // Set the branch name for the order
        orders.add(order);
        saveOrderDetails(); // Save orders whenever a new order is added
    }

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



//    public void displayOrdersByStatus(OrderItem.Status status) {
//        for (OrderItem order : orders) {
//            if (order.getOrderStatus() == status) {
//                System.out.println(order);
//            }
//        }
//    }
    
    public void displayNewOrders() {
        //System.out.println(staffBranch);
        if (staffBranch == null) {
            System.out.println("Staff branch is not set. Unable to display new orders.");
            return;
        }
        
        for (OrderItem order : orders) {
            if (isOrderForBranch(order, staffBranch)) {
                if (order.getOrderStatus() == OrderItem.Status.NEW_ORDER) {
                    System.out.println(order);
                }
            }
        }
    }
    

    private boolean isOrderForBranch(OrderItem order, String staffBranch) {
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
    
    public void displayReadyForPickupOrders() {
        System.out.println("\nOrders Ready for Pickup: ");
        for (OrderItem order : orders) {
        	 if (isOrderForBranch(order, staffBranch)) {
               if (order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT) {
                    System.out.println(order);
            }
        }
    }
  }      

    // Method to display cancelled orders
    public void displayCancelledOrders() {
        System.out.println("\nCancelled Orders: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("cancelled_orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error displaying cancelled orders: " + e.getMessage());
        }
    }
  //  private boolean isOrderForCancelledBranch(OrderItem order, String staffBranch) {
    //    try (BufferedReader reader = new BufferedReader(new FileReader("cancelled_orders.txt"))) {
      //      String line;
        //    while ((line = reader.readLine()) != null) {
          //      String[] parts = line.split(",");
            //    if (parts.length >= 3 && parts[2].trim().equals(staffBranch)) {
              //      int orderIDFromFile = Integer.parseInt(parts[0].trim());
                //    if (order.getOrderID() == orderIDFromFile) {
                  //      return true;
                   // }
                //}
           // }
    
    public void clearAllOrders() {
    	orders.clear();
    	saveOrderDetails();
    }
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
    
    public boolean collectOrder(int orderId) {
        for (OrderItem order : orders) {
            if (order.getOrderID() == orderId && order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT) {
                orders.remove(order); // Remove the order from the list
                saveOrderDetails(); // Save the updated order list to file
                return true; // Indicate the order was successfully collected
            }
        }
        return false; // Indicate the order was not found or not ready to collect
    }

    // Saves order details to a file
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

    // Loads order details from a file
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
    private void startTimeoutMonitorThread() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cancelDueToTimeout();
            }
        }, TIMEOUT_DURATION, TIMEOUT_DURATION); // Schedule the task to run every TIMEOUT_DURATION milliseconds
    }

    // Method to cancel orders that have timed out
    private synchronized void cancelDueToTimeout() {
        long currentTime = System.currentTimeMillis();
        Iterator<OrderItem> iterator = orders.iterator();
        while (iterator.hasNext()) {
            OrderItem order = iterator.next();
            if (order.getOrderStatus() == OrderItem.Status.READY_TO_COLLECT && order.getTimeout() + TIMEOUT_DURATION <= currentTime) {
                order.setOrderStatus(OrderItem.Status.CANCELLED);
                logCancelledOrder(order, order.getBranchName()); // Log the cancelled order
                iterator.remove();
            }
        }
        saveOrderDetails(); // Save the updated order list to file
    }
}