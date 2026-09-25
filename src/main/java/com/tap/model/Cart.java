package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	
	private Map<Integer, CartItem> items;
	 
	public Cart() {
		items = new HashMap<Integer, CartItem>();
	}
    
	public Map<Integer, CartItem> getItems(){
		return items;
	}
	


	public void addCartItem(CartItem cartItem) {
		int menuID = cartItem.getMenuID();
		
		if(items.containsKey(menuID)) {
		CartItem existingItem = items.get(menuID);
		existingItem.setQuantity(existingItem.getQuantity()+1);
		
		}
		else{
			items.put(menuID, cartItem);
		}
             		
	}

	public void update(int menuID, int quantity) {
		
		if(items.containsKey(menuID)) {
			
			if(quantity<=0) {
				items.remove(menuID);
			}
			else {
				CartItem cartItem =items.get(menuID);
				cartItem.setQuantity(quantity);
	
			}
		}
	}

	public void removeItem(int menuID) {
		items.remove(menuID);
		
	}

}
