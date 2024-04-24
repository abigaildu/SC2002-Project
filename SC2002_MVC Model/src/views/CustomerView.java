package views;

import java.util.Scanner;

import controllers.BranchController;

public class CustomerView {
	private MainView main;
	private BranchController branchController;
//	private CartController cart;
    private Scanner scanner;
    
    private Menu menu;
    private Cart cart;
    private OrderStatus orderStatus;
    private OrderManagement orderManagement;
    private String branchName;

	public CustomerView(MainView main, BranchController branchController) {
		this.main = main;
		this.branchController = branchController;
		this.scanner = new Scanner(System.in);
	}

}
