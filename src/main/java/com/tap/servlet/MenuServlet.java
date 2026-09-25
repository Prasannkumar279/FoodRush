package com.tap.servlet;
import com.tap.DAOImpl.restaurantDAOImpl;
import com.tap.model.restaurant;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.DAOImpl.restaurantDAOImpl;
import com.tap.model.Menu;
import com.tap.model.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.tap.DAOImpl.restaurantDAOImpl;
import com.tap.model.restaurant;
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int restaurantID = Integer.parseInt(request.getParameter("restaurantID"));
	//	System.out.println("Servlet Executed");

		MenuDAOImpl mdao = new MenuDAOImpl();

		List<Menu> MenuList = mdao.getAllMenu(restaurantID);
		 restaurantDAOImpl rdao = new restaurantDAOImpl();

		  restaurant restaurant = rdao.getrestaurant(restaurantID);
		for (Menu m: MenuList) {
			System.out.println(m);
		}
		 
		request.setAttribute("allMenu", MenuList);
		request.setAttribute("restaurant", restaurant);
		
		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
		rd.forward(request, response);

   }
}
