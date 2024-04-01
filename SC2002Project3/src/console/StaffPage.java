package console;

import java.util.Scanner;

public class StaffPage {
	private MainFrame main;
    private Scanner scanner;
    private StaffManagement staffManagement;
    private BranchManagement branchManagement;
    private ManagerPage managerPage;

    public StaffPage(MainFrame m) {
        this.main = m;
        this.scanner = new Scanner(System.in);
        this.branchManagement = new BranchManagement(null); // Temporarily initialize with null
        this.staffManagement = new StaffManagement(branchManagement);
        this.branchManagement.setStaffManagement(staffManagement); // Set staffManagement after it's initialized
        //this.managerPage = new ManagerPage();
    }


    public void showLoginOptions(){
        System.out.println("Please Select:");
        System.out.println("1. Admin");
        System.out.println("2. Manager/Staff");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
            	loginProcess();
                break;
            case 2:
            	loginProcess();
                break;
            case 3:
            	System.out.println("Returning to the main menu...");
            	this.main.run();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }


//    private void adminLogin() {
//    	 boolean isAuthenticated = false;
//
//    	    while (!isAuthenticated) {
//    	        System.out.println("Login as Admin");
//    	        System.out.print("Enter ID (or type 'exit' to quit): ");
//    	        String id = scanner.nextLine();
//
//    	        // Check if the user wants to exit the login process
//    	        if ("exit".equalsIgnoreCase(id)) {
//    	            System.out.println("Exiting login process...");
//    	            return;
//    	        }
//
//    	        System.out.print("Enter Password: ");
//    	        String password = scanner.nextLine();
//
//    	        if ("admin".equals(id) && "admin".equals(password)) {
//    	            System.out.println("Admin logged in successfully.");
//    	            isAuthenticated = true; // Break the loop on successful authentication
//    	        } else {
//    	            System.out.println("Login failed. Incorrect ID or password. Please try again.");
//    	        }
//    	    }
//
//    	    // Proceed to show admin options only after successful login
//    	    AdminPage adminPage = new AdminPage(staffManagement, main, branchManagement);
//    	    adminPage.showAdminOptions();
//    }

    private void loginProcess() {
        System.out.print("Enter ID (or type 'exit' to return): ");
        String id = scanner.nextLine();

        if ("exit".equalsIgnoreCase(id)) {
            System.out.println("Exiting login process...");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String role = staffManagement.validateStaffLogin(id, password);
        if (role != null) {
            switch (role.toUpperCase()) {
                case "ADMIN":
                    System.out.println("Admin logged in successfully.");
                    // Redirect to AdminPage
            	    AdminPage adminPage = new AdminPage(staffManagement, main, branchManagement);
            	    adminPage.showAdminOptions();
                    break;
                case "MANAGER":
                    System.out.println("Manager logged in successfully.");
                    // Redirect to ManagerPage
                    redirectManagerToPage(id);
                    break;
                case "STAFF":
                    System.out.println("Staff logged in successfully.");
                    // Redirect to a StaffPage or similar
                    // For now, just a confirmation message or simple options
                    //showStaffOptions();
                    break;
                default:
                    System.out.println("Unknown role. Please contact system administrator.");
                    break;
            }
        } else {
            System.out.println("Login failed. Incorrect ID or password. Please try again.");
        }
    }
    
    private void redirectManagerToPage(String managerId) {
        Staff manager = staffManagement.getStaff(managerId);
        if (manager != null && manager.isBranchManager()) {
            String branchName = manager.getBranchName();
            Menu menu = branchManagement.getOrCreateMenuForBranch(branchName);
            ManagerPage managerPage = new ManagerPage(menu, branchName);
            managerPage.showManagerOptions();
        } else {
            System.out.println("Manager details not found or not authorized as a manager.");
        }
    }



}
