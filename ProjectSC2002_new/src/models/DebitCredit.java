package models;

import java.util.Scanner;

import models.Order.OrderStatus;

public class DebitCredit extends PaymentMethod{
	Scanner scan;
	public DebitCredit() {
		this.setName("Debit/Credit Card");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(int orderID, OrderList orderList) {
		Order order = orderList.getOrder(orderID);
		if(order != null) {
			float payable = order.getCart().totalCost();
			System.out.println("Payable amount by credit/debit card: $"+ payable);
			System.out.print("Card number: ");
			scan.nextLong();
			scan.nextLine();
			System.out.println("CVC: ");
			scan.nextInt();
			scan.nextLine();
			orderList.editOrder(orderID, 0, OrderStatus.COMPLETED);
			System.out.println("Payment is successful. Thank you!");
			return true;
		}
		return false; //order not found
	}
}
