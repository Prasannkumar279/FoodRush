package com.tap.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.DAOImpl.OrderitemDAOImpl;
import com.tap.DAOImpl.OrdertableDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Orderitem;
import com.tap.model.Ordertable;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		User u = (User)session.getAttribute("u");
		Cart cart = (Cart)session.getAttribute("cart");
		int  restaurantID = (int)session.getAttribute("restaurantID");
		String paymentMode = req.getParameter("paymentMode");
		double finalTotal   = (double)session.getAttribute("finalTotal");
		
		if(u==null) {
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
			return ;
		}
		
         if(u != null && cart != null && ! cart.getItems().isEmpty()) {
        	 
     		Ordertable ordertable  = new Ordertable();
    		
         ordertable.setUserId(u.getUserId());
   		 ordertable.setRestaurantID(restaurantID);
   		 ordertable.setOrderDate(new Timestamp(System.currentTimeMillis()));
   		 ordertable.setPaymentMethod(paymentMode);
   		 ordertable.setStatus("pending");
   		 ordertable.setTotalAmount(finalTotal);
   		 
   		 OrdertableDAOImpl ordertableDAOImpl =  new OrdertableDAOImpl();
   		int orderID =  ordertableDAOImpl.addOrdertable(ordertable);
   		 
   		 
   	
    
   	 OrderitemDAOImpl orderitemDAOImpl =  new OrderitemDAOImpl();
   		 
   		 for(CartItem item : cart.getItems().values()) {
   			 
   			 Orderitem orderitem = new Orderitem();
   	   		 

   	   		 orderitem.setOrderID(orderID);
   			 orderitem.setMenuID(item.getMenuID());
   			 orderitem.setQuantity(item.getQuantity());
   			 orderitem.setItemTotal(item.getTotalPrice());
   			orderitemDAOImpl.addOrderitem(orderitem);
   			 
   		 }
   		 
   		
   		// orderitemDAOImpl.addOrderitem(orderitem);
        
   		 session.setAttribute("orderID", orderID);
   		 session.setAttribute("finalTotal", finalTotal);
   		 session.removeAttribute("cart");
   		 //session.removeAttribute("restaurantID");
   		 session.removeAttribute("paymentMode");
   		 
   		 resp.sendRedirect("orderConfirmation.jsp");
   		 
   		 
         }
         
         else {
        	 resp.sendRedirect("cart.jsp");
         }

	}

}
