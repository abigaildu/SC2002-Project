package models;
import java.util.Scanner;
import models.Order.OrderStatus;

public class Paynow extends PaymentMethod{
	Scanner scan;
	public Paynow() {
		this.setName("Paynow");
		scan = new Scanner(System.in);
	}
	
	/*@Override*/
	public boolean run(int orderID, OrderList orderList) {
		Order order = orderList.getOrder(orderID);
		if(order != null) {
			float payable = order.getCart().totalCost();
			System.out.println("Payable amount by paynow: $"+ payable);
			System.out.print("Enter your phone number: ");
			
			scan.nextLong();
			scan.nextLine();
			orderList.editOrder(orderID, 0, OrderStatus.COMPLETED);
			System.out.println("Payment is successful. Thank you!");
			return true;
		}
		return false; //order not found
	}
}
