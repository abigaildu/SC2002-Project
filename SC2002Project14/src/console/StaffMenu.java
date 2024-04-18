package console;

import java.util.Scanner;

public class StaffMenu {
    private StaffManagement staffManagement;
    private OrderStatus orderStatus;
    private OrderManagement orderManagement;
    private Scanner scanner;

    public StaffMenu(StaffManagement staffManagement, OrderStatus orderStatus, OrderManagement orderManagement) {
        this.staffManagement = staffManagement;
        this.orderStatus = orderStatus;
        this.orderManagement = orderManagement;
        this.scanner = new Scanner(System.in);
    }

//    public void checkPassword(String staffId) {
//    	Staff staff = staffManagement.getStaff(staffId);
//        if (staff == null) {
//            System.out.println("Staff ID not found.");
//            return;
//        }
//
//        // Check if the staff's password is the default one and prompt for a change
//        if ("password".equals(staff.getpassword())) {
//            System.out.println("Your password is set to the default. Please change it.");
//            if (changePassword(staffId) == false) {
//            	return;
//            }
//            displayMenu();
//            //return; // Prevents accessing the menu before changing the default password
//        }
//    }

    public void displayMenu(String staffId) {
    	this.orderManagement = OrderManagement.getInstance();
        Staff staff = staffManagement.getStaff(staffId);
        if (staff == null) {
            System.out.println("Staff ID not found.");
            return;
        }
        

        // Check if the staff's password is the default one and prompt for a change
        if ("password".equals(staff.getpassword())) {
            System.out.println("Your password is set to the default. Please change it.");
            if (changePassword(staffId) == false) {
            	return;
            }
            //return; // Prevents accessing the menu before changing the default password
        }

        int choice;
        do {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. Display New Orders");
            System.out.println("2. View Order Details");
            System.out.println("3. Display Orders Ready to Collect");
            System.out.println("4. Display Cancelled Orders");
            System.out.println("5. Change order to ready to collect");
            System.out.println("6. Delete all orders");
            System.out.println("7. Change Password");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    displayNewOrders();
                    break;
                case 2:
                	System.out.print("Enter Order ID to view: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    String details = orderManagement.getOrderDetailsById(orderId);
                    System.out.println(details);
                    break;
                case 3:
//                    System.out.print("Enter Order ID to process: ");
//                    int processOrderId = scanner.nextInt();
//                    scanner.nextLine(); // Consume the newline
//                    processOrder(staffId, processOrderId);
                	orderStatus.displayReadyForPickupOrders();
                    break;
                case 4:
                	orderStatus.displayCancelledOrders();
                	break;
                case 5:
                	System.out.print("Enter Order ID to mark as ready to collect: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    orderStatus.markOrderAsReadyToCollect(id);
                    System.out.println("Order ID " + id + " has been marked as READY_TO_COLLECT.");
                	break;
                case 6:
                    resetOrders();
                    break;
                case 7:
                    changePassword(staffId);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }
    
    
    
    public void displayNewOrders() {
        System.out.println("Displaying new orders:");
        orderStatus.displayNewOrders(); // Assumes displayNewOrders method exists in OrderStatus
    }

//    public void viewOrderDetails(int orderId) {
//        // Assuming getOrderDetailsById method exists in OrderStatus
//        String details = orderManagement.getOrderDetailsById(orderId);
//        if (details != null) {
//            System.out.println("Order details: " + details);
//        } else {
//            System.out.println("Order with ID " + orderId + " not found.");
//        }
//    }

//    public void processOrder(String staffId, int orderId) {
//        Staff staff = staffManagement.getStaff(staffId);
//        if (staff != null) {
//            // Check if staff password is default and prompt for change
//            if ("password".equals(staff.getpassword())) {
//                System.out.println("Your password is set to the default. Please change it.");
//                changePassword(staffId);
//                return; // Return to prevent processing the order before password change
//            }
//
//            // Assuming changeOrderStatus method exists in OrderStatus
//            orderStatus.changeOrderStatus(orderId, OrderItem.Status.ORDER_PROCESSING);
//            System.out.println("Order with ID " + orderId + " is now being processed.");
//        } else {
//            System.out.println("Staff ID not found.");
//        }
//    }

    private boolean changePassword(String staffId) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        staffManagement.updateStaffPassword(staffId, newPassword);
        System.out.println("Password changed successfully. Please log in again.");
        // Assuming logIn method exists for re-login after password change
        boolean status = logIn(staffId);
        return status;
    }

    private boolean logIn(String staffId) {
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        String loginSuccess = staffManagement.validateStaffLogin(staffId, password);
        if (loginSuccess == "STAFF" || loginSuccess == "MANAGER") {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Login failed. Incorrect password.");
            return false;
        }
    }
    
    public void resetOrders() {
        System.out.println("Are you sure you want to delete all orders? (Y/N)");
        String confirmation = scanner.nextLine().trim();

        if ("Y".equalsIgnoreCase(confirmation)) {
            orderManagement.clearAllOrders();
            orderStatus.clearAllOrders();
            System.out.println("All orders have been successfully deleted.");
        } else {
            System.out.println("Operation canceled.");
        }
    }
}