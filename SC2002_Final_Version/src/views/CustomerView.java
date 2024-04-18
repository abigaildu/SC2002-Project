package views;

import java.util.Scanner;

import controllers.BranchController;

public class CustomerView {
	private MainView main;
	private BranchController branchController;
    private Scanner scanner;

	public CustomerView(MainView main, BranchController branchController) {
		this.main = main;
		this.branchController = branchController;
		this.scanner = new Scanner(System.in);
	}

}
