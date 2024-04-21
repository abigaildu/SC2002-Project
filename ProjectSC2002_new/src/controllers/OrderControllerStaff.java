package controllers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import models.Order;
import models.Order.OrderStatus;

public class OrderControllerStaff extends OrderControllerCustomer {
	public OrderControllerStaff(String branchName) {
		super(branchName);
	}
	
	public List<Order> getOrdersByStatus(OrderStatus status) {
		return this.orders.stream()
				.filter(order -> order.getOrderStatus() == status)
				.collect(Collectors.toList());
	}

	public List<Order> getOrders(){
		return orders;
	}
	
	public void clearOrders() {
		orders.clear();
	}
	
	public boolean changeOrderStatusToReady (int id) {
		Order order = this.getOrderById(id);
		if(order != null) {
			order.setOrderStatus(OrderStatus.READY);
			
			order.setTimeout(System.currentTimeMillis()); // Set timeout
            startTimeoutMonitorThread(); // Start timeout monitor thread
			
			DataController.writeOrdersToFile(orders, filePath);
			return true; //order updated successfully			
		}
		return false; //order ID not found
	}
	
	private void startTimeoutMonitorThread() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cancelDueToTimeout();
            }
        }, TIMEOUT_DURATION);
    }
//	
//	private void startTimeoutMonitorThread() {
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

    // Method to cancel orders that have timed out
    private synchronized void cancelDueToTimeout() {
        long currentTime = System.currentTimeMillis();
        for (Order order : orders) {
        	if (order.getOrderStatus() == OrderStatus.READY && order.getTimeout() + TIMEOUT_DURATION <= currentTime) {
        		order.setOrderStatus(OrderStatus.CANCELLED);
        		DataController.writeOrdersToFile(orders, filePath);
        	}
        }
    }
}
