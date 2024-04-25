package console;

import java.io.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
Representing a StaffManagement.
*/
public class StaffManagement {
	/**
	* The staff accounts.
	*/
    private Map<String, Staff> staffAccounts = new HashMap<>();	
    /**
	* The file path of text file storing staff accounts.
	*/
    private final String filePath = "staff_accounts.txt";
    /**
	* The BranchManagement object.
	*/
    private BranchManagement branchManagement;
    /**
	* The staff branch.
	*/
    private String staffBranch;
    
    /**
     * Creating a new StaffManagement with the given information.
     * @param branchManagement BranchManagement object.
     */
    public StaffManagement(BranchManagement branchManagement) {
        this.branchManagement = branchManagement;
        loadStaffAccounts();
    }

    /**
     * Loading staff accounts from text file.
     */
    private void loadStaffAccounts() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No existing staff accounts file found. A new one will be created.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Ensure there are enough parts to parse
                    String id = parts[0];
                    String password = parts[1];
                    String branchName = parts[2];
                    char gender = parts[3].charAt(0);
                    int age = Integer.parseInt(parts[4]);
                    boolean branchManager = Boolean.parseBoolean(parts[5]);
                    
                    Staff staff = new Staff(id, password, branchName, gender, age, branchManager);
                    staffAccounts.put(id, staff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Staff accounts file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the staff accounts file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("An error occurred while parsing the age of a staff member: " + e.getMessage());
        }
    }

    /**
     * Registering new staff.
     * @param id New staff's id.
     * @param password New staff's password.
     * @param branchName New staff's branch name.
     * @param gender New staff's gender.
     * @param age New staff's age.
     * @param isBranchManager New staff's possibility to be a branch manager.
     * @return State to indicate whether registering new staff is successful.
     */
    public boolean registerStaff(String id, String password, String branchName, char gender, int age, boolean isBranchManager) {
        if (staffAccounts.containsKey(id)) {
            System.out.println("Staff ID already exists.");
            return false;
        }
        Staff newStaff = new Staff(id, password, branchName, gender, age, isBranchManager);
        staffAccounts.put(id, newStaff);
        saveToFile();
        branchManagement.getBranchByName(branchName).incrementStaffCount();
        branchManagement.saveToFile();
        return true;
    }

    /**
     * Updating staff information.
     * @param id Staff's id.
     * @param branchName Staff's new branch name.
     * @param gender Staff's new gender.
     * @param age Staff's new age.
     * @param isBranchManager Staff's new possibility to be a branch manager.
     * @return State to indicate whether updating staff information is successful.
     */
    public boolean updateStaffDetails(String id, String branchName, char gender, int age, boolean isBranchManager) {
    	Staff staff = staffAccounts.get(id);
        if (staff == null) {
            System.out.println("Staff ID not found.");
            return false;
        }
        String oldBranchName = staff.getBranchName();
        staff.setBranchName(branchName);
        branchManagement.getBranchByName(oldBranchName).decrementStaffCount();
        branchManagement.getBranchByName(branchName).incrementStaffCount();
        
        staff.setGender(gender);
        staff.setAge(age);
        staff.setBranchManager(isBranchManager);
        saveToFile();
        branchManagement.saveToFile();
        return true;
    }

    /**
     * Deleting staff.
     * @param id Staff's id.
     * @return State to indicate whether deleting staff is successful.
     */
    public boolean deleteStaff(String id) {
        if (!staffAccounts.containsKey(id)) {
            System.out.println("Staff ID not found.");
            return false;
        }
        String oldBranchName = staffAccounts.get(id).getBranchName();
        branchManagement.getBranchByName(oldBranchName).decrementStaffCount();
        staffAccounts.remove(id);
        saveToFile();
        System.out.println("Staff " + id + " removed successfully.");
        branchManagement.saveToFile();
        return true;
    }

    /**
     * Displaying staff list.
     */
    public void displayStaffList() {
        if (staffAccounts.isEmpty()) {
            System.out.println("No staff accounts available.");
        } else {
            System.out.println("Staff List:");
            staffAccounts.forEach((id,staff) -> System.out.println("ID: " + id +  ", BranchName: " + staff.getBranchName() + ", Age: " + staff.getAge() + ", Gender: " + staff.getGender() + ", BranchManager: " + staff.isBranchManager()));
        }
    }

    /**
     * Validating staff account when logging in.
     * @param id Staff's id.
     * @param password Staff's password.
     * @return Role of the staff.
     */
    public String validateStaffLogin(String id, String password) {
    	// Admin login shortcut for demonstration purposes
    	loadStaffAccounts();
    	if ("admin".equalsIgnoreCase(id) && "admin".equals(password)) {
    		return "ADMIN";
    	}
    	
    	// Example lookup in a hypothetical staff list/database
    	Staff staff = staffAccounts.get(id);
    	if (staff != null && staff.getPassword().equals(password)) {
    		// Assuming StaffMember class has a getRole() method
    		staffBranch = staff.getBranchName();
    		if(staff.isBranchManager() == true) {
    			return "MANAGER"; // Returns "MANAGER" or "STAFF"
    		} else {
    			return "STAFF";
	        }
    	}
    	// Login failed
    	return null;
    }
	  
    /**
     * Getting the staff branch.
     * @return Staff branch.
     */
    public String getStaffBranch() {
    	return staffBranch;
    }

    /**
     * Getting Staff by Id.
     * @param id Staff's id.
     * @return Staff.
     */
    public Staff getStaff(String id) {
    	loadStaffAccounts();
        return staffAccounts.get(id);
    }

    /**
     * Saving staff accounts to text file.
     */
    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Staff> entry : staffAccounts.entrySet()) {
                Staff staff = entry.getValue();
                // Assuming you're storing passwords externally, you might need to adjust how you handle them.
                String line = entry.getKey() + "," + staff.getPassword() + "," + staff.getBranchName() + "," + staff.getGender() + "," + staff.getAge() + "," + staff.isBranchManager();
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving staff accounts: " + e.getMessage());
        }
    }


    /**
     * Promoting a staff to manager.
     * @param id Staff's id.
     * @return State to indicate whether promoting staff to manager is successful.
     */
    public boolean promoteToManager(String id) {
    	Staff staff = getStaff(id);
    	if (staff != null && !staff.isBranchManager()) {
    		String branchName = staff.getBranchName();
    		int currentexistingManagers = countCurrentManagersInBranch(branchName);
    		int totalstaffCount = countStaffsInBranch(branchName);
    		int requiredManagersperbranch = calculateRequiredManagers(totalstaffCount);
           
    		if (currentexistingManagers >= requiredManagersperbranch) {
    			System.out.println("Cannot promote " + id + " to manager. Manager capacity for branch " + branchName + " reached.");
    			return false;
    		}
    		// Directly update the isBranchManager flag
    		staff.setBranchManager(true);

    		// Save the updated staff information to the file
    		saveToFile();
                
    		return true;
    	} else {
    		System.out.println("Failed to promote staff to manager.");
    		return false;
    	}
            
    }

    /**
     * Demoting a manager to staff.
     * @param id Staff's id.
     * @return State to indicate whether demoting staff to manager is successful.
     */
    public boolean demoteToStaff(String id) {
        Staff staff = getStaff(id);
        if (staff != null && staff.isBranchManager()) {
            // Directly update the isBranchManager flag without checking for instanceof Manager
            staff.setBranchManager(false);
            
            // Save the updated staff information to the file
            saveToFile();
            return true;
        }
        return false;
    }

    /**
     * Transferring staff to a different branch.
     * @param id Staff's id.
     * @param newBranch Staff's new branch.
     * @return State to indicate whether transferring staff to a different branch is successful.
     */
    public boolean transferStaff(String id, String newBranch) {
        Staff staff = getStaff(id);
        if (staff != null) {
        	String oldBranchName = staff.getBranchName();
            staff.setBranchName(newBranch);
            branchManagement.getBranchByName(oldBranchName).decrementStaffCount();
            branchManagement.getBranchByName(newBranch).incrementStaffCount();
            saveToFile(); // Save changes
            branchManagement.saveToFile();
            return true;
        }
        return false;
    }
    
    /**
     * Counting the number of staffs in a branch.
     * @param branchName Branch name.
     * @return The number of staffs in the branch.
     */
    public int countStaffsInBranch(String branchName) {
        return (int) staffAccounts.values().stream()
                .filter(staff -> staff.getBranchName().equals(branchName))
                .count();
    }
    
    /**
     * Assigning managers automatically.
     */
    public void assignManagersAutomatically() {
        for (Branch branch : branchManagement.getOpenBranches()) {
            String branchName = branch.getName();
            int staffCount = countStaffsInBranch(branchName);
            int requiredManagers = calculateRequiredManagers(staffCount);
            int currentManagers = countCurrentManagersInBranch(branchName);

            adjustManagersForBranch(branchName, requiredManagers - currentManagers);
        }
    }
    
    /**
     * Calculating the number of managers required in a branch.
     * @param staffCount The number of staffs in the branch.
     * @return The number of managers required in the branch.
     */
    private int calculateRequiredManagers(int staffCount) {
        if (staffCount <= 4) return 1;
        else if (staffCount <= 8) return 2;
        else return 3; // Assuming 9-15 staff members require 3 managers
    }
    
    /**
     * Counting the number of managers in a branch.
     * @param branchName Branch name.
     * @return The number of managers in the branch.
     */
    private int countCurrentManagersInBranch(String branchName) {
        // Iterate through staffAccounts, count how many managers are in the specified branch
        int managerCount = 0;
        for (Staff staff : staffAccounts.values()) {
            if (staff.getBranchName().equals(branchName) && staff.isBranchManager()) {
                managerCount++;
            }
        }
        return managerCount;
    }

    /**
     * Adjusting the number of managers required in a branch.
     * @param branchName Branch name.
     * @param managersNeeded The number of managers need to be added/removed in the branch.
     */
    private void adjustManagersForBranch(String branchName, int managersNeeded) {
        if (managersNeeded > 0) {
            promoteStaffToManagers(branchName, managersNeeded);
        } else if (managersNeeded < 0) {
            demoteManagersToStaff(branchName, -managersNeeded);
        }
    }
    
    /**
     * Promoting staff to managers.
     * @param branchName Branch name.
     * @param numberToPromote The number of staff need to be promoted.
     */
    private void promoteStaffToManagers(String branchName, int numberToPromote) {
        for (Staff staff : staffAccounts.values()) {
            if (!staff.isBranchManager() && staff.getBranchName().equals(branchName) && numberToPromote > 0) {
                staff.setBranchManager(true);
                numberToPromote--;
                System.out.println("Promoted " + staff.getId() + " to manager in branch: " + branchName);
            }
            if (numberToPromote == 0) break;
        }
        saveToFile(); // Assuming this method saves the current state to file
    }

    /**
     * Demoting managers to staff.
     * @param branchName Branch name.
     * @param numberToDemote The number of managers need to be demoted.
     */
    private void demoteManagersToStaff(String branchName, int numberToDemote) {
        for (Staff staff : staffAccounts.values()) {
            if (staff.isBranchManager() && staff.getBranchName().equals(branchName) && numberToDemote > 0) {
                staff.setBranchManager(false);
                numberToDemote--;
                System.out.println("Demoted " + staff.getId() + " to staff in branch: " + branchName);
            }
            if (numberToDemote == 0) break;
        }
        saveToFile(); // Assuming this method saves the current state to file
    }
    
    
 // Method for a staff member to view orders
    /**
     * Printing new orders.
     * @param staffId Staff's id.
     * @param orderStatus OrderStatus object.
     */
    public void viewOrders(String staffId, OrderStatus orderStatus) {
        Staff staff = staffAccounts.get(staffId);
        if (staff != null) {
            System.out.println("Viewing all orders by staff: " + staffId);
            //orderStatus.displayOrderStatus(); // Display all orders
            orderStatus.displayNewOrders();
        } else {
            System.out.println("Staff ID not found.");
        }
    }

    /**
     * Changing order status.
     * @param staffId Staff's id.
     * @param orderStatus OrderStatus object.
     * @param orderID Order id.
     * @param newStatus New order status.
     */
    // Method for changing the status of an order, possibly with permission checks for managers
    public void changeOrderStatus(String staffId, OrderStatus orderStatus, int orderID, OrderItem.Status newStatus) {
        Staff staff = staffAccounts.get(staffId);
        if (staff == null) {
            System.out.println("Staff ID not found.");
            return;
        }

        // Example of a permission check before allowing the status change
        if (staff instanceof Manager || (staff.isBranchManager())) {
            //boolean success = orderStatus.markOrderAsReadyToCollect(orderID);
        	orderStatus.markOrderAsReadyToCollect(orderID);
            //if (success) {
                System.out.println("Order status updated for Order ID: " + orderID + " to " + newStatus + " by " + staffId);
            //} else {
            //    System.out.println("Failed to update order status for Order ID: " + orderID);
            //}
        } else {
            System.out.println("Insufficient permissions to change order status.");
        }
    }
    
    /**
     * Changing staff password.
     * @param id Staff's id.
     * @param newPassword Staff's new password.
     */
    public void updateStaffPassword(String id, String newPassword) {
        Staff staff = staffAccounts.get(id);
        if (staff != null) {
            staff.setPassword(newPassword);
            saveToFile(); // Assuming this method saves the updated staff list to a file
            System.out.println("Password updated for staff ID: " + id);
        } else {
            System.out.println("Staff ID not found.");
        }
    }
    
    /**
     * Getting staff list in a particular branch.
     * @param branchName Branch name.
     * @return Staff list in the branch.
     */
    public List<Staff> getStaffListForBranch(String branchName) {
        List<Staff> staffListForBranch = new ArrayList<>();
        for (Staff staff : staffAccounts.values()) {
            if (staff.getBranchName().equals(branchName)) {
                staffListForBranch.add(staff);
            }
        }
        return staffListForBranch;
    }
    
    /**
     * Getting staff list by role.
     * @param isManager Possibility of being a branch manager of a staff.
     * @return Staff list by role.
     */
    public List<Staff> getStaffListByRole(boolean isManager) {
        List<Staff> staffListByRole = new ArrayList<>();
        for (Staff staff : staffAccounts.values()) {
            if (staff.isBranchManager() == isManager) {
                staffListByRole.add(staff);
            }
        }
        return staffListByRole;
    }
    
    /**
     * Getting staff list by gender.
     * @param gender Gender.
     * @return Staff list by gender.
     */
    public List<Staff> getStaffListByGender(char gender) {
        List<Staff> staffListByGender = new ArrayList<>();
        for (Staff staff : staffAccounts.values()) {
            if (staff.getGender() == gender) {
                staffListByGender.add(staff);
            }
        }
        return staffListByGender;
    }
    
    /**
     * Getting staff list by age range.
     * @param minAge Minimum age of the range.
     * @param maxAge Maximum age of the range.
     * @return Staff list by the range of the age.
     */
    public List<Staff> getStaffListByAgeRange(int minAge, int maxAge) {
        List<Staff> staffListByAgeRange = new ArrayList<>();
        for (Staff staff : staffAccounts.values()) {
            int age = staff.getAge();
            if (age >= minAge && age <= maxAge) {
                staffListByAgeRange.add(staff);
            }
        }
        return staffListByAgeRange;
    }  
}