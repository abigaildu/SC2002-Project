package controllers;

import java.util.List;
import java.util.Random;

import models.Order;

public class OrderControllerCustomer {
	public static int generateUniqueId() {
		Random random = new Random();
        int id;
        
        do {
            id = random.nextInt(1000) + 1;
        } while (getOrderItemById(id) != null);
        return id;
    }
	
	public String getBranchName() {
		return this.branchName;
	}
	
	public Order getOrder(int orderID) {
		for(Order order: orders) {
			if(order.getOrderId() == orderID) {
				return order;
			}
		}
		return null;
	}
	
	/*CRUD Operation*/
	//create
	public boolean addOrder(Order itemToAdd) {
		Order order = getOrder(itemToAdd.getOrderId());
		if(order != null) {
			return false; //order already in the list
		}
		orders.add(itemToAdd);
		return true;  //order added successfully
	}
	
	//read
	public List<Order> getOrders(){
		return this.orders;
	}
	
	//update
	public boolean editOrder(int orderIDToEdit, long newTimeout, Order.OrderStatus newOrderStatus) {
		Order order = getOrder(orderIDToEdit);
		if(order != null) {
			order.setTimeout(newTimeout);
			order.setOrderStatus(newOrderStatus);
			return true; //order updated successfully			
		}
		return false; //order ID not found
	}
	
	//delete
	public boolean deleteOrder(int orderIDToRemove) {
		Order order = getOrder(orderIDToRemove);
		if(order != null) {
			orders.remove(order);
			return true; //order removed successfully
		}
		return false; //order ID not found
	}
}
