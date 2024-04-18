package console;

import java.util.Scanner;

public class AdminPage {
	private MainFrame main;
    private StaffManagement staffManagement;
    private BranchManagement branchManagement;
    private PaymentManagement paymentManagement;
    private Scanner scanner;

    public AdminPage(StaffManagement staffManagement, MainFrame m, BranchManagement branchManagement) {
    	this.main = m;
        this.staffManagement = staffManagement;
        this.branchManagement = branchManagement;
        this.paymentManagement = new PaymentManagement();
        this.scanner = new Scanner(System.in);
    }

    public void showAdminOptions() {
        boolean running = true;
        while (running) {
        	System.out.println("\nAdmin Options:");
            System.out.println("1. Add Staff");
            System.out.println("2. Edit Staff");
            System.out.println("3. Remove Staff");
            System.out.println("4. Display Staff List");
            System.out.println("5. Promote Staff to Manager");
            System.out.println("6. Demote Manager to Staff");
            System.out.println("7. Automatically assign Managers to Branches");
            System.out.println("8. Transfer Staff");
            System.out.println("9. Open a Branch");
            System.out.println("10. Close a Branch");
            System.out.println("11. Display Open Branches");
            System.out.println("12. Add Payment Method");
            System.out.println("13. Remove Payment Method");
            System.out.println("14. Display Payment Methods");
            System.out.println("15. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
            case 1:
                addStaff();
                break;
            case 2:
                editStaff();
                break;
            case 3:
                removeStaff();
                break;
            case 4:
                displayStaffList();
                break;
            case 5:
                promoteToManager();
                break;
            case 6:
                demoteToStaff();
                break;
            case 7:
                staffManagement.assignManagersAutomatically();
                System.out.println("Managers have been assigned based on current branch staff counts.");
                break;
            case 8:
                transferStaff();
                break;
            case 9:
            	branchManagement.openBranch(scanner);
                break;
            case 10:
            	System.out.print("Enter Branch Name to close: ");
                String branchName = scanner.nextLine();
                branchManagement.closeBranch(branchName);
                break;
            case 11:
            	branchManagement.displayOpenBranches();
                break;
            case 12:
                System.out.print("Enter Payment Method to add: ");
                String addMethod = scanner.nextLine();
                paymentManagement.addPaymentMethod(addMethod);
                break;
            case 13:
                System.out.print("Enter Payment Method to remove: ");
                String removeMethod = scanner.nextLine();
                paymentManagement.removePaymentMethod(removeMethod);
                break;
            case 14:
                paymentManagement.displayPaymentMethods();
                break;
            case 15:
                running = false;
                this.main.run();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
            }
        }
    }

    private void addStaff() {
        System.out.print("Enter Staff ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Branch Name: ");
        String branchName = scanner.nextLine();
        System.out.print("Enter Gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Is Branch Manager? (true/false): ");
        boolean isBranchManager = scanner.nextBoolean();

        boolean success = staffManagement.registerStaff(id, password, branchName, gender, age, isBranchManager);
        if (success) {
            System.out.println("Staff added successfully.");
        } else {
            System.out.println("Failed to add staff.");
        }
    }


    private void editStaff() {
        // Assume you're only editing the password for simplicity
    	 System.out.print("Enter Staff ID to edit: ");
    	    String id = scanner.nextLine();
    	    
    	    // Fetch staff details and display them
    	    Staff staff = staffManagement.getStaff(id);
    	    if (staff == null) {
    	        System.out.println("Staff not found.");
    	        return;
    	    }
    	    
    	    System.out.println("Editing Staff: " + id);
    	    System.out.print("Enter new Branch Name (current: " + staff.getBranchName() + "): ");
    	    String branchName = scanner.nextLine();
    	    System.out.print("Enter new Gender (M/F) (current: " + staff.getGender() + "): ");
    	    char gender = scanner.nextLine().charAt(0);
    	    System.out.print("Enter new Age (current: " + staff.getAge() + "): ");
    	    int age = scanner.nextInt();
    	    scanner.nextLine(); // Consume newline
    	    System.out.print("Is Branch Manager? (true/false) (current: " + staff.isBranchManager() + "): ");
    	    boolean isBranchManager = scanner.nextBoolean();
    	    scanner.nextLine(); // Consume newline

    	    // Update staff details
    	    boolean success = staffManagement.updateStaffDetails(id, branchName, gender, age, isBranchManager);
    	    if (success) {
    	        System.out.println("Staff details updated successfully.");
    	    } else {
    	        System.out.println("Failed to update staff details.");
    	    }
    }

    private void removeStaff() {
        System.out.print("Enter Staff ID to remove: ");
        String id = scanner.nextLine();
        boolean success = staffManagement.deleteStaff(id);
        if (success) {
            System.out.println("Staff removed successfully.");
        } else {
            System.out.println("Failed to remove staff.");
        }
    }

    private void displayStaffList() {
    	
        staffManagement.displayStaffList();
    }
    
    private void promoteToManager() {
        System.out.print("Enter Staff ID to promote to manager: ");
        String id = scanner.nextLine();
        if (staffManagement.promoteToManager(id)) {
            System.out.println("Staff promoted to manager successfully.");
        } else {
            System.out.println("Failed to promote staff to manager.");
        }
    }
    
    private void demoteToStaff() {
    	System.out.print("Enter Manager ID to demote: ");
        String demoteId = scanner.nextLine();
        if (staffManagement.demoteToStaff(demoteId)) {
            System.out.println("Manager demoted to staff successfully.");
        } else {
            System.out.println("Failed to demote manager.");
        }
    }
    
    private void transferStaff() {
        System.out.print("Enter Staff ID to transfer: ");
        String id = scanner.nextLine();
        System.out.print("Enter new Branch Name: ");
        String newBranch = scanner.nextLine();
        if (staffManagement.transferStaff(id, newBranch)) {
            System.out.println("Staff transferred successfully.");
        } else {
            System.out.println("Failed to transfer staff.");
        }
    }
}