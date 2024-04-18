package console;

import java.util.List;

public class Branch {
    private String name;
    private String daysOpen;
    private String openingHours;
    private String location;
    private int numberOfStaffs;
    private int numberOfManagers;
    private Menu menu;
    private OrderStatus orderStatus;
    private List<Staff> allStaffs;

    public Branch(String name, String daysOpen, String openingHours, String location, String menuFilePath, List<Staff> staffsByBranch) {
        this.name = name;
        this.daysOpen = daysOpen;
        this.openingHours = openingHours;
        this.location = location;
        this.numberOfStaffs = 0; // Initialize with 0 staffs
        this.menu = new Menu(menuFilePath);
        this.orderStatus = new OrderStatus();
        this.allStaffs = staffsByBranch;
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