package console;

/**
Representing a Branch.
*/
public class Branch {
	/**
	* The name of this Branch.
	*/
    private String name;
    /**
	* The opening days of this Branch.
	*/
    private String daysOpen;
    /**
	* The opening hours of this Branch.
	*/
    private String openingHours;
    /**
	* The location of this Branch.
	*/
    private String location;
    /**
	* The number of staffs of this Branch.
	*/
    private int numberOfStaffs;

    /**
     * Creating a new Branch with the given information.
     * @param name This Branch's name.
     * @param daysOpen This Branch's opening days.
     * @param openingHours This Branch's opening hours.
     * @param location This Branch's location.
     */
    public Branch(String name, String daysOpen, String openingHours, String location) {
        this.name = name;
        this.daysOpen = daysOpen;
        this.openingHours = openingHours;
        this.location = location;
        this.numberOfStaffs = 0; // Initialize with 0 staffs
    }
  
    /**
     * Getting the name of this Branch.
     * @return This Branch's name.
     */
    public String getName() {
		return name;
	}

    /**
     * Changing the name of this Branch.
     * @param name This Branch's new name.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Getting the opening days of this Branch.
     * @return This Branch's opening days.
     */
	public String getDaysOpen() {
		return daysOpen;
	}

	/**
     * Changing the opening days of this Branch.
     * @param daysOpen This Branch's new opening days.
     */
	public void setDaysOpen(String daysOpen) {
		this.daysOpen = daysOpen;
	}

	/**
     * Getting the opening hours of this Branch.
     * @return This Branch's opening hours.
     */
	public String getOpeningHours() {
		return openingHours;
	}

	/**
     * Changing the opening hours of this Branch.
     * @param openingHours This Branch's new opening hours.
     */
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	/**
     * Getting the location of this Branch.
     * @return This Branch's location.
     */
	public String getLocation() {
		return location;
	}

	/**
     * Changing the location of this Branch.
     * @param location This Branch's new location.
     */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
     * Getting the number of staffs of this Branch.
     * @return The number of staffs of this Branch.
     */
	public int getNumberOfStaffs() {
		return numberOfStaffs;
	}

	/**
     * Changing the number of staffs of this Branch.
     * @param numberOfStaffs The new number of staffs of this Branch.
     */
	public void setNumberOfStaffs(int numberOfStaffs) {
		this.numberOfStaffs = numberOfStaffs;
	}
	
	/**
     * Incrementing the number of staffs of this Branch by 1.
     */
	public void incrementStaffCount() {
		this.numberOfStaffs++;
	}

	/**
     * Decrementing the number of staffs of this Branch by 1.
     */
	public void decrementStaffCount() {
		if (this.numberOfStaffs > 0) {
			this.numberOfStaffs--;
		}
	}

	/**
     * Describing the information of this Branch.
     * @return Information of this Branch.
     */
	@Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", daysOpen='" + daysOpen + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", location='" + location + '\'' +
                ", numberOfStaffs=" + numberOfStaffs +
                '}';
    }
}