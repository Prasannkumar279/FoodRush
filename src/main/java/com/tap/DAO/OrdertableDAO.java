package com.tap.DAO;

import java.util.List;

import com.tap.model.Ordertable;


public interface OrdertableDAO {
	

	int addOrdertable(Ordertable od);
	void updateOrdertable(Ordertable od);
	void deleteOrdertable(int orderID); 
	Ordertable getOrdertable(int orderID); 
	List<Ordertable> getAllOrdertable();
	

}
