package console;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

/**
Representing a StaffFilteringList.
*/
public class StaffFilteringList {
	/**
	* The StaffManagement object.
	*/
    private final StaffManagement staffManagement;

    /**
     * Creating a new StaffFilteringList with the given information.
     * @param staffManagement StaffManagement object.
     */
    public StaffFilteringList(StaffManagement staffManagement) {
        this.staffManagement = staffManagement;
    }

    /**
     * Taking input from users to display staff list according to a particular condition.
     */
    public void filterStaff() {
        // Prompt the user to choose the filtering criteria
        System.out.println("Choose filtering criteria:");
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        System.out.print("Enter your choice: ");
        int choice = readIntInput();

        // Perform filtering based on the chosen criteria
        switch (choice) {
            case 1:
                filterByBranch();
                break;
            case 2:
                filterByRole();
                break;
            case 3:
                filterByGender();
                break;
            case 4:
                filterByAge();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Displaying staff list in a particular branch.
     */
    private void filterByBranch() {
        // Prompt the user to enter the branch name
        System.out.print("Enter branch name: ");
        String branchName = readStringInput();

        // Get the staff list for the specified branch
        List<Staff> filteredStaff = staffManagement.getStaffListForBranch(branchName);

        // Display the filtered staff list
        displayStaffList(filteredStaff);
    }

    /**
     * Displaying staff list by role.
     */
    private void filterByRole() {
        // Prompt the user to enter true or false
        System.out.print("Enter role (true for MANAGER, false for STAFF): ");
        boolean isManager = readBooleanInput();

        // Filter staff by role
        List<Staff> filteredStaff = staffManagement.getStaffListByRole(isManager);

        // Display the filtered staff list
        displayStaffList(filteredStaff);
    }

    /**
     * Displaying staff list by gender.
     */
    private void filterByGender() {
        // Prompt the user to enter the gender
        System.out.print("Enter gender (M/F): ");
        char gender = Character.toUpperCase(readCharInput()); // Convert to uppercase

        // Filter staff by gender
        List<Staff> filteredStaff = staffManagement.getStaffListByGender(gender);

        // If the entered gender is 'M' or 'F', also include 'm' and 'f' in the filter
        if (gender == 'M' || gender == 'F') {
            filteredStaff.addAll(staffManagement.getStaffListByGender(Character.toLowerCase(gender)));
        }

        // Display the filtered staff list
        displayStaffList(filteredStaff);
    }


    /**
     * Displaying staff list by age range.
     */
    private void filterByAge() {
        // Prompt the user to enter the age range
        System.out.print("Enter minimum age: ");
        int minAge = readIntInput();
        System.out.print("Enter maximum age: ");
        int maxAge = readIntInput();

        // Filter staff by age
        List<Staff> filteredStaff = staffManagement.getStaffListByAgeRange(minAge, maxAge);

        // Display the filtered staff list
        displayStaffList(filteredStaff);
    }

    /**
     * Displaying staff list.
     * @param staffList List of staff.
     */
    private void displayStaffList(List<Staff> staffList) {
        if (staffList.isEmpty()) {
            System.out.println("No staff found matching the criteria.");
        } else {
            System.out.println("Filtered Staff List:");
            for (Staff staff : staffList) {
                System.out.println("ID: " + staff.getId() + ", BranchName: " + staff.getBranchName() +
                        ", Age: " + staff.getAge() + ", Gender: " + staff.getGender() +
                        ", BranchManager: " + staff.isBranchManager());
            }
        }
    }

    // Helper methods for reading user input
    /**
     * Reading and validating integer input from users.
     * @return Integer input from users.
     */
    private int readIntInput() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
        return choice;
    }
    
    /**
     * Reading and validating boolean input from users.
     * @return Boolean input from users.
     */
    private boolean readBooleanInput() {
        // Implement logic to read boolean input from the user
        // You can use a Scanner to read user input
        // For simplicity, let's assume you are using Scanner:
        Scanner scanner = new Scanner(System.in);
        return scanner.nextBoolean();
    }

    /**
     * Reading and validating character input from users.
     * @return Character input from users.
     */
    private char readCharInput() {
        Scanner scanner = new Scanner(System.in);
        char input;
        while (true) {
            String line = scanner.nextLine();
            if (line.length() == 1) {
                input = line.charAt(0);
                break;
            } else {
                System.out.print("Invalid input. Please enter a single character: ");
            }
        }
        return input;
    }

    /**
     * Reading and validating String input from users.
     * @return String input from users.
     */
    private String readStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
