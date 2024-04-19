package models;

import java.util.List;

import controllers.MenuController;

public class Branch {
	private String name;
	private String location;
	private int numberOfStaffs;
	private int numberOfManagers;
	private MenuController menu;
	private OrderController orders;
	
	public Branch(String name, String location) {
		this.name = name;
		this.location = location;
		this.numberOfStaffs = 0;
		this.numberOfManagers = 0;
		this.menu = new MenuController();
		this.orders = new OrderController();
	}

	
}
