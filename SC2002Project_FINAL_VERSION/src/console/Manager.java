package console;

import java.util.List;

/**
Representing a Manager.
*/
public class Manager extends Staff {
	/**
	* The Menu object.
	*/
    private Menu menu; // Assuming each manager has access to the branch-specific menu

    /**
     * Creating a new Manager with the given information.
     * @param id This Manager's id.
     * @param password This Manager's password.
     * @param branchName This Manager's branch name.
     * @param gender This Manager's gender.
     * @param age This Manager's age.
     * @param menu This Manager branch's menu.
     */
    public Manager(String id, String password, String branchName, char gender, int age, Menu menu) {
        super(id, password, branchName, gender, age, true); // Automatically set as a manager
        this.menu = menu; // Each manager is associated with a branch-specific menu
    }

    /**
     * Displaying menu.
     */
    public void displayMenu() {
        menu.displayMenu();
    }

    /**
     * Adding a new MenuItem.
     * @param name MenuItem's name.
     * @param description MenuItem's description.
     * @param price MenuItem's price.
     * @param isAvail MenuItem's state of availability.
     * @param category MenuItem's category.
     */
    public void addMenuItem(String name, String description, float price, boolean isAvail, MenuItem.Category category) {
        menu.addMenuItem(name, description, price, isAvail, category);
        System.out.println("Menu item added by manager: " + name);
    }

    /**
     * Removing a MenuItem.
     * @param name MenuItem's name.
     */
    public void removeMenuItem(String name) {
        menu.removeMenuItem(name); // Assuming Menu class has a method to remove by item name
    }

    /**
     * Updating a MenuItem.
     * @param name MenuItem's original name.
     * @param newDescription MenuItem's new description.
     * @param newPrice MenuItem's new price.
     * @param newAvail MenuItem's new state of availability.
     */
    public void updateMenuItem(String name, String newDescription, float newPrice, boolean newAvail) {
        MenuItem item = menu.getMenuItemByName(name); // Fetching item by name, assuming unique names
        if (item != null) {
            item.setItemDesc(newDescription);
            item.setPrice(newPrice);
            item.setAvail(newAvail);
            System.out.println("Menu item updated by manager: " + name);
        } else {
            System.out.println("Menu item not found: " + name);
        }
    }
}