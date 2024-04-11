package console;

public class MenuItem {
	
	public enum Category {
	    FOOD,
	    DRINK,
	    DESSERT
	}
	
    private String itemName;
    private String itemDesc;
    private float price;
    private boolean avail;
    private Category category; // Category of the menu item

    // Constructor
    public MenuItem(String itemName, String itemDesc, float price, boolean avail, Category category) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.price = price;
        this.avail = avail;
        this.category = category;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return avail;
    }

    public void setAvailability(boolean avail) {
        this.avail = avail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        // Create a string representation of the MenuItem
        return itemName + "," + itemDesc + "," + price + "," + avail + "," + category.name();
    }

    public static MenuItem fromString(String line) {
        try {
            String[] parts = line.split(",");
            String itemName = parts[0];
            String itemDesc = parts[1];
            float price = Float.parseFloat(parts[2]);
            boolean avail = Boolean.parseBoolean(parts[3]);
            Category category = Category.valueOf(parts[4]);

            return new MenuItem(itemName, itemDesc, price, avail, category);
        } catch (Exception e) {
            System.out.println("Error parsing menu item from string: " + e.getMessage());
            return null;
        }
    }

}