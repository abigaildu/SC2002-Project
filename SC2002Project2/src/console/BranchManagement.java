package console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BranchManagement {
    private List<Branch> openBranches;
    private final String filePath = "branches.txt";
    private StaffManagement staffManagement;

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
        if (!openBranches.contains(branch)) {
            openBranches.add(branch);
            
            System.out.println("Branch '" + branchName + "' opened successfully.");
        } else {
            System.out.println("Branch '" + branchName + "' is already open.");
        }
        saveToFile();
    }

    public void closeBranch(String branchName) {
        Branch branchToRemove = null;
        for (Branch branch : openBranches) {
            if (branch.getName().equals(branchName)) {
                branchToRemove = branch;
                break;
            }
        }
        if (branchToRemove != null) {
            openBranches.remove(branchToRemove);
            System.out.println("Branch '" + branchName + "' closed successfully.");
        } else {
            System.out.println("Branch '" + branchName + "' is not currently open.");
        }
        saveToFile();
    }

    public void displayOpenBranches() {
        System.out.println("Open Branches:");
        if (openBranches.isEmpty()) {
            System.out.println("No branches are currently open.");
        } else {
            for (Branch branch : openBranches) {
                int numberOfStaffs = staffManagement.countStaffInBranch(branch.getName());
                System.out.println("Branch Name: " + branch.getName() + ", Staff Count: " + numberOfStaffs);
            }
        }
    }
    
    public void setStaffManagement(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }
    
    public List<Branch> getOpenBranches() {
        return new ArrayList<>(openBranches); // Return a copy to prevent external modifications
    }
}