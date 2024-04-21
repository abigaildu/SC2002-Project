package models;

import models.Order.OrderStatus;

public class PaymentMethod {
	private String name;
	
	public PaymentMethod() {
		this.name = "default";
		
	}
	
	public boolean run(int orderID, OrderList orderList) {
		Order order = orderList.getOrder(orderID);
		if(order != null) {
			float payable = order.getCart().totalCost();
			System.out.println("Payable amount: $"+ payable);
			orderList.editOrder(orderID, 0, OrderStatus.COMPLETED);
			System.out.println("Payment is successful. Thank you!");
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
