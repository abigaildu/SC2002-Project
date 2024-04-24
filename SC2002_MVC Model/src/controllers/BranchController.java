package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Staff;

public class BranchController {
	private List<Branch> branchList; 

	public BranchController() {
		this.branchList = new ArrayList<Branch>();
	}
	
	public Branch getBranchByName(String branchName) {
		
	}
	
	public List<Branch> getBranchList() {
		return branchList;
	}

	public void updateNumberOfPersonnels(char role) {
		
	}
	
	public void openBranch(Branch branch) {
		
	}
	
	public void closeBranch(String branchName) {
		
	}
	
	public void updateBranch(String branchName, String location) {
		
	}
}
