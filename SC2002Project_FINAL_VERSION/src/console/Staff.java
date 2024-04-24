package console;

/**
Representing a staff.
*/
public class Staff {
	/**
	* The id of this staff.
	*/
	private String id;
	/**
	* The password of this staff.
	*/
	private String password;
	/**
	* The branch of this staff.
	*/
    private String branchName;
    /**
	* The gender of this staff.
	*/
    private char gender;
    /**
	* The age of this staff.
	*/
    private int age;
    /**
	* The possibility of being a branch manager of this staff.
	*/
    private boolean isBranchManager;
    
    /**
     * Creating a new Staff with the given information.
     * @param id This Student's id.
     * @param password This Student's password.
     * @param branchName This Student's branch.
     * @param gender This Student's gender.
     * @param age This Student's age.
     * @param branchManager This Student's possibility of being a branch manager.
     */
    public Staff(String id, String password, String branchName, char gender, int age, boolean isBranchManager) {
        this.id = id;
        this.password = password;
        this.branchName = branchName;
        this.gender = gender;
        this.age = age;
        this.isBranchManager = isBranchManager;
    }

    /**
     * Getting id of this Staff.
     * @return this Staff's id.
     */
    public String getid() {
        return id;
    }

    /**
     * Changing the id of this Staff.
     * This may involve a lengthy legal process.
     * @param id This Staff's new id.
     */
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