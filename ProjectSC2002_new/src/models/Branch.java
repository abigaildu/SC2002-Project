package models;

public class Branch {
	private String name;
	private String location;
	private int staffQuota;
	
	public Branch(String name, String location, int staffQuota) {
		super();
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStaffQuota() {
		return staffQuota;
	}

	public void setStaffQuota(int staffQuota) {
		this.staffQuota = staffQuota;
	}
}
