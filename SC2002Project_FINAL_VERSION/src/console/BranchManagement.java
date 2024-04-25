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
	/**
	* All branches.
	*/
    private Map<String, Branch> allBranches;
    /**
   	* The file path of text file storing branches.
   	*/
    private final String filePath = "branches.txt";
    /**
   	* The StaffManagement object.
   	*/
    private StaffManagement staffManagement;
    /**
	* The menu of this Branch.
	*/
    private Map<String, Menu> branchMenus = new HashMap<>();

    /**
     * Creating a new BranchManagement with the given information.
     * @param staffManagement StaffManagement object.
     */
    public BranchManagement(StaffManagement staffManagement) {
    	this.staffManagement = staffManagement;
        this.allBranches = new HashMap<>();
        loadBranches();
    }
    
    /**
     * Loading branches from text file.
     */
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
    
    /**
     * Saving branches to text file.
     */
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (Branch branch : allBranches.values()) {
                // Save branch details without the number of staffs
                String line = String.join(",",
                        branch.getName(),
                        branch.getDaysOpen(),
                        branch.getOpeningHours(),
                        branch.getLocation(),
                        String.valueOf(branch.getNumberOfStaffs()));
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the branch file: " + e.getMessage());
        }
    }
    
    /**
     * Opening a new Branch.
     * @param scanner Scanner object.
     */
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

    /**
     * Closing a Branch.
     * @param branchName The Branch's name.
     */
    public void closeBranch(String branchName) {
    	Branch branchToRemove = allBranches.get(branchName);
    	if(branchToRemove != null) //if it exists and it is opened
    	{
    		for(Branch branch: allBranches.values()) {
    			if(branch.getName().equalsIgnoreCase(branchName.trim())) {
    				allBranches.remove(branch.getName(),branch);
    				break;
    			}
    		}
    		saveToFile();
    		System.out.println("Branch '" + branchName + "' closed.");
    		return;
    		
    	}
		System.out.println("Branch '" + branchName + "' not found.");
    }

    /**
     * Displaying all opening branches.
     */
    public void displayOpenBranches() {
    	allBranches.values().forEach(branch-> System.out.println("Branch Name: " + branch.getName().trim() + ", Location: " + branch.getLocation().trim() + ", OpeningDays: " + branch.getDaysOpen().trim() + ", OpeningHours: " + branch.getOpeningHours().trim()));
    }
    
    /**
     * Getting all opening branches.
     * @return List of all opening branches.
     */
    public List<Branch> getOpenBranches() {
    	List<Branch> openBranches = new ArrayList<Branch>();
    	for(Branch branch: allBranches.values()) {
    		openBranches.add(branch);
    	}
    	return openBranches;
    }

    /**
     * Changing the StaffManagement of this BranchManagement.
     * @param staffManagement This BranchManagement's new StaffManagement.
     */
    public void setStaffManagement(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }
    
    /**
     * Getting or creating (if not exists) Menu for the Branch.
     * @param branchName Branch's name.
     * @return Menu.
     */
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

    /**
     * Getting the Branch by name.
     * @param branchName Branch's name.
     * @return Branch.
     */
    public Branch getBranchByName(String branchName) {
    	return allBranches.get(branchName);
    }
}