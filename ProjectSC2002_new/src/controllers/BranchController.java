package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Branch;
import models.Staff;

public class BranchController {
	private List<Branch> branchList;
    private final String fileName = "branches.txt";
	
    public BranchController() {
		branchList = DataController.readBranchListFromFile(fileName);
	}
    
    public Branch getBranchByName(String branchName) {
    	for(Branch branch: branchList) {
			if(branch.getName().equalsIgnoreCase(branchName.trim())) {
				return branch; //if branch found
			}
		}
		return null; //branch not found
	}
    	
    public List<Branch> getBranchList() {
    	return branchList;
    }
	
	public boolean addBranch(Branch branchToAdd) {
		Branch branch = this.getBranchByName(branchToAdd.getName());
		if(branch != null) {
			return false;
		}
		branchList.add(branchToAdd);
		DataController.writeBranchListToFile(branchList, fileName);
		return true;
	}

	public boolean updateBranch(String branchToUpdate, String newName, String newLocation, int newStaffQuota) {
		Branch branch = this.getBranchByName(branchToUpdate);
		if(branch != null) {
			branch.setName(newName);
			branch.setLocation(newLocation);
			branch.setStaffQuota(newStaffQuota);
			DataController.writeBranchListToFile(branchList, fileName);
			return true; //branch details updated successfully
		}
		return false; //branch not found
	}
	
	//delete
	public boolean deleteBranch(String branchToRemove) {
		Branch branch = this.getBranchByName(branchToRemove);
		if(branch != null) {
			branchList.remove(branch);
			DataController.writeBranchListToFile(branchList, fileName);
			return true; //branch removed successfully
		}
		return false; //branch not found
	}
}
