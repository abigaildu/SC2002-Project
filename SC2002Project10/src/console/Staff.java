package console;

public class Staff {
	private String id;
	private String password;
    private Branch branch;
    private char gender;
    private int age;
    private boolean isBranchManager;
    
    // Constructor
    public Staff(String id, String password, Branch branch, char gender, int age, boolean isBranchManager) {
        this.id = id;
        this.password = password;
        this.branch = branch;
        this.gender = gender;
        this.age = age;
        this.isBranchManager = isBranchManager;
    }

    // Getters and setters
    
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    
    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public Branch getBranch() {
        return branch;
    }

    public void changeBranch(Branch newBranch) {
        this.branch = newBranch;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public boolean getIsBranchManager() {
        return isBranchManager;
    }

    public void setBranchManager(boolean isBranchManager) {
        this.isBranchManager = isBranchManager;
    }
//    need to complete
    public void displayNewOrders() {
    	branch.
    }
    
    public void viewOrderById(String id) {
    	
    }
    
    public void selectOrderToProcess() {
    	
    }
    
    public void updateCompletedOrder() {
    	
    }


    // Override toString method for displaying staff details
    //@Override
//    public String toString() {
//        return "Branch Name: " + branchName + ", Gender: " + gender + ", Age: " + age;
//    }
}