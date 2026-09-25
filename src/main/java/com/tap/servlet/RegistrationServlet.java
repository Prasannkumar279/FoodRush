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

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Registration Servlet Executed");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String role = req.getParameter("role");
		
     String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		  
	  User u = new User(userName,hashpw,email,address,role);
	  
	
	  
	  UserDAOImpl userDAOImpl = new UserDAOImpl();
	  int res = userDAOImpl.addUser(u);
		
	  
	  if(res==1) {
		    User loggedUser = userDAOImpl.getUserByUserName(userName);
		  
		  	HttpSession session = req.getSession();
		    session.setAttribute("u", loggedUser);
		  resp.sendRedirect("RestaurantServlet");
		  
	  }
	  else {
		  resp.sendRedirect("registration.html");
	  }
		
		
	}
	
	

}
