package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.*;
import java.nio.file.*;
import java.util.*;



import console.MenuItem.Category;

public class Menu {
    private List<MenuItem> menuItems;
    private String filePath;

    public Menu(String filePath) {
        this.menuItems = new ArrayList<>();
        this.filePath = filePath;
    }

    public void addMenuItem(String name, String description, float price, boolean available, Category category) {
        // Check if an item with the same name already exists
        MenuItem existingItem = getMenuItemById(name);
        if (existingItem != null) {
            System.out.println("A menu item with the name '" + name + "' already exists.");
            return; // Return to the original page
        }

        // If the item does not exist, add it to the menu
        MenuItem newItem = new MenuItem(name, description, price, available, category);
        menuItems.add(newItem);
        System.out.println("Added new menu item: " + name + " under " + category + " category.");
        saveMenuToFile();
        loadMenuFromFile();
        System.out.println("Menu item added successfully.");
    }
    
    public boolean editMenuItem(String originalName, String newName, String newDescription, float newPrice, boolean newAvailability, MenuItem.Category newCategory) {
        for (MenuItem item : menuItems) {
            if (item.getItemName().equalsIgnoreCase(originalName)) {
                item.setItemName(newName);
                item.setItemDesc(newDescription);
                item.setPrice(newPrice);
                item.setAvailability(newAvailability);
                item.setCategory(newCategory);
                System.out.println("Menu item updated successfully.");
                saveMenuToFile();
                loadMenuFromFile();
                return true;
            }
        }
        System.out.println("Menu item not found.");
        return false;
    }	

    // Method to display all items, optionally filtered by category
    public void displayMenu() {
    	loadMenuFromFile();
        System.out.println("--- Full Menu ---");
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }

    // Overloaded method to display items by category
    public void displayMenu(Category category) {
        System.out.println("--- " + category + " Menu ---");
        for (MenuItem item : menuItems) {
            if (item.getCategory() == category) {
                System.out.println(item);
            }
        }
    }
    
    public MenuItem getMenuItemById(String itemName) {
        for (MenuItem item : menuItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Return null if the item is not found.
    }
    
    public void removeMenuItem(String name) {
        // Using an iterator to avoid ConcurrentModificationException while removing
        Iterator<MenuItem> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            if (item.getItemName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Removed menu item: " + name);
                saveMenuToFile();
                loadMenuFromFile();
                return;
            }
        }
        System.out.println("Menu item not found: " + name);
    }
    
    
    public void saveMenuToFile() {
        ensureDirectoryExists(this.filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.filePath))) {
            for (MenuItem item : this.menuItems) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving menu to file: " + e.getMessage());
        }
    }

    // Method to load the menu from a file
    public void loadMenuFromFile() {
        ensureDirectoryExists(this.filePath);
        this.menuItems.clear();
        Path path = Paths.get(this.filePath);
        if (Files.exists(path)) { // Check if the file actually exists before attempting to read
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    MenuItem item = MenuItem.fromString(line);
                    if (item != null) {
                        this.menuItems.add(item);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading menu from file: " + e.getMessage());
            }
        } else {
            System.out.println("Menu file does not exist and will be created upon saving.");
        }
    }
    
    private void ensureDirectoryExists(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + e.getMessage());
        }
    }

}