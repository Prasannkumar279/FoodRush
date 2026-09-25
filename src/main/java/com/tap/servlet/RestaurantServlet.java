


package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.restaurantDAOImpl;
import com.tap.model.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RestaurantServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Servlet Executed");

		restaurantDAOImpl rdao = new restaurantDAOImpl();
		

	    String search = request.getParameter("search");
		
	    
	    
		
	    List<restaurant> restaurantList;

	    if (search != null && !search.trim().isEmpty()) {

	        restaurantList = rdao.searchRestaurants(search.trim());

	    } else {

	        restaurantList = rdao.getAllrestaurant();
	    }

	    
		for (restaurant r : restaurantList) {
			System.out.println(r);
		}
		 
		request.setAttribute("allrestaurant", restaurantList);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("restaurant.jsp");
		rd.forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}