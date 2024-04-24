package models;

import java.util.Scanner;

import controllers.CartController;
import controllers.OrderControllerCustomer;
import models.Order.OrderStatus;

public class DebitCredit extends PaymentMethod{
	Scanner scan;
	public DebitCredit() {
		this.setName("Debit/Credit Card");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(CartController cart, OrderControllerCustomer orderControllerCustomer) {
		int orderId = orderControllerCustomer.generateUniqueId();
		Order order = new Order(cart, orderId);
		float payable = order.getCart().totalCost();
		System.out.println("Payable amount by credit/debit card: $"+ payable);
		System.out.print("Card number: ");
		scan.nextLong();
		scan.nextLine();
		System.out.println("CVC: ");
		scan.nextInt();
		scan.nextLine();
		orderControllerCustomer.addOrder(order);
		System.out.println("Payment is successful. Thank you!");
		return true;
	}
}
