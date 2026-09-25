package com.tap.DAO;

import java.util.List;

import com.tap.model.Orderitem;



public interface OrderitemDAO {
	
	   void addOrderitem(Orderitem od);

	    Orderitem getOrderitem(int od);

	    List<Orderitem> getAllOrderItem();

	    void updateOrderitem(Orderitem od);

	    void deleteOrderitem(int orderitemID);
	

}
