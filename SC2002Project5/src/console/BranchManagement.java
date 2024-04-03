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
    private List<Branch> openBranches;
    private final String filePath = "branches.txt";
    private StaffManagement staffManagement;
    private Map<String, Menu> branchMenus = new HashMap<>();

    

    public BranchManagement(StaffManagement staffManagement) {
    	this.staffManagement = staffManagement;
        this.openBranches = new ArrayList<>();
        loadBranches();
    }
    
    private void loadBranches() {
    	File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No existing branch file found. A new one will be created.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Check if parts.length matches the expected number including numberOfStaffs
                    String name = parts[0];
                    String daysOpen = parts[1];
                    String openingHours = parts[2];
                    String location = parts[3];
                    int numberOfStaffs = Integer.parseInt(parts[4]); // Parse numberOfStaffs from the string
                    Branch branch = new Branch(name, daysOpen, openingHours, location);
                    branch.setNumberOfStaffs(numberOfStaffs); // Set the number of staffs for the branch
                    openBranches.add(branch);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Branch file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the branch file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("An error occurred while parsing the number of staffs: " + e.getMessage());
        }
    }
    
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (Branch branch : openBranches) {
                // Save branch details without the number of staffs
                String line = String.join(",",
                        branch.getName(),
                        branch.getDaysOpen(),
                        branch.getOpeningHours(),
                        branch.getLocation());
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the branch file: " + e.getMessage());
        }
    }
    
    public void openBranch(Scanner scanner) {
        System.out.println("Please enter branch details....");
        System.out.print("Branch Name: ");
        String branchName = scanner.nextLine();
        System.out.print("Days Open (e.g., Monday-Friday): ");
        String daysOpen = scanner.nextLine();
        System.out.print("Opening Hours: ");
        String openingHours = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Initial Number of Staffs: ");
        int numberOfStaffs = scanner.nextInt();
        scanner.nextLine();
        
        Branch branch = new Branch(branchName, daysOpen, openingHours, location);
        branch.setNumberOfStaffs(numberOfStaffs);
//        if (!openBranches.contains(branch)) {
//            openBranches.add(branch);
//            
//            System.out.println("Branch '" + branchName + "' opened successfully.");
//        } else {
//            System.out.println("Branch '" + branchName + "' is already open.");
//        }
//        saveToFile();
        if (!openBranches.contains(branchName)) { // Implement branchExists to check the file
            appendBranchToFile(branch); // Append new branch directly to the file
            openBranches.add(branch); // Optionally maintain the runtime list
            System.out.println("Branch '" + branchName + "' opened successfully.");
        } else {
            System.out.println("Branch '" + branchName + "' already exists.");
        }
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
        List<Branch> remainingBranches = new ArrayList<>();
        boolean found = false;

        // Read all branches and filter out the one to close
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && !parts[0].trim().equals(branchName)) {
                    remainingBranches.add(new Branch(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return;
        }

        // Overwrite the file with the remaining branches
        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
                for (Branch branch : remainingBranches) {
                    out.println(branch.getName() + "," + branch.getDaysOpen() + "," + branch.getOpeningHours() + "," + branch.getLocation());
                }
                System.out.println("Branch '" + branchName + "' closed successfully.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Branch '" + branchName + "' not found.");
        }
    }


//    public void displayOpenBranches() {
//        System.out.println("Open Branches:");
//        if (openBranches.isEmpty()) {
//            System.out.println("No branches are currently open.");
//        } else {
//            for (Branch branch : openBranches) {
//                int numberOfStaffs = staffManagement.countStaffInBranch(branch.getName());
//                System.out.println("Branch Name: " + branch.getName() + ", Staff Count: " + numberOfStaffs);
//            }
//        }
//    }
    
    public void displayOpenBranches() {
        boolean hasBranches = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                hasBranches = true;
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    System.out.println("Branch Name: " + parts[0].trim() + ", Location: " + parts[3].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        if (!hasBranches) {
            System.out.println("No branches are currently open.");
        }
    }

    
    public void setStaffManagement(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }
    
    public List<Branch> getOpenBranches() {
        return new ArrayList<>(openBranches); // Return a copy to prevent external modifications
    }
    
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
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].trim().equals(branchName)) {
                    // Assuming the constructor of Branch is Branch(name, daysOpen, openingHours, location)
                    return new Branch(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null; // Branch not found or error occurred
    }

    private void appendBranchToFile(Branch branch) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true for append mode
            writer.write(branch.getName() + "," + branch.getDaysOpen() + "," + branch.getOpeningHours() + "," + branch.getLocation());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to append the new branch: " + e.getMessage());
        }
    }

    



}