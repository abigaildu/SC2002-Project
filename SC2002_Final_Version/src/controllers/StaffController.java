package controllers;

import java.util.*;

import models.Staff;
import views.AdminView;
import views.ManagerView;
import views.StaffView;

public class StaffController {
    private List<Staff> staffList; 
//    private StaffView staffView;  
//    private ManagerView managerView; 
//    private AdminView adminView; 

//    public StaffController(BranchController branchList,StaffView staffView, ManagerView managerView, AdminView adminView) {
//		this.staffList = new ArrayList<Staff>();
//		this.branchList = branchList;
////		this.staffView = staffView;
////		this.managerView = managerView;
////		this.adminView = adminView;
//    }
    
    public StaffController() {
		this.staffList = new ArrayList<Staff>();
    }
    
    
    public Staff getStaffByID(String id) {
    	
    }
    
    public List<Staff> getStaffsByGender(char gender) {
    	
    }
    
    
    public List<Staff> getStaffsByRole(char role) {
    	
    }
    

    public List<Staff> getStaffsByAgeRange(int start, int end) {
    	
    }

    public List<Staff> getStaffsByGender(char gender) {
	
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
    
    public void updateStaff(String id, String name, char gender, char role, int age, String branchName) {
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
    
    public char validateLogin(id, password) {
    	
    }
    
    
}
