package com.tap.model;

public class restaurant {
	
	private int restaurantID;
	private String restName;
	private String cuisineType;
	private int deliveryTime;
	private String address;
	private int  adminUserId;
	private Double rating;
	private Boolean isActive;  
	private byte[] image;
	
	
     public restaurant(){
		
	 }


	 public restaurant(int restaurantID, String restName, String cuisineType, int deliveryTime, String address,
			int adminUserId, Double rating, Boolean isActive, byte[] image) {
		super();
		this.restaurantID = restaurantID;
		this.restName = restName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.image = image;
	}
	 
	 

	 public restaurant(int restaurantID, String restName, String cuisineType, int deliveryTime, String address,
			int adminUserId,byte[] image) {
		super();
		this.restaurantID = restaurantID;
		this.restName = restName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminUserId = adminUserId;
		this.image = image;
	}


	 public int getRestaurantID() {
		 return restaurantID;
	 }


	 public void setRestaurantID(int restaurantID) {
		 this.restaurantID = restaurantID;
	 }


	 public String getRestName() {
		 return restName;
	 }


	 public void setRestName(String restName) {
		 this.restName = restName;
	 }


	 public String getCuisineType() {
		 return cuisineType;
	 }


	 public void setCuisineType(String cuisineType) {
		 this.cuisineType = cuisineType;
	 }


	 public int getDeliveryTime() {
		 return deliveryTime;
	 }


	 public void setDeliveryTime(int deliveryTime) {
		 this.deliveryTime = deliveryTime;
	 }


	 public String getAddress() {
		 return address;
	 }


	 public void setAddress(String address) {
		 this.address = address;
	 }


	 public int getAdminUserId() {
		 return adminUserId;
	 }


	 public void setAdminUserId(int adminUserId) {
		 this.adminUserId = adminUserId;
	 }


	 public Double getRating() {
		 return rating;
	 }


	 public void setRating(Double rating) {
		 this.rating = rating;
	 }


	 public Boolean getIsActive() {
		 return isActive;
	 }


	 public void setIsActive(Boolean isActive) {
		 this.isActive = isActive;
	 }

	 
	 public byte[] getImage() {
		    return image;
		}

		public void setImage(byte[] image) {
		    this.image = image;
		}
	 
	 
	 
	 
	 
	 
	 
	 

	 @Override
	 public String toString() {
		return "restaurant [restaurantID=" + restaurantID + ", restName=" + restName + ", cuisineType=" + cuisineType
				+ ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminUserId=" + adminUserId
				+ ", rating=" + rating + ", isActive=" + isActive + ", image=" + image +  "]";
	 }
    
}
