package com.tap.DAO;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	
	void addMenu(Menu m);
	void updateMenu(Menu m);
	void deleteMenu(int menuID); 
	Menu getMenu(int menuID); 
	List<Menu> getAllMenu();
	List<Menu> getAllMenu(int restaurantID);
	

}
