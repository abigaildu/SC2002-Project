package console;

import java.util.Scanner;
import java.util.List;
import console.Staff;
//import console.StaffMenu;

public class ManagerPage {
    private Scanner scanner;
    private Menu menu; // The menu this manager is responsible for
    private String branchName; // The name of the branch this manager oversees
    private StaffMenu staffMenu;
    private StaffManagement staffManagement;
    private OrderManagement orderManagement;
    
    

    public ManagerPage(Menu menu, String branchName, StaffManagement staffManagement, OrderStatus orderStatus, OrderManagement orderManagement) {
        this.scanner = new Scanner(System.in);
        this.menu = menu;
        this.branchName = branchName;
        this.orderManagement =  orderManagement;
        this.staffMenu = new StaffMenu(staffManagement, orderStatus, orderManagement);
        this.staffManagement = staffManagement;
        
        
    }

    public void showManagerOptions(String id) {
    	
    	Staff staff = staffManagement.getStaff(id);
    	
    	if ("password".equals(staff.getpassword())) {
            System.out.println("Your password is set to the default. Please change it.");
            if (changePassword(id) == false) {
            	return;
            }
            //return; // Prevents accessing the menu before changing the default password
        }
    	
        boolean running = true;
        while (running) {
            System.out.println("\nManager Options for Branch: " + branchName);
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. View Menu");
            System.out.println("5. Display Stafflist");
            System.out.println("6. View Staff Options");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    editMenuItem();
                    break;
                case 4:
                    viewMenu();
                    break;
                case 5:
                	displayStaffList();
                	break;
                case 6:
                	//staff options
                	staffMenu.displayMenu(id);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addMenuItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        float price = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        System.out.print("Is the item available (true/false)? ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter category (FOOD, DRINK, DESSERT): ");
        String categoryStr = scanner.nextLine().toUpperCase();
        MenuItem.Category category = MenuItem.Category.valueOf(categoryStr);

        menu.addMenuItem(name, description, price, available, category);
        //System.out.println("Menu item added successfully.");
    }

    private void removeMenuItem() {
        System.out.print("Enter item name to remove: ");
        String name = scanner.nextLine();
        menu.removeMenuItem(name);
    }

    private void editMenuItem() {
        System.out.print("Enter original item name: ");
        String originalName = scanner.nextLine();
        System.out.print("Enter new item name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new price: ");
        float newPrice = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        System.out.print("Is the item available (true/false)? ");
        boolean newAvailable = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new category (FOOD, DRINK, DESSERT): ");
        String newCategoryStr = scanner.nextLine().toUpperCase();
        MenuItem.Category newCategory = MenuItem.Category.valueOf(newCategoryStr);

        boolean updated = menu.editMenuItem(originalName, newName, newDescription, newPrice, newAvailable, newCategory);
        if (updated) {
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("Failed to update the menu item. Item may not exist.");
        }
    }

    private void viewMenu() {
        System.out.println("Current Menu:");
        menu.displayMenu();
    }
    private void displayStaffList() {
        List<Staff> staffList = staffManagement.getStaffListForBranch(branchName);
        if (staffList.isEmpty()) {
            System.out.println("No staff found for this branch.");
        } else {
            System.out.println("Staff List for Branch: " + branchName);
            for (Staff staff : staffList) {
            	System.out.println("- ID: " + staff.getid());
            }
        }
    }

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

    // Additional functionalities can be implemented here as needed.
}