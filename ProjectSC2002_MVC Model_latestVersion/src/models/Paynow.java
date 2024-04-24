package models;
import java.util.Scanner;

import controllers.CartController;
import controllers.OrderControllerCustomer;
import models.Order.OrderStatus;

public class Paynow extends PaymentMethod{
	Scanner scan;
	
	public Paynow() {
		this.setName("Paynow");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(CartController cart, OrderControllerCustomer orderControllerCustomer) {
		int orderId = orderControllerCustomer.generateUniqueId();
		Order order = new Order(cart, orderId);
		float payable = order.getCart().totalCost();
		System.out.println("Payable amount by paynow: $"+ payable);
		System.out.print("Enter your phone number: ");
		scan.nextLong();
		scan.nextLine();
		orderControllerCustomer.addOrder(order);
		System.out.println("Payment is successful. Thank you!");
		return true;
	}
}
