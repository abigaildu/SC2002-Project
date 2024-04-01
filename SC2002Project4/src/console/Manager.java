package console;

import java.util.List;

public class Manager extends Staff {
    private Menu menu; // Assuming each manager has access to the branch-specific menu

    public Manager(String id, String password, String branchName, char gender, int age, Menu menu) {
        super(id, password, branchName, gender, age, true); // Automatically set as a manager
        this.menu = menu; // Each manager is associated with a branch-specific menu
    }

    // Method to display menu items managed by this manager
    public void displayMenu() {
        menu.displayMenu();
    }

    // Method to add a menu item
    public void addMenuItem(String name, String description, float price, boolean available, MenuItem.Category category) {
        menu.addMenuItem(name, description, price, available, category);
        System.out.println("Menu item added by manager: " + name);
    }

    // Method to remove a menu item
    public void removeMenuItem(String name) {
        menu.removeMenuItem(name); // Assuming Menu class has a method to remove by item name
    }

    // Method to update a menu item
    public void updateMenuItem(String name, String newDescription, float newPrice, boolean newAvail) {
        MenuItem item = menu.getMenuItemById(name); // Fetching item by name, assuming unique names
        if (item != null) {
            item.setItemDesc(newDescription);
            item.setPrice(newPrice);
            item.setAvailability(newAvail);
            System.out.println("Menu item updated by manager: " + name);
        } else {
            System.out.println("Menu item not found: " + name);
        }
    }
}
