package views;

import java.util.Scanner;

import controllers.BranchController;
import controllers.StaffController;

public class StaffView {
	private MainView main;
	private BranchController branchController;
	private StaffController staffController;
	private Scanner scanner;
	
	public StaffView(MainView main, BranchController branchController, StaffController staffControlle) {
		this.main = main;
		this.branchController = branchController;
		this.staffController = staffController;
		this.scanner = new Scanner(System.in);
	}
}