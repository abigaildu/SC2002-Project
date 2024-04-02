package console;

import java.util.*;

import console.OrderItem.Status;

//Orders are automatically canceled and removed from the " ready to pickup"
//list if not picked up within a specified timeframe.

//Real-time updates for customers on the status of their orders, from new
//order to completed order.

//Process order: select order to process, update the status of the
//processed order from a new order to be “Ready to pickup”.
//New order
//Order Processing
//Ready for Pickup
//Completed/Cancelled

public class OrderStatus {
	private List<OrderItem> newOrders;
	private List<OrderItem> processingOrders;
	private List<OrderItem> readyForPickupOrders;
	private List<OrderItem> completedOrders;
	private List<OrderItem> cancelledOrders;
	
	public OrderStatus() {
		this.newOrders = new ArrayList<OrderItem>();
		this.processingOrders = new ArrayList<OrderItem>();
		this.readyForPickupOrders = new ArrayList<OrderItem>();
		this.completedOrders = new ArrayList<OrderItem>();
		this.cancelledOrders = new ArrayList<OrderItem>();
	}
	
//	add order and get orders

	public void addNewOrders(int orderID) {
		OrderItem newOrder = new OrderItem(orderID);
		this.newOrders.add(newOrder);
	}

	public List<OrderItem> getProcessingOrders() {
		return processingOrders;
	}
	
	public void addProcessingOrders(int orderID) {
		for (int i = 0; i < this.newOrders.size(); i++) {
			if (this.newOrders.get(i).getOrderID() == orderID);
			OrderItem processingOrder = this.newOrders.remove(i);
			processingOrder.setOrderStatus(Status.ORDER_PROCESSING);
			this.processingOrders.add(processingOrder);
			break;
		}
//		need to validate the orderID...(havent done)
	}

	public List<OrderItem> getReadyForPickupOrders() {
		return readyForPickupOrders;
	}

	public synchronized void addReadyForPickupOrders(int orderID) {
		for (int i = 0; i < this.processingOrders.size(); i++) {
			if (this.processingOrders.get(i).getOrderID() == orderID);
			OrderItem readyForPickupOrder = this.processingOrders.remove(i);
			readyForPickupOrder.setOrderStatus(Status.READY_FOR_PICKUP);
			//Orders are automatically canceled and removed from the " ready to pickup" after 15s
			readyForPickupOrder.setTimeout(System.currentTimeMillis() + 15000); 
			this.readyForPickupOrders.add(readyForPickupOrder);
			break;
		}
//		need to validate the orderID...(havent done)
	}

	public List<OrderItem> getCompletedOrders() {
		return completedOrders;
	}

	public void addCompletedOrders(int orderID) {
		for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
			if (this.readyForPickupOrders.get(i).getOrderID() == orderID);
			OrderItem completedOrder = this.readyForPickupOrders.remove(i);
			completedOrder.setOrderStatus(Status.COMPLETED);
			this.readyForPickupOrders.add(completedOrder);
			break;
		}
//		need to validate the orderID...(havent done)
	}

	public List<OrderItem> getCancelledOrders() {
		return cancelledOrders;
	}
	
	public void addCancelledOrders(int orderID) {
		for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
			if (this.readyForPickupOrders.get(i).getOrderID() == orderID);
			OrderItem cancelledOrder = this.readyForPickupOrders.remove(i);
			cancelledOrder.setOrderStatus(Status.CANCELLED);
			this.cancelledOrders.add(cancelledOrder);
			break;
		}
//		need to validate the orderID...(havent done)
	}
	

//	cancel order
	public synchronized void cancelDueToTimedOut() {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < this.readyForPickupOrders.size(); i++) {
        	if (this.readyForPickupOrders.get(i).getTimeout() <= currentTime) {
        		OrderItem cancelledOrder = this.readyForPickupOrders.remove(i);
    			cancelledOrder.setOrderStatus(Status.CANCELLED);
    			this.cancelledOrders.add(cancelledOrder);
    			break;
        	}
        }
    }
	
	// Background thread to monitor and remove timed-out elements
    private void startTimeoutMonitorThread() {
        Thread timeoutMonitorThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Check every second
                    cancelDueToTimedOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeoutMonitorThread.setDaemon(true);
        timeoutMonitorThread.start();
    }
//	add more cancel order method (havent done)...
	
//	display orders
	public void displayNewOrders() {
		System.out.println("\nNew orders: ");
		for (OrderItem order : newOrders) {
//			System.out.println("Order new here!!!");
			System.out.println(order.toString());
		}
	}
	
	public void displayProcessingOrders() {
		System.out.println("\nOrder Processing: ");
		for (OrderItem order : processingOrders) {
//			System.out.println("Order processing here!!!");
			System.out.println(order.toString());
		}
	}
	
	public void displayReadyForPickupOrders() {
		System.out.println("\nReady for Pickup: ");
		for (OrderItem order : readyForPickupOrders) {
//			System.out.println("Order ready here!!!");
			System.out.println(order.toString());
		}
	}
	
	public void displayCompletedOrders() {
		System.out.println("\nCompleted Orders: ");
		for (OrderItem order : completedOrders) {
//			System.out.println("Order completed here!!!");
			System.out.println(order.toString());
		}
	}
	
	public void displayCancelledOrders() {
		System.out.println("\nCancelled Orders: ");
		for (OrderItem order : cancelledOrders) {
//			System.out.println("Order cancelled here!!!");
			System.out.println(order.toString());
		}
	}
	
	public void displayOrderStatus() {
		this.displayNewOrders();
		this.displayProcessingOrders();
		this.displayReadyForPickupOrders();
		this.displayCompletedOrders();
		this.displayCancelledOrders();
	}
}
