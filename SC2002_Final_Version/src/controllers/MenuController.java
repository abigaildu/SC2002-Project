package controllers;

import java.util.ArrayList;
import java.util.List;

import models.MenuItem;
import models.MenuItem.Category;

public class MenuController {
	private List<MenuItem> menu; 
	private String branchName;

	public MenuController(String branchName) {
		this.menu = new ArrayList<MenuItem>();
		this.branchName = branchName;
	}
	
	public MenuItem getMenuItemByName(String name) {
		
	}
	
	public List<MenuItem> getMenuItemsByAvailibility(boolean isAvailable) {
		
	}
	
	public List<MenuItem> getMenuItemsByCategory(Category category) {
		
	}

	public List<MenuItem> getMenu() {
		return menu;
	}
	
	public void addMenuItem(MenuItem item) {
		
	}
	
	public void updateMenuItem(String name, double price, boolean isAvailable, Category category) {
		
	}
	
	public void deleteMenuItem(String name) {
		
	}
}
