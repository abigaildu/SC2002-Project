package models;

import java.util.List;

import controllers.DataController;

import java.util.ArrayList;

public class Menu {
	private List<MenuItem> menu;
	private String branchName;
	private String filePath;
	
	
	public Menu(String branchName) {
		this.branchName = branchName;
		this.filePath = this.getMenuFileNameForBranch(branchName);
		this.menu = DataController.readMenuFromFile(filePath);
		
	}
	
	public String getMenuFileNameForBranch(String branchName) {
		return "menus/" + branchName.replace(" ", "_") + "_menu.txt"; 
	}
	
	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchName() {
		return this.branchName;
	}
}
