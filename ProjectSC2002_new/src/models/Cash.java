package models;

import java.util.InputMismatchException;
import java.util.Scanner;

import models.Order.OrderStatus;

public class Cash extends PaymentMethod{
	Scanner scan;
	
	public Cash() {
		this.setName("Cash");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(int orderID, OrderList orderList) {
		Order order = orderList.getOrder(orderID);
		if(order != null) {
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
			orderList.editOrder(orderID, 0, OrderStatus.COMPLETED);
			System.out.println("Payment successful. Your change is $" + change +". Thank you!");
			return true;
		}
		return false; //order not found
	}
}
