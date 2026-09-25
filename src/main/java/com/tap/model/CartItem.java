package com.tap.model;

public class CartItem {
	
	private int menuID;
	private int restaurantID;
	private String name;
	private double price;
	private int quantity;
	private String imagePath;
	
	public CartItem() {
		
	}
	
	public CartItem(int menuID, int restaurantID, String name, double price, int quantity) {
		super();
		this.menuID = menuID;
		this.restaurantID = restaurantID;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImagePath() {
	    return imagePath;
	}
	public void setImagePath(String imagePath) {
	    this.imagePath = imagePath;
	}
	public double getTotalPrice() {
		return quantity*price;
	}
	
	
	
}
