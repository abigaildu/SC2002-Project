package models;

import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.CartController;
import controllers.OrderControllerCustomer;
import models.Order.OrderStatus;

public class Cash extends PaymentMethod{
	Scanner scan;
	
	public Cash() {
		this.setName("Cash");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(CartController cart, OrderControllerCustomer orderControllerCustomer) {
		int orderId = orderControllerCustomer.generateUniqueId();
		Order order = new Order(cart, orderId);
		float payable = order.getCart().totalCost();
		float pay = 0;
		float change = pay - payable;
		System.out.println("Payable amount by cash: $"+ payable);
		while(change < 0) {
			System.out.print("Enter amount to pay: $");
			try{
				pay = scan.nextFloat();
				scan.nextLine();
				change = pay - payable;
			}catch(InputMismatchException e) {
				System.out.println("Input only a numeric value larger than the payable amount. Try again.");
				scan.nextLine();
			}
		}
		orderControllerCustomer.addOrder(order);
		System.out.println("Payment is successful. Thank you!");
		return true;
	}
}
