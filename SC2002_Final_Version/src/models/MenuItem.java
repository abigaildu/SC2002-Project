package models;

public class MenuItem {
	public enum Category{
		FOOD,
		DRINK,
		DESSERT
	}
	
	private String name;
	private double price;
	private boolean isAvailable;
	private Category category;
	
	public MenuItem(String name, double price, boolean isAvailable, Category category) {
		this.name = name;
		this.price = price;
		this.isAvailable = isAvailable;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
