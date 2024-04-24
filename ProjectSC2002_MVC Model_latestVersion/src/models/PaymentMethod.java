package models;

import java.util.List;

import controllers.CartController;
import controllers.OrderControllerCustomer;
import models.Order.OrderStatus;

public class PaymentMethod {
	private String name;
	
	public PaymentMethod() {
		this.name = "default";
		
	}
	
	public boolean run(CartController cart, OrderControllerCustomer orderControllerCustomer) {
		int orderId = orderControllerCustomer.generateUniqueId();
		Order order = new Order(cart, orderId);
		float payable = order.getCart().totalCost();
		System.out.println("Payable amount: $"+ payable);
		orderControllerCustomer.addOrder(order);
		System.out.println("Payment is successful. Thank you!");
		return true;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
