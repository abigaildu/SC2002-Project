package console;

import java.util.Scanner;

public class StaffPage {
	private MainFrame main;
    private Scanner scanner;
    private StaffManagement staffManagement;
    private BranchManagement branchManagement;

    public StaffPage(MainFrame m) {
        this.main = m;
        this.scanner = new Scanner(System.in);
        this.branchManagement = new BranchManagement(null); // Temporarily initialize with null
        this.staffManagement = new StaffManagement(branchManagement);
        this.branchManagement.setStaffManagement(staffManagement); // Set staffManagement after it's initialized
    }


    public void showLoginOptions(){
        System.out.println("Please Select:");
        System.out.println("1. Admin");
        System.out.println("2. Manager");
        System.out.println("3. Staff");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 2:
                managerLogin();
                break;
            case 3:
                staffLogin();
                break;
            case 4:
            	System.out.println("Returning to the main menu...");
            	this.main.run();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void staffLogin() {
        // Common login method for all types of staff since the differentiation is not implemented yet
        loginProcess("Staff");
    }

    private void managerLogin() {
        // Common login method for all types of staff since the differentiation is not implemented yet
        loginProcess("Manager");
    }

    private void adminLogin() {
    	 boolean isAuthenticated = false;

    	    while (!isAuthenticated) {
    	        System.out.println("Login as Admin");
    	        System.out.print("Enter ID (or type 'exit' to quit): ");
    	        String id = scanner.nextLine();

    	        // Check if the user wants to exit the login process
    	        if ("exit".equalsIgnoreCase(id)) {
    	            System.out.println("Exiting login process...");
    	            return;
    	        }

    	        System.out.print("Enter Password: ");
    	        String password = scanner.nextLine();

    	        if ("admin".equals(id) && "admin".equals(password)) {
    	            System.out.println("Admin logged in successfully.");
    	            isAuthenticated = true; // Break the loop on successful authentication
    	        } else {
    	            System.out.println("Login failed. Incorrect ID or password. Please try again.");
    	        }
    	    }

    	    // Proceed to show admin options only after successful login
    	    AdminPage adminPage = new AdminPage(staffManagement, main, branchManagement);
    	    adminPage.showAdminOptions();
    }

    private void loginProcess(String role) {
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.print("Enter ID (or type 'exit' to return): ");
            String id = scanner.nextLine();
            
            // Allow the user to exit the login process
            if ("exit".equalsIgnoreCase(id)) {
                System.out.println("Exiting login process...");
                return;
            }

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (staffManagement.validateStaffLogin(id, password)) {
                System.out.println(role + " logged in successfully.");
                loginSuccess = true; // Breaks the loop
            } else {
                System.out.println("Login failed. Incorrect ID or password. Please try again.");
            }
        }
    }
}
