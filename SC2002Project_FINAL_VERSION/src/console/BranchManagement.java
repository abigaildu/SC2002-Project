package console;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class BranchManagement {
    private Map<String, Branch> allBranches;
    private final String filePath = "branches.txt";
    private StaffManagement staffManagement;
    private Map<String, Menu> branchMenus = new HashMap<>();

    

    public BranchManagement(StaffManagement staffManagement) {
    	this.staffManagement = staffManagement;
        this.allBranches = new HashMap<>();
        loadBranches();
    }
    
    public void loadBranches() {
    	File file = new File(filePath);	
    	if (!file.exists()) {
    	    System.out.println("No existing branch file found. A new one will be created.");
    	    try {
    	        if (file.createNewFile()) {
    	            System.out.println("New branch file created successfully.");
    	        } else {
    	            System.out.println("Failed to create new branch file.");
    	        }
    	    } catch (IOException e) {
    	        System.err.println("An error occurred while creating the new branch file: " + e.getMessage());
    	    }
    	    return;
    	}
    	
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        	allBranches.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) { // Check if parts.length matches the expected number including numberOfStaffs
                    String name = parts[0];
                    String daysOpen = parts[1];
                    String openingHours = parts[2];
                    String location = parts[3];
                    int numberOfStaffs = Integer.parseInt(parts[4]); // Parse numberOfStaffs from the string
                    Branch branch = new Branch(name, daysOpen, openingHours, location);
                    branch.setNumberOfStaffs(numberOfStaffs); // Set the number of staffs for the branch
//                    branch.setBranchStatus(isOpen);
                    
                    allBranches.put(name,branch);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Branch file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the branch file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("An error occurred while parsing the number of staffs: " + e.getMessage());
        }
    }
    
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (Branch branch : allBranches.values()) {
                // Save branch details without the number of staffs
                String line = String.join(",",
                        branch.getName(),
                        branch.getDaysOpen(),
                        branch.getOpeningHours(),
                        branch.getLocation(),
                        String.valueOf(branch.getNumberOfStaffs(staffManagement)));
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the branch file: " + e.getMessage());
        }
    }
    
    public void openBranch(Scanner scanner) {
        System.out.println("Please enter branch details....");
        System.out.print("Branch Name: ");
        String branchName = scanner.nextLine().toLowerCase(); //get branch name
        
        if(allBranches.containsKey(branchName)) { //if branch name already exists
        	System.out.println("Branch '" + branchName + "' already exists.");
        	return;
        }
        
        //else if it is a new branch entry (open a new branch)
        System.out.print("Days Open (e.g., Monday-Friday): "); //hard to error check
        String daysOpen = scanner.nextLine().toLowerCase();
        
        System.out.print("Opening Hours: "); //also hard to error check
        String openingHours = scanner.nextLine().toLowerCase();
        
        System.out.print("Location: ");
        String location = scanner.nextLine().toLowerCase();
        
        Branch branch = new Branch(branchName, daysOpen, openingHours, location);     
        allBranches.put(branchName, branch); // Optionally maintain the runtime list
        saveToFile(); //save to file
        System.out.println("Branch '" + branchName + "' opened successfully.");
    }

//    public void closeBranch(String branchName) {
//        Branch branchToRemove = null;
//        for (Branch branch : openBranches) {
//            if (branch.getName().equals(branchName)) {
//                branchToRemove = branch;
//                break;
//            }
//        }
//        if (branchToRemove != null) {
//            openBranches.remove(branchToRemove);
//            System.out.println("Branch '" + branchName + "' closed successfully.");
//        } else {
//            System.out.println("Branch '" + branchName + "' is not currently open.");
//        }
//        saveToFile();
//    }
    public void closeBranch(String branchName) {
    	Branch branchToRemove = allBranches.get(branchName);
    	if(branchToRemove != null) //if it exists and it is opened
    	{
//    		for(Staff staff: staffManagement.getStaffListForBranch(branchName)) {
//    			staffManagement.deleteStaff(staff.getid());
//    		}
    		for(Branch branch: allBranches.values()) {
    			if(branch.getName().equalsIgnoreCase(branchName.trim())) {
    				allBranches.remove(branch.getName(),branch);
    				break;
    			}
    		}
    		saveToFile();
    		return;
    		
    	}
		System.out.println("Branch '" + branchName + "' not found.");
    }

    
    public void displayOpenBranches() {
    	allBranches.values().forEach(branch-> System.out.println("Branch Name: " + branch.getName().trim() + ", Location: " + branch.getLocation().trim() + ", OpeningDays: " + branch.getDaysOpen().trim() + ", OpeningHours: " + branch.getOpeningHours().trim()));
    }
    
    public List<Branch> getOpenBranches() {
        //return new ArrayList<>(openBranches); // Return a copy to prevent external modifications
    	List<Branch> openBranches = new ArrayList<Branch>();
    	for(Branch branch: allBranches.values()) {
    		openBranches.add(branch);
    	}
    	return openBranches;
    }

    
    public void setStaffManagement(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }
    
//    public List<Branch> getOpenBranches() {
//        //return new ArrayList<>(openBranches); // Return a copy to prevent external modifications
//    	List<Branch> openBranches = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] branchDetails = line.split(",");
//                // Assuming the file structure is: id, name, isOpen (where isOpen is either "true" or "false")
//                if (branchDetails.length == 3 && branchDetails[2].trim().equalsIgnoreCase("true")) {
//                    Branch branch = new Branch(branchDetails[0].trim(), branchDetails[1].trim(), true);
//                    openBranches.add(branch);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return openBranches;
//    }
    
//    public Branch getBranchByName(String branchName) {
//        for (Branch branch : openBranches) {
//            if (branch.getName().equals(branchName)) {
//                return branch;
//            }
//        }
//        return null; // Or handle this case as you see fit.
//    }
    
//    public MenuBranch getMenuBranchByManagerId(String managerId) {
//        for (Branch branch : openBranches) {
//            if (branch instanceof MenuBranch) { //&& ((MenuBranch)branch).getManagerId().equals(managerId)
//                return (MenuBranch) branch;
//            }
//        }
//        return null; // Manager ID not found or not managing a specific branch
//    }
    
//    public Menu getOrCreateMenuForBranch(String branchName) {
//        // Try to find the branch by name
//        Branch branch = getBranchByName(branchName);
//        
//        // Check if the branch already exists and is a MenuBranch
//        if (branch instanceof MenuBranch) {
//            return ((MenuBranch) branch).getMenu();
//        } else {
//            // Branch exists but is not a MenuBranch, or branch doesn't exist
//            Menu newMenu = new Menu();
//            
//            if (branch == null) {
//                // Branch doesn't exist, so create a new MenuBranch
//                branch = new MenuBranch(branchName, "defaultDays", "defaultHours", "defaultLocation");
//                openBranches.add(branch); // Assuming this is the list of all branches
//            }
//            
//            // If the found branch is not a MenuBranch, it needs conversion or handling.
//            // However, simple conversion might not be straightforward due to potential data loss.
//            // Thus, creating a new MenuBranch might be the safer route, or rethinking the design to avoid such a scenario.
//            
//            // For now, let's assume we're setting the menu to the newly created one.
//            if(branch instanceof MenuBranch) {
//                ((MenuBranch) branch).setMenu(newMenu);
//            } else {
//                // If branch is not an instance of MenuBranch, handle this scenario.
//                // This might involve creating a new MenuBranch instance or reconsidering how branches are structured.
//                System.out.println("Existing branch found but it's not a MenuBranch. Needs handling.");
//            }
//            
//            return newMenu;
//        }
//    }
    
    public Menu getOrCreateMenuForBranch(String branchName) {
        // Path for the menu file, unique for each branch
        String menuFilePath = "menus/" + branchName.replace(" ", "_") + "_menu.txt"; 

        if (!branchMenus.containsKey(branchName)) {
            try {
                Files.createDirectories(Paths.get(menuFilePath).getParent()); // Ensure parent directory exists
            } catch (IOException e) {
                System.err.println("Failed to create directory for menu files: " + e.getMessage());
                return null;
            }

            Menu menu = new Menu(menuFilePath);
            menu.loadMenuFromFile(); // Load existing menu items from the file
            branchMenus.put(branchName, menu); // Store the menu in the map
        }
        return branchMenus.get(branchName);
    }



//    public Branch getBranchByName(String branchName) {
//        for (Branch branch : openBranches) {
//            if (branch.getName().equals(branchName)) {
//                return branch;
//            }
//        }
//        return null; // Or handle this case as you see fit.
//    }
    public Branch getBranchByName(String branchName) {
    	return allBranches.get(branchName);
    }

//    public void keepTrackofStaff(StaffManagement staffManagement){
//	    for(Branch branch: this.getOpenBranches()) {
//				int numberOfStaff = staffManagement.countStaffInBranch(branch.getName());
//				branch.setNumberOfStaffs(numberOfStaff);
//		}
//    }
//    private void appendBranchToFile(Branch branch) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true for append mode
//            writer.write(branch.getName() + "," + branch.getDaysOpen() + "," + branch.getOpeningHours() + "," + branch.getLocation());
//            writer.newLine();
//        } catch (IOException e) {
//            System.err.println("Failed to append the new branch: " + e.getMessage());
//        }
//    }

    



}