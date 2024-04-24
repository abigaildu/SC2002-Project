package models;

public class Staff {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String role;
	private int age;
	private String branchName;
	
	public Staff(String id, String password, String name, String gender, String role, int age, String branchName) {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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
