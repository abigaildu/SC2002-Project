package controllers;

import java.util.List;
import java.util.Random;

import models.Menu;
import models.MenuItem;
import models.Order;
import models.Order.OrderStatus;
import models.OrderList;

public class OrderControllerCustomer {
	protected OrderList orderList;
	protected List<Order> orders;
	protected String branchName;
	protected String filePath;
	protected static final long TIMEOUT_DURATION = 10 * 1000;
	
	public OrderControllerCustomer(String branchName) {
		this.branchName = branchName;
		this.orderList = new OrderList(branchName);
		this.orders = orderList.getOrders();
		this.filePath = orderList.getOrdersFileNameForBranch(branchName);
	}

	public int generateUniqueId() {
		Random random = new Random();
        int id;
        do {
            id = random.nextInt(1000) + 1;
        } while(this.getOrderById(id) != null);
        return id;
    }
	
	public Order getOrderById(int id) {
		for(Order order: orders) {
			if(order.getOrderId() == id) {
				return order;
			}
		}
		return null;
	}
	
	public String getBranchName() {
		return this.branchName;
	}
	
	public boolean addOrder(Order itemToAdd) {
		Order order = getOrderById(itemToAdd.getOrderId());
		if(order != null) {
			return false; //order already in the list
		}
		orders.add(itemToAdd);
		DataController.writeOrdersToFile(orders, filePath);
		return true;  //order added successfully
	}
	
	public boolean changeOrderStatusToCompleted (int id) {
		Order order = this.getOrderById(id);
		if(order != null) {
			order.setOrderStatus(OrderStatus.COMPLETED);
			DataController.writeOrdersToFile(orders, filePath);
			return true; //order updated successfully			
		}
		return false; //order ID not found
	}
	
	public boolean changeOrderStatusToCancelled (int id) {
		Order order = this.getOrderById(id);
		if(order != null) {
			order.setOrderStatus(OrderStatus.CANCELLED);
			DataController.writeOrdersToFile(orders, filePath);
			return true; //order updated successfully			
		}
		return false; //order ID not found
	}
	
	public boolean deleteOrder(int orderIDToRemove) {
		Order order = this.getOrderById(orderIDToRemove);
		if(order != null) {
			orders.remove(order);
			DataController.writeOrdersToFile(orders, filePath);
			return true; //order removed successfully
		}
		return false; //order ID not found
	}
	
//	public boolean editOrder(int orderIDToEdit, long newTimeout, Order.OrderStatus newOrderStatus) {
//	Order order = getOrder(orderIDToEdit);
//	if(order != null) {
//		order.setTimeout(newTimeout);
//		order.setOrderStatus(newOrderStatus);
//	    DataController.writeOrdersToFile(orders, filePath);
//		return true; //order updated successfully			
//	}
//	return false; //order ID not found
//}
}
