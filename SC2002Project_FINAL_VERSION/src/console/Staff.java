package console;

/**
Representing a Staff.
*/
public class Staff {
	/**
	* The id of this Staff.
	*/
	private String id;
	/**
	* The password of this Staff.
	*/
	private String password;
	/**
	* The branch name of this Staff.
	*/
    private String branchName;
    /**
	* The gender of this Staff.
	*/
    private char gender;
    /**
	* The age of this Staff.
	*/
    private int age;
    /**
	* The possibility of being a branch manager of this Staff.
	*/
    private boolean isBranchManager;
    
    /**
     * Creating a new Staff with the given information.
     * @param id This Staff's id.
     * @param password This Staff's password.
     * @param branchName This Staff's branch name.
     * @param gender This Staff's gender.
     * @param age This Staff's age.
     * @param branchManager This Staff's possibility of being a branch manager.
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
     * Getting the id of this Staff.
     * @return This Staff's id.
     */
    public String getId() {
		return id;
	}

    /**
     * Changing the id of this Staff.
     * @param id This Staff's new id.
     */
	public void setId(String id) {
		this.id = id;
	}

	/**
     * Getting the password of this Staff.
     * @return This Staff's password.
     */
	public String getPassword() {
		return password;
	}

	/**
     * Changing the password of this Staff.
     * @param password This Staff's new password.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Getting the branch name of this Staff.
     * @return This Staff's branch name.
     */
	public String getBranchName() {
		return branchName;
	}

	/**
     * Changing the branch name of this Staff.
     * @param branchName This Staff's new branch name.
     */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
     * Getting the gender of this Staff.
     * @return This Staff's gender.
     */
	public char getGender() {
		return gender;
	}

	/**
     * Changing the gender of this Staff.
     * @param gender This Staff's new gender.
     */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
     * Getting the age of this Staff.
     * @return This Staff's age.
     */
	public int getAge() {
		return age;
	}

	/**
     * Changing the age of this Staff.
     * @param age This Staff's new age.
     */
	public void setAge(int age) {
		this.age = age;
	}

	/**
     * Getting the possibility of being a branch manager of this Staff.
     * @return This Staff's possibility of being a branch manager.
     */
	public boolean isBranchManager() {
		return isBranchManager;
	}
	
	/**
     * Changing the possibility of being a branch manager of this Staff.
     * @param isBranchManager This Staff's new possibility of being a branch manager.
     */
	public void setBranchManager(boolean isBranchManager) {
		this.isBranchManager = isBranchManager;
	}
}