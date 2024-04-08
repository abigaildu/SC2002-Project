package console;
import java.util.Scanner;

public class MainFrame {
    private CustomerPage customerPage;
    private StaffPage staffPage;
    private Scanner scanner;
    private BranchManagement branchManagement;
    private StaffManagement staffManagement;
    private OrderStatus orderStatus;
    private OrderManagement orderManagement;
    private StaffMenu staffMenu;
    
    public MainFrame() {
        this.scanner = new Scanner(System.in);
        this.orderStatus = new OrderStatus();
        this.orderManagement = new OrderManagement();
        this.staffManagement = new StaffManagement(branchManagement);
        this.branchManagement = new BranchManagement(staffManagement);
        this.customerPage = new CustomerPage(this,branchManagement, orderStatus, orderManagement);
        this.staffMenu = new StaffMenu(staffManagement, orderStatus, orderManagement);
        this.staffPage = new StaffPage(this, staffManagement, staffMenu ,orderStatus);
        
        
        
    }

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
                    //customerPage.displayCustomerOptions();
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
    
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.run();
    }
}
