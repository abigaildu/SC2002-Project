package views;

import java.util.Scanner;

import controllers.BranchController;
import controllers.StaffController;

public class MainView {
	private CustomerView customerView;
	private PersonnelView personnelView;
	private BranchController branchController;
	private StaffController staffController;
    private Scanner scanner;
	
	public MainView() {
		this.customerView = new CustomerView(this, branchController);
		this.personnelView = new PersonnelView(this, branchController, staffController);
		this.branchController = new BranchController();
		this.staffController = new StaffController();
		this.scanner = new Scanner(System.in);
	}

	void run() {
		int roleChoice;
    	do {
    		System.out.println("Welcome to the Fastfood Ordering and Management System (FOMS)!");

            System.out.println("\nYou are a:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (roleChoice) {
                case 1:
                	customerPage.selectBranchAndDisplayMenu();
                    break;
                case 2:
                    // Show staff interface
                    staffPage.showLoginOptions();
                    break;
                case 3:
                    System.out.println("Exiting the program. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
    	} while (roleChoice != 3);
	}

	public static void main(String[] args) {
		MainView mainView = new MainView();
        mainView.run();
	}

}
