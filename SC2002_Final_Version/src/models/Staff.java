package models;

public class Staff {
	private String id;
	private String password;
	private String name;
    private char gender;
    private char role;
    private int age;
	private String branchName;
	
	public Staff(String id, String name, char gender, char role, int age, String branchName) {
		super();
		this.id = id;
		this.password = "password";
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.branchName = branchName;
	}
	
	public Staff(String id, String password, String name, char gender, char role, int age, String branchName) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.branchName = branchName;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getRole() {
		return role;
	}

	public void setRole(char role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
}
