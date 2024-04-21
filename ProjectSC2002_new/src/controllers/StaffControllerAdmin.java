package controllers;

import models.Staff;

public class StaffControllerAdmin extends StaffController {
	public StaffControllerAdmin() {
		super();
	}
	
	public boolean addStaff(Staff staffToAdd) {
		Staff staff = super.getStaffById(staffToAdd.getId());
		if(staff != null) {
			return false; //staff has already existed in the list
		}
		
		super.staffList.add(staffToAdd);
		DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
		return true; //staff added successfully
	}
	
	public boolean updateStaff(String staffIdToUpdate, String newPassword, String newName, String newGender, String newRole, int newAge, String newBranchName) {
		Staff staff = super.getStaffById(staffIdToUpdate);
		if(staff != null) {
			staff.setPassword(newPassword);
			staff.setName(newName);
			staff.setGender(newGender);
			staff.setRole(newRole);
			staff.setAge(newAge);
			staff.setBranchName(newBranchName);
			DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
			return true; //staff details updated successfully			
		}
		return false; //staff not found
	}
	
	public boolean deleteStaff(String id) {
		Staff staff = super.getStaffById(id);
		if(staff != null) {	
			super.staffList.remove(staff);
			DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
			return true; //staff removed successfully
		}
		return false; //staff not found
	}
	
	public boolean transferStaff (String id, String newBranchName) {
    	Staff staff = super.getStaffById(id);
		if(staff != null) {	
			staff.setBranchName(newBranchName);
			DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
			return true; //staff transfered successfully
		}
		return false; //staff not found
    }

    public boolean promoteToManager(String id) {
//    	check current role
//    	check the number of manager and staff in branch
//    	update the number of staff in branch
    	Staff staff = super.getStaffById(id);
		if(staff != null) {	
			staff.setRole("M");
			DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
			return true; //staff promoted successfully
		}
		return false; //staff not found
    	
    }
    
    public boolean demoteToStaff(String id) {
//    	check current role
//    	check the number of manager and staff in branch
//    	update the number of staff in branch
    	Staff staff = super.getStaffById(id);
		if(staff != null) {	
			staff.setRole("S");
			DataController.writeStaffAccountsToFile(super.staffList, super.fileName);
			return true; //staff demoted successfully
		}
		return false; //staff not found
    }
}
