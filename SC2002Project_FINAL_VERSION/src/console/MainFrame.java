package console;
import java.util.Scanner;

/**
Representing MainFrame.
*/
public class MainFrame {
	/**
	* The CustomerPage object.
	*/
    private CustomerPage customerPage;
    /**
	* The StaffPage object.
	*/
    private StaffPage staffPage;
    private Scanner scanner;
    /**
	* The BranchManagement object.
	*/
    private BranchManagement branchManagement;
    /**
	* The StaffManagement object.
	*/
    private StaffManagement staffManagement;
    /**
	* TheOrderStatus object.
	*/
    private OrderStatus orderStatus;
    /**
	* The OrderManagement object.
	*/
    private OrderManagement orderManagement;
    /**
	* The StaffMenu object.
	*/
    private StaffMenu staffMenu;
    
    /**
     * Creating a new MainFrame.
     */
    public MainFrame() {
        this.scanner = new Scanner(System.in);
        this.staffManagement = new StaffManagement(branchManagement);
        this.orderStatus = new OrderStatus();
        this.orderManagement = new OrderManagement();
        this.branchManagement = new BranchManagement(staffManagement);
        this.customerPage = new CustomerPage(this,branchManagement, orderStatus, orderManagement);
        this.staffMenu = new StaffMenu(staffManagement, orderStatus, orderManagement);
        this.staffPage = new StaffPage(this, staffManagement, staffMenu ,orderStatus); 
    }

    /**
     * Running main page.
     */
    void run() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Fast Food Management System");

            System.out.println("\nAre you a:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");  // Added exit option here
            System.out.print("Your choice: ");

            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (roleChoice) {
                case 1:
                    // Show customer interface
                	branchManagement.loadBranches();
                	customerPage.selectBranchAndDisplayMenu();
                    break;
                case 2:
                    // Show staff interface
                    staffPage.showLoginOptions();
                    break;
                case 3:
                    // Exit the program
                    running = false; // Set running to false to exit the while loop and end the program
                    System.out.println("Exiting the program. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    running = false;
                    break;
            }

            // Add a condition to break out of the while loop if the program should exit
            if (!running) {
                break;
            }
        }
    }
    
    /**
     * Main method of the program.
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.run();
    }
}