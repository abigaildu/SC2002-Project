package console;

import java.util.Scanner;

public class CustomerPage {
	private Scanner scanner;
	
	public CustomerPage(){
		this.scanner = new Scanner(System.in);
	}
	
	public void showLoginOptions(){
		System.out.println("Please Select:");
		System.out.println("1. Menu");
		System.out.println("2. Customize Order");
		System.out.println("3. Payment");
		System.out.println("4. Order Status");
		System.out.println("5. Exit");
		System.out.print("Choose an option: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
	
		switch (choice) {
		case 1:
			showMenu();
			break;
		case 2:
			showOrder();
			break;
		case 3:
			showPayment();
			break;
		case 4:
			showOrderStatus();
			break;
	    case 5:
	        System.out.println("Exiting...");
	        break;
	    default:
	        System.out.println("Invalid option. Please try again.");
	        break;
		}
	}

	private void showOrderStatus() {
		// TODO Auto-generated method stub
		
	}

	private void showPayment() {
		// TODO Auto-generated method stub
		
	}

	private void showOrder() {
		// TODO Auto-generated method stub
		
	}

	private void showMenu() {
		// TODO Auto-generated method stub
		
	}
}
