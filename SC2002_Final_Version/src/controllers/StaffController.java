package controllers;

import java.util.*;

import models.Staff;
import views.AdminView;
import views.ManagerView;
import views.StaffView;

public class StaffController {
    private List<Staff> staffList;
    private String fileName = "staff_accounts.txt";
    
    public StaffController() {
		this.staffList = new ArrayList<Staff>();
    }
    
    
    public Staff getStaffByID(String id) {
    	
    }
    
    public List<Staff> getStaffsByGender(String gender) {
    	
    }
    
    
    public List<Staff> getSt;affsByRole(String role) {
    	
    }
    

    public List<Staff> getStaffsByAgeRange(int start, int end) {
    	
    }

    public List<Staff> getStaffsByGender(String gender) {
	
    }
    
    public List<Staff> getStaffsByBranchName(String branchName) {
    	
    }
    
    public List<Staff> getStaffList() {
		return staffList;
	}

	public void addStaff(Staff staff) {
    	staffList.add(staff);
//    	update the number of staff in branch
    }
    
    public void updateStaff(String id, String name, String gender, String role, int age, String branchName) {
//    	check id
    }
    
    public void deleteStaff(String id) {
//    	
    }
    
    public void transferStaff (String id, String newBranchName) {
//    	check id
    }

    public void promoteToManager(String id) {
//    	check the number of manager and staff in branch
//    	update the number of staff in branch
    	
    }
    
    public void demoteToStaff(String id) {
//    	check the number of manager and staff in branch
//    	update the number of staff in branch
    } 
    
    public String validateLogin(String id, String password) {
    	
    }
    
    
}
