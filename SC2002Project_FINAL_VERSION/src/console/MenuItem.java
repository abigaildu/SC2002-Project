package console;

/**
Representing a MenuItem.
*/
public class MenuItem {
	/**
	 *  Category of MenuItem
	 */
	public enum Category
	{
	    /**
	     * Food
	     */
		FOOD,

	    /**
	     * Drink
	     */
		DRINK,

	    /**
	     * Dessert
	     */
		DESSERT

	}
	/**
	* The name of this MenuItem.
	*/
    private String itemName;
    /**
	* The description of this MenuItem.
	*/
    private String itemDesc;
    /**
	* The price of this MenuItem.
	*/
    private float price;
    /**
	* The state of availability of this MenuItem.
	*/
    private boolean isAvail;
    /**
	* The category of this MenuItem.
	*/
    private Category category; 

    /**
     * Creating a new MenuItem with the given information.
     * @param itemName This MenuItem's name.
     * @param itemDesc This MenuItem's description.
     * @param price This MenuItem's price.
     * @param isAvail This MenuItem's state of availability.
     * @param category This MenuItem's category.
     */
    public MenuItem(String itemName, String itemDesc, float price, boolean isAvail, Category category) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.price = price;
		this.isAvail = isAvail;
		this.category = category;
	}

    /**
     * Creating a new MenuItem with the given information.
     * @param itemName This MenuItem's name.
     * @param price This MenuItem's price.
     */
	public MenuItem(String itemName, float price) {
        this.itemName = itemName;
        this.price = price;
    }
    
	/**
     * Getting the name of this MenuItem.
     * @return This MenuItem's name.
     */
    public String getItemName() {
		return itemName;
	}

    /**
     * Changing the name of this MenuItem.
     * @param itemName This MenuItem's new name.
     */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
     * Getting the description of this MenuItem.
     * @return This MenuItem's description.
     */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
     * Changing the description of this MenuItem.
     * @param itemDesc This MenuItem's new description.
     */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	/**
     * Getting the price of this MenuItem.
     * @return This MenuItem's price.
     */
	public float getPrice() {
		return price;
	}

	/**
     * Changing the price of this MenuItem.
     * @param price This MenuItem's new price.
     */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
     * Getting the state of availability of this MenuItem.
     * @return This MenuItem's state of availability.
     */
	public boolean isAvail() {
		return isAvail;
	}

	/**
     * Changing the state of availability of this MenuItem.
     * @param isAvail This MenuItem's new state of availability.
     */
	public void setAvail(boolean isAvail) {
		this.isAvail = isAvail;
	}

	/**
     * Getting the category of this MenuItem.
     * @return This MenuItem's category.
     */
	public Category getCategory() {
		return category;
	}

	/**
     * Changing the category of this MenuItem.
     * @param category This MenuItem's new category.
     */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
     * Describing the information of this MenuItem.
     * @return Information of this MenuItem.
     */
	@Override
    public String toString() {
        // Create a string representation of the MenuItem
        return itemName + "," + itemDesc + "," + price + "," + isAvail + "," + category.name();
    }

	/**
     * Getting MenuItem object from a string.
     * @param line Information of this MenuItem object.
     * @return MenuItem object.
     */
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