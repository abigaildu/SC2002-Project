package console;

import java.util.Scanner;

/**
Representing a StaffPage.
*/
public class StaffPage {
	/**
	* The main page.
	*/
	private MainFrame main;
    private Scanner scanner;
    /**
	* The StaffManagement object.
	*/
    private StaffManagement staffManagement;
    /**
	* The BranchManagement object.
	*/
    private BranchManagement branchManagement;
    /**
	* The OrderManagement object.
	*/
    private OrderManagement orderManagement;
    /**
	* The StaffMenu object.
	*/
    private StaffMenu staffMenu;
    /**
	* The OrderStatus object.
	*/
    private OrderStatus orderStatus;

    /**
     * Creating a new StaffPage with the given information.
     * @param main Main page.
     * @param staffManagement StaffManagement object.
     * @param staffMenu StaffMenu object.
     * @param orderStatus OrderStatus object.
     */
    public StaffPage(MainFrame main, StaffManagement staffManagement, StaffMenu staffMenu, OrderStatus orderStatus) {
        this.main = main;
        this.scanner = new Scanner(System.in);
        this.branchManagement = new BranchManagement(staffManagement);
        this.staffManagement = new StaffManagement(branchManagement);
        this.branchManagement.setStaffManagement(staffManagement); // Set staffManagement after it's initialized
        this.staffMenu = staffMenu;
        this.orderStatus = orderStatus;
    }

    /**
     * Taking input from users to select the role.
     */
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
            	adminLogin();
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
    
    /**
     * Admin logging in.
     */
    private void adminLogin() {
        System.out.print("Enter ID (or type 'exit' to return): ");
        String id = scanner.nextLine();

        if ("exit".equalsIgnoreCase(id)) {
            System.out.println("Exiting login process...");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if ("admin".equals(id) && "admin".equals(password)) {
            System.out.println("Admin logged in successfully.");
            AdminPage adminPage = new AdminPage(main, staffManagement, branchManagement);
            adminPage.showAdminOptions();
        } else {
            System.out.println("Login failed. Incorrect ID or password. Please try again.");
        }
    }

    /**
     * Logging in and validating account.
     */
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
                        System.out.println("Invalid option for manager/staff login.");
                        break;
                case "MANAGER":// Redirect to ManagerPage
                	String staffBranch = staffManagement.getStaffBranch();
                    this.orderStatus.setStaffBranch(staffBranch);
                    System.out.println("Manager logged in successfully.");
                    redirectManagerToPage(id);
                    break;
                case "STAFF":
                	
                	
                    System.out.println("Staff logged in successfully.");
                     staffBranch = staffManagement.getStaffBranch();
                    this.orderStatus.setStaffBranch(staffBranch);
                    this.staffMenu.displayMenu(id); // Show staff options
                    break;
                default:
                    System.out.println("Unknown role. Please contact system administrator.");
                    break;
            }
        } else {
            System.out.println("Login failed. Incorrect ID or password. Please try again.");
        }
    }
    
    /**
     * Redirecting manager to Manager Page.
     */
    private void redirectManagerToPage(String managerId) {
        Staff manager = staffManagement.getStaff(managerId);
        if (manager != null && manager.isBranchManager()) {
            String branchName = manager.getBranchName();
            Menu menu = branchManagement.getOrCreateMenuForBranch(branchName);
            ManagerPage managerPage = new ManagerPage(menu, branchName, staffManagement, orderStatus, orderManagement);
            managerPage.showManagerOptions(manager.getId());
        } else {
            System.out.println("Manager details not found or not authorized as a manager.");
        }
    }



}