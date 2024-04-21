package controllers;

import java.util.List;
import java.util.stream.Collectors;

import models.Staff;

public class StaffController {
	protected List<Staff> staffList;
    protected final String fileName = "staff_accounts.txt";

	public StaffController() {
		staffList = DataController.readStaffAccountsFromFile(fileName);
	}
	
	
    public String validateLogin(String id, String password) {
    	Staff staff = this.getStaffById(id);
    	
    	if (staff != null && staff.getPassword().equals(password)) {
    		return staff.getRole();
    	}
        return null;
    }
    
    public boolean changePassword(String oldPassword, String newPassword) {
    	Staff staff = this.getStaffByPassword(oldPassword);
    	
    	if (staff != null) {
    		staff.setPassword(newPassword);
    		return true;
    	} 
    	return false;
    }
    
    public Staff getStaffById(String id) {
    	for(Staff staff : staffList) {
			if(staff.getId().equalsIgnoreCase(id.trim())) {
				return staff;
			}
		}
		return null;
    }
    
    private Staff getStaffByPassword(String password) {
    	for(Staff staff : staffList) {
			if(staff.getPassword().equals(password)) {
				return staff;
			}
		}
		return null;
    }
    
    public List<Staff> getStaffsByGender(String gender) {
    	return this.staffList.stream()
    			.filter(staff -> staff.getGender().equalsIgnoreCase(gender))
    			.collect(Collectors.toList());
    }
    
    
    public List<Staff> getStaffsByRole(String role) {
    	return this.staffList.stream()
    			.filter(staff -> staff.getRole().equalsIgnoreCase(role))
    			.collect(Collectors.toList());
    }
    

    public List<Staff> getStaffsByAgeRange(int start, int end) {
    	return this.staffList.stream()
    			.filter(staff -> staff.getAge() <= end && staff.getAge() >= end)
    			.collect(Collectors.toList());
    }
    
    public List<Staff> getStaffsByBranchName(String branchName) {
    	return this.staffList.stream()
    			.filter(staff -> staff.getBranchName().equalsIgnoreCase(branchName))
    			.collect(Collectors.toList());
    }
    
    public List<Staff> getStaffList() {
		return staffList;
	}
    
    public int getNumberOfStaffsInBranch(String branchName) {
    	return (int) this.getStaffsByBranchName(branchName).stream()
    			.filter(staff -> staff.getRole().equalsIgnoreCase("S"))
    			.count();
    }
    
    public int getNumberOfManagersInBranch(String branchName) {
    	return (int) this.getStaffsByBranchName(branchName).stream()
    			.filter(staff -> staff.getRole().equalsIgnoreCase("M"))
    			.count();
    }
}
