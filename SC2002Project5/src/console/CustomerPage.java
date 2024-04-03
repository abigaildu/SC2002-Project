package console;

import java.util.List;
import java.util.Scanner;

public class CustomerPage {
	private MainFrame main;
    private Menu menu;
    private Cart cart;
    private Scanner scanner;
    private BranchManagement branchManagement;
    private OrderStatus orderStatus;
    private OrderManagement orderManagement;

    public CustomerPage(MainFrame m, BranchManagement branchManagement, OrderStatus orderStatus, OrderManagement orderManagement) {
    	this.main = m;
        this.cart = new Cart();
        this.scanner = new Scanner(System.in);
        this.branchManagement = branchManagement;
        this.orderStatus = orderStatus;
        this.orderManagement = orderManagement;
    }

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
                	orderStatus.displayNewOrders();
                	orderStatus.displayReadyForPickupOrders();
                	break;
                case 4:
                	collectOrder();
                	break;
//                	System.out.println("\nReady Orders: ");
//                	orderStatus.displayReadyForPickupOrders();
//                	System.out.println("\nThe orders you want to collect: ");
//                	List<OrderItem> readyForPickupOrders = orderStatus.getReadyForPickupOrders();
//                	for (OrderItem readyOrder : readyForPickupOrders) {
//                		System.out.println("OrderID " + readyOrder.getOrderID() + ": (Y/N)");
//                		String isCollect = scanner.nextLine();
//                		if (isCollect.equalsIgnoreCase("Y")) {
//                			orderStatus.addCompletedOrders(readyOrder.getOrderID());
//                		}
//                	}
                	
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
//            System.out.println("5. Track Order");
//            System.out.println("6. Collect Order");
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
                    int orderID = cart.checkout();
                    OrderItem newOrder = new OrderItem();
//                    OrderItem order = new OrderItem(orderID);
//                	order.toString();
                	orderStatus.addNewOrder(newOrder);
                	orderManagement.addOrder(orderID,cart);
                	
                    break;
//                case 5:
//                    orderStatus.displayOrderStatus();
//                    break;
//                case 6:
//                    orderStatus.displayReadyForPickupOrders();
//                    System.out.println("\nThe orders you want to collect: ");
//                    List<OrderItem> readyForPickupOrders = orderStatus.getReadyForPickupOrders();
//                    for (OrderItem readyOrder : readyForPickupOrders) {
//                    	System.out.println("OrderID " + readyOrder.getOrderID() + ": (Y/N)");
//                    	String isCollect = scanner.nextLine();
//                    	if (isCollect.equalsIgnoreCase("Y")) {
//                    		orderStatus.addCompletedOrders(readyOrder.getOrderID());
//                    	}
//                    }
//                    break;
                case 5:
                	this.main.run();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    public void selectBranchAndDisplayMenu() {
        System.out.println("Please select a branch from the following list:");
        branchManagement.displayOpenBranches();
        
        System.out.print("Enter the name of the branch you wish to order from: ");
        String branchName = scanner.nextLine();
        
        Branch selectedBranch = branchManagement.getBranchByName(branchName);
        if (selectedBranch != null) {
            Menu branchMenu = branchManagement.getOrCreateMenuForBranch(branchName);
            this.menu = branchMenu; // Update the CustomerPage menu reference to the branch-specific menu
            displayCustomerOptions(); // Show the branch-specific menu options
        } else {
            System.out.println("Branch not found. Please try again.");
        }
    }


    private void addItemToCart() {
        menu.displayMenu();
        System.out.print("Enter the name of the menu item you wish to add: ");
        String itemName = scanner.nextLine(); 
        //scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        MenuItem item = menu.getMenuItemById(itemName); // Assume this method exists and retrieves the correct item
        if (item != null && item.isAvailable()) {
            cart.addMenuItem(item, quantity);
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Item not found or unavailable.");
        }
    }
    
    public void collectOrder() {
        //System.out.println("Orders ready to collect:");
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
        }
        else {
        	System.out.println("OrderID " + orderId+ " not successfully collected.");
        }
    }
}