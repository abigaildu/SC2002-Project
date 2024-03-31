package console;

public class Manager extends Staff {
    // Manager-specific attributes could go here

    // Constructor
    public Manager(String id, String password, String branchName, char gender, int age) {
        super(id, password, branchName, gender, age, true); // Automatically set as a manager
    }

    // Manager-specific methods can be added here
}
