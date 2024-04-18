package console;

public class MenuBranch extends Branch {
    private Menu menu; // Each MenuBranch has a unique Menu

    public MenuBranch(String name, String daysOpen, String openingHours, String location, String menuFilePath) {
        super(name, daysOpen, openingHours,location); // Call the parent constructor
        this.menu = new Menu(menuFilePath); // Initialize a new menu for this branch
    }

    // Getter and setter for the menu
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // Additional functionalities related to the menu can be added here, for example:
    public void addMenuItem(String name, String description, float price, boolean available, MenuItem.Category category) {
        menu.addMenuItem(name, description, price, available, category);
        System.out.println("Added menu item to " + getName() + ": " + name);
    }

    public void displayMenu() {
        System.out.println("Menu for " + getName() + ":");
        menu.displayMenu();
    }

    // If necessary, override other methods from Branch
}