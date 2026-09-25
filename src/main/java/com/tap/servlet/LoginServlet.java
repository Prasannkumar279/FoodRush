package com.tap.servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String userName = 	req.getParameter("userName");
		String password = req.getParameter("password");
		
	
		
	   UserDAOImpl userDAOImpl = new UserDAOImpl();
	   User u =  userDAOImpl.getUserByUserName(userName);
	 //  String dbPassword = u.getPassword();
	   
	   if(u != null && BCrypt.checkpw(password, u.getPassword())) {
		   
		   HttpSession session = req.getSession();
		   session.setAttribute("u", u);
			
		   session.setAttribute("userName",userName);
		   resp.sendRedirect("RestaurantServlet");
	   }else {
		   resp.sendRedirect("login.html");
	   }
	
		
	}
	

}
