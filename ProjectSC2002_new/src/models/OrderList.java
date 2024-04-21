package models;

import java.util.List;
import java.util.Random;

import controllers.DataController;

import java.util.ArrayList;

public class OrderList {
	private List<Order> orders;
	private String branchName;
	private String filePath;
	
	public OrderList(String branchName) {
		this.branchName = branchName;
		this.filePath = this.getOrdersFileNameForBranch(branchName);
		this.orders = DataController.readOrdersFromFile(filePath);
	}
	
	public String getOrdersFileNameForBranch(String branchName) {
		return "orders/" + branchName.replace(" ", "_") + "_orders.txt"; 
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
