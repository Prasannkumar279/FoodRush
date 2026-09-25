package com.tap.DAO;

import java.util.List;

import com.tap.model.restaurant;


public interface restaurantDAO {
	
	void addrestaurant(restaurant r);
	void updaterestaurant(restaurant r);
	void deleterestaurant(int restaurantID); 
	restaurant getrestaurant(int restaurantID); 
	List<restaurant> getAllrestaurant();
	
	
}
