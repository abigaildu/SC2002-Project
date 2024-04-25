package console;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
Representing a CustomerPage.
*/
public class CustomerPage {
	/**
	* The main page.
	*/
	private MainFrame main;
	/**
	* The Menu object.
	*/
    private Menu menu;
    /**
	* The Cart object.
	*/
    private Cart cart;
    private Scanner scanner;
    /**
	* The BranchManagement object.
	*/
    private BranchManagement branchManagement;
    /**
	* The OrderStatus object.
	*/
    private OrderStatus orderStatus;
    /**
	* The OrderManagement object.
	*/
    private OrderManagement orderManagement;
    /**
	* The branch name.
	*/
    private String branchName;
    
    /**
     * Creating a new CustomerPage with the given information.
     * @param main Main page.
     * @param branchManagement BranchManagement object.
     * @param orderStatus OrderStatus object.
     * @param orderManagement OrderManagement object.
     */
    public CustomerPage(MainFrame main, BranchManagement branchManagement, OrderStatus orderStatus, OrderManagement orderManagement) {
    	this.main = main;
        //this.cart = new Cart(orderId);
    	this.cart = new Cart(generateUniqueCartID());
        this.scanner = new Scanner(System.in);
        this.branchManagement = branchManagement;
        this.orderStatus = orderStatus;
        this.orderManagement = orderManagement;
    }
    
    /**
     * Generating a unique cart ID.
     * @return A unique cart ID.
     */
    public static int generateUniqueCartID() {
    	Random random = new Random();
        int id;
        do {
            id = random.nextInt(1000) + 1;
        } while(isIDUsed(id));
        addUsedID(id);
        return id;
    }
    
    /**
     * Taking input from users to perform customer's functions.
     */
    public void displayCustomerOptions() {
    	int choice = 0;
    	do {
            System.out.println("\n--- Dining Option ---");
            System.out.println("1. Dine-in");
            System.out.println("2. Takeaway");
            System.out.println("---------------------");
            System.out.println("3. Track Order");
            System.out.println("4. Collect Order");
            System.out.println("5. Exit");
            
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                	cart.setIsDineIn(true);
                    break;
                case 2:
                	cart.setIsDineIn(false);
                    break;
                case 3:
                	System.out.print("Enter the OrderID you want to track: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline
                    orderStatus.trackOrder(orderId);
                	break;
                case 4:
                	collectOrder();
                	break;
                	
                case 5:
                	this.main.run();
                	break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 1 && choice != 2);
        
    	while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Browse Menu");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    addItemToCart();
                    break;
                case 3:
                    cart.displayCartItems();
                    break;
                case 4:
                	
                    cart.checkout(branchName); //just for payment only
                    OrderItem newOrder = new OrderItem(cart.getCartID());
                    orderStatus.addNewOrder(newOrder, branchName);
                	orderManagement.addOrder(cart.getCartID(),cart);
                	
                    break;
                case 5:
                	this.main.run();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    /**
     * Selecting Branch and Displaying Menu according to that Branch.
     */
    public void selectBranchAndDisplayMenu() {
        System.out.println("Please select a branch from the following list:");
        branchManagement.displayOpenBranches();
        
        System.out.print("Enter the name of the branch you wish to order from: ");
        String branchName = scanner.nextLine();
        
        Branch selectedBranch = branchManagement.getBranchByName(branchName);
        if (selectedBranch != null) {
            Menu branchMenu = branchManagement.getOrCreateMenuForBranch(branchName);
            this.menu = branchMenu; // Update the CustomerPage menu reference to the branch-specific menu
            this.branchName = branchName;
            displayCustomerOptions(); // Show the branch-specific menu options
        } else {
            System.out.println("Branch not found. Please try again.");
        }
    }

    /**
     * Adding an item to Cart.
     */
    private void addItemToCart() {
        menu.displayMenu();
        System.out.print("Enter the name of the menu item you wish to add: ");
        String itemName = scanner.nextLine(); 
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        MenuItem item = menu.getMenuItemByName(itemName); // Assume this method exists and retrieves the correct item
        if (item != null && item.isAvail()) {
            cart.addMenuItem(item, quantity);
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Item not found or unavailable.");
        }
    }
    
    /**
     * Collecting the Order.
     */
    public void collectOrder() {
        orderStatus.displayReadyForPickupOrders(); // Display orders ready for pickup
        
        System.out.println("\nEnter the OrderID you want to collect, or type '0' to exit:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Early exit if the user chooses '0'
        if (orderId == 0) {
            return;
        }
        boolean collected = orderStatus.collectOrder(orderId);
        if  (collected == true) {
        	System.out.println("OrderID " + orderId+ " successfully collected.");
        	cart.removeCartDetails(orderId);
        }
        else {
        	System.out.println("OrderID " + orderId+ " not successfully collected.");
        }
    }
    
    /**
     * Checking if an ID is used.
     * @param id ID to check.
     * @return State to indicate whether the ID is used.
     */
    private static boolean isIDUsed(int id) {
        return UsedID.isIDUsed(id);
    }
    
    /**
     * Adding a new used ID.
     * @param id ID to add.
     */
    private static void addUsedID(int id) {
        UsedID.addUsedID(id);
    }  
}