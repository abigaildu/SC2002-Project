package controllers;

import java.util.List;

import models.MenuItem;
import models.MenuItem.Category;

public class MenuControllerManager extends MenuControllerCustomer {	
	public MenuControllerManager(String branchName) {
		super(branchName);
	}
	
	public boolean addMenuItem(MenuItem itemToAdd) {
		MenuItem menuItem = super.getMenuItemByName(itemToAdd.getName());
		if(menuItem != null) {
			return false; //menu item already exists
		}
		super.menuItems.add(itemToAdd);
		DataController.writeMenuToFile(super.menuItems, super.filePath);
		return true; //menu item added successfully
	}
	
	public boolean updateMenuItem(String originalName, String newName, float newPrice, boolean newAvailability, MenuItem.Category newCategory) {
    	MenuItem menuItem = super.getMenuItemByName(originalName);
		if(menuItem != null) {
			menuItem.setName(newName);;
			menuItem.setPrice(newPrice);
			menuItem.setAvail(newAvailability);
			menuItem.setCategory(newCategory);
			DataController.writeMenuToFile(super.menuItems, super.filePath);
			return true; //menu item updated successfully			
		}
        return false; //menu item not found
    }
	
    public boolean deleteMenuItem(String itemToRemove) {
		MenuItem menuItem = super.getMenuItemByName(itemToRemove);
		if(menuItem != null) {
			super.menuItems.remove(menuItem);
			DataController.writeMenuToFile(super.menuItems, super.filePath);
			return true; //menu item removed successfully
		}
    	return false; //menu item not found
    }
}
