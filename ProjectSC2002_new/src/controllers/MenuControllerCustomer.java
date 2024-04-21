package controllers;

import java.util.List;
import java.util.stream.Collectors;

import models.Menu;
import models.MenuItem;
import models.MenuItem.Category;

public class MenuControllerCustomer {
	protected Menu menu;
	protected List<MenuItem> menuItems;
	protected String branchName;
	protected String filePath;
	
	public MenuControllerCustomer(String branchName) {
		this.branchName = branchName;
		this.menu = new Menu(branchName);
		this.menuItems = menu.getMenu();
		this.filePath = menu.getMenuFileNameForBranch(branchName);
	}
	
	public MenuItem getMenuItemByName(String name) {
		for(MenuItem menuItem : menuItems) {
			if(menuItem.getName().equalsIgnoreCase(name.trim())){
				return menuItem;
			}
		}
		return null; //menu item not found
	}
	
	public List<MenuItem> getMenuItemsByAvailibility(boolean isAvailable) {
		return this.menuItems.stream()
				.filter(item -> item.isAvail() == isAvailable)
				.collect(Collectors.toList());
	}
	
	public List<MenuItem> getMenuItemsByCategory(Category category) {
		return this.menuItems.stream()
				.filter(item -> item.getCategory() == category)
				.collect(Collectors.toList());
	}

	public List<MenuItem> getMenu() {
		return menuItems;
	}
	
	public String getBranchName() {
		return branchName;
	}
}
