package console;

public class Branch {
    private String name;
    private String daysOpen;
    private String openingHours;
    private String location;
    private int numberOfStaffs;

    public Branch(String name, String daysOpen, String openingHours, String location) {
        this.name = name;
        this.daysOpen = daysOpen;
        this.openingHours = openingHours;
        this.location = location;
        this.numberOfStaffs = 0; // Initialize with 0 staffs
    }
    
    public Branch (String name) {
    	this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDaysOpen() {
        return daysOpen;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getLocation() {
        return location;
    }
    
    public int getNumberOfStaffs(StaffManagement staffManagement) {
        return 0;
    }

    public void setNumberOfStaffs(int numberOfStaffs) {
        this.numberOfStaffs = numberOfStaffs;
    }

    public void incrementStaffCount() {
        this.numberOfStaffs++;
    }

    public void decrementStaffCount() {
        if (this.numberOfStaffs > 0) {
            this.numberOfStaffs--;
        }
    }

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