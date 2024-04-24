package models;

public class MenuItem {
	public enum Category{
		FOOD,
		DRINK,
		DESSERT
	}
	
	private String name;
	private float price;
	private boolean isAvail;
	private Category category;
	
	public MenuItem(String name, float price, boolean isAvail, Category category) {
		this.name = name;
		this.price = price;
		this.isAvail = isAvail;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAvail() {
		return isAvail;
	}

	public void setAvail(boolean isAvail) {
		this.isAvail = isAvail;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
