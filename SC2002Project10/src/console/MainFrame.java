package console;
import java.util.Scanner;

public class MainFrame {
    private CustomerPage customerPage;
    private StaffPage staffPage;
    private BranchManagement branchManagement;
    private StaffManagement staffManagement;
    private OrderStatus orderStatus;
    private OrderManagement orderManagement;
    private StaffMenu staffMenu;
    
    public MainFrame() {
        this.customerPage = new CustomerPage(this, branchManagement, orderStatus, orderManagement);
        this.staffPage = new StaffPage(this, staffManagement, staffMenu ,orderStatus);  
        this.branchManagement = new BranchManagement(staffManagement);
        this.staffManagement = new StaffManagement(branchManagement);
        this.orderStatus = new OrderStatus();
        this.orderManagement = new OrderManagement();
        this.staffMenu = new StaffMenu(staffManagement, orderStatus, orderManagement);
    }

    void run() {
    	Scanner scanner = new Scanner(System.in);
    	int roleChoice;
    	do {
    		System.out.println("Welcome to the Fastfood ordering and management System (FOMS)!");

            System.out.println("\nYou are a:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");  // Added exit option here
            System.out.print("Your choice: ");

            roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (roleChoice) {
                case 1:
                    // Show customer interface
                    //customerPage.displayCustomerOptions();
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
        MainFrame mainFrame = new MainFrame();
        mainFrame.run();
    }
}