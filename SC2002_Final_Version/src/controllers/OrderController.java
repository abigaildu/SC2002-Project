package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.OrderItem;
import models.OrderItem.OrderStatus;

public class OrderController {
	private List<OrderItem> orders; 
	private String branchName;

	public OrderController(String branchName) {
		this.orders = new ArrayList<OrderItem>();
		this.branchName = branchName;
	}
	
	public int generateUniqueId() {
		Random random = new Random();
        int id;
        
        do {
            id = random.nextInt(1000) + 1;
        } while (getOrderItemById(id) != null);
        return id;
    }
	
	public OrderItem getOrderItemById(int id) {
		
	}
	
	public List<OrderItem> getOrdersByStatus(OrderStatus status) {
		
	}
	
	public void addOrderItem(OrderItem orderItem) {
		this.orderId = generateUniqueId();
	}
	
	public void updateOrderStatus(int id, OrderStatus status) {
		
	}

	public void deleteOrder(int id) {
		
	}
	
	public List<OrderItem> getOrders() {
		return orders;
	}
}
