package com.tap.model;

import java.sql.Timestamp;

public class Ordertable {
    private int orderID;
    private int userId;
    private int restaurantID;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String paymentMethod;
    
    public Ordertable() {
    	
    }
    

	public Ordertable(int orderID, int userId, int restaurantID, Timestamp orderDate, double totalAmount, String status,
			String paymentMethod) {
		super();
		this.orderID = orderID;
		this.userId = userId;
		this.restaurantID = restaurantID;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
	}
	public Ordertable(int orderID, int userId, int restaurantID,
            double totalAmount, String status, String paymentMethod) {
		super();
		this.orderID = orderID;
		this.userId = userId;
		this.restaurantID = restaurantID;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	@Override
	public String toString() {
		return "Ordertable [orderID=" + orderID + ", userId=" + userId + ", restaurantID=" + restaurantID
				+ ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", paymentMethod=" + paymentMethod + "]";
	}
    

}
