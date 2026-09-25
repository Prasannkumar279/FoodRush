package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callCartServlet")
public class CartServlet  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		String action = req.getParameter("action");
		
	    HttpSession session = req.getSession();
	    
	    Cart cart = (Cart)session.getAttribute("cart");
	  //  Integer  restaurantID = (Integer)session.getAttribute("restaurantID");
	    
	    int newRestaurantID = Integer.parseInt(req.getParameter("restaurantID"));
	    
	  //  if(cart==null ||  restaurantID == null || restaurantID != newRestaurantID  ) {
	    	 if(cart==null ) {
		cart = new Cart();
		session.setAttribute("cart",cart);
	
	  }
		session.setAttribute("restaurantID",newRestaurantID);
	    
		
		if(action.equals("add")){
			addItemToCart(req,cart);
		}
		else if(action.equals("update")) {
			updateCart(req,cart);
		}
		else if(action.equals("delete")){
			removeItemFromCart(req,cart);
		}
		resp.sendRedirect("cart.jsp");
		
	}

	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuID"));
		cart.removeItem(menuID);
		
	}

	private void updateCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuID"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
	
		cart.update(menuID,quantity);
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuID"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menu = menuDAOImpl.getMenu(menuID);
		
	
		
		
		if(menu != null) {
			
			HttpSession session = req.getSession();
			session.setAttribute("restaurantID", menu.getRestaurantID());
			
	    CartItem cartItem = new CartItem(menuID,menu.getRestaurantID(),menu.getItemName(),menu.getPrice(),quantity);
	    cartItem.setImagePath(menu.getImagePath());
	    cart.addCartItem(cartItem);
			
		}
		
		
	}
	
	

}
