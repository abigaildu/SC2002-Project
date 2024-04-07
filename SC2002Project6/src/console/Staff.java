package console;

public class Staff {
	private String id;
	private String password;
    private String branchName;
    private char gender;
    private int age;
    private boolean branchManager;
    // Constructor
    public Staff(String id, String password, String branchName, char gender, int age, boolean branchManager) {
        this.id = id;
        this.password = password;
        this.branchName = branchName;
        this.gender = gender;
        this.age = age;
        this.branchManager = branchManager;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
    public boolean isBranchManager() {
        return branchManager;
    }

    public void setBranchManager(boolean branchManager) {
        this.branchManager = branchManager;
    }

    // Override toString method for displaying staff details
    //@Override
//    public String toString() {
//        return "Branch Name: " + branchName + ", Gender: " + gender + ", Age: " + age;
//    }
}
