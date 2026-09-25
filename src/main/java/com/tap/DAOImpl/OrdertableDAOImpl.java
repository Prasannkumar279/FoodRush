package com.tap.DAOImpl;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrdertableDAO;
import com.tap.Utility.DBConnection;
import com.tap.model.Ordertable;
import com.tap.model.User;
import com.tap.model.restaurant;


	public class OrdertableDAOImpl implements OrdertableDAO {
		
		private static final String INSERT_QUERY =
				"INSERT INTO Ordertable(userId,restaurantID,orderDate,totalAmount,status,paymentMethod) VALUES(?,?,?,?,?,?)";

         private static final String GET_QUERY =     "SELECT * FROM Ordertable WHERE orderID = ?";
         
         private static final String GETALL_QUERY = "SELECT * FROM Ordertable";
         
         private static final String UPDATE_QUERY = "UPDATE Ordertable SET userId=?,"
         		+ "restaurantID=?,totalAmount=?,status=?,paymentMethod=? WHERE  orderID = ?";
         
         private static final String DELETE_QUERY = "DELETE  FROM Ordertable WHERE orderID = ?";
         
		@Override
		public int addOrdertable(Ordertable od) {
			
			int orderID = 0;
		     Connection connection = DBConnection.getConnection();

		     System.out.println(connection);
		     
		     try {
		    	 
		    	 PreparedStatement pstmt = connection.prepareStatement(
		    			    INSERT_QUERY,
		    			    Statement.RETURN_GENERATED_KEYS);
		    	 
//		 		PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY); 
	    	  
		 		 
		    	 pstmt.setInt(1, od.getUserId());
		 		 pstmt.setInt(2, od.getRestaurantID());
		 		 pstmt.setTimestamp(3,new java.sql.Timestamp(od.getOrderDate().getTime()));
		 		 pstmt.setDouble(4, od.getTotalAmount());
		 		 
		 		System.out.println("Status = '" + od.getStatus() + "'");
		 		pstmt.setString(5, od.getStatus().trim());
		 		 
//		 		 pstmt.setString(5,od.getStatus());
		 		 pstmt.setString(6,od.getPaymentMethod());
//		 		 pstmt.setInt(6, od.getOrderID());;
		    	  

			        int i  = pstmt.executeUpdate();
			        
			            System.out.println(i);
			            
			        	ResultSet res = pstmt.getGeneratedKeys();
						if(res.next()) {
							orderID = res.getInt(1);
							System.out.println("Generate Order ID = "+ orderID);
							
						}

		    	 
		     }catch(SQLException  e) {
		    	 e.printStackTrace();
		    	 
		     }
		     
		     return orderID;
		     
		}

		@Override
		public void updateOrdertable(Ordertable od) {
			   Connection connection = DBConnection.getConnection();
			
			try {
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);
				
			
				pstmt.setInt(1, od.getUserId());
                pstmt.setInt(2, od.getRestaurantID());
		 		pstmt.setDouble(3, od.getTotalAmount());
				pstmt.setString(4, od.getStatus());
				pstmt.setString(5,od.getPaymentMethod());
				pstmt.setInt(6, od.getOrderID());
				
				int i = pstmt.executeUpdate();
				System.out.println(i);
				
			
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		

		@Override
		public void deleteOrdertable(int orderID) {
			Connection connection  = DBConnection.getConnection();
			try {
				PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
				pstmt.setInt(1,orderID);
				
				int rows = pstmt.executeUpdate();
				
			    if(rows>0) {	
			    	System.out.println("Order Table DELETED");
			    }else {
			    	System.out.println("Order Table NOT FOUND");
			    }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public Ordertable getOrdertable(int orderID) {
			Connection connection = DBConnection.getConnection();
		    Ordertable od = null;

		    try {
		        PreparedStatement pstmt = connection.prepareStatement(GET_QUERY);
		        pstmt.setInt(1, orderID);

		        ResultSet res = pstmt.executeQuery();

		        while (res.next()) {
		            od = getOrdertableByResultSet(res);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

			return od;
		}
		

		@Override
		public List<Ordertable> getAllOrdertable() {
			List<Ordertable> list = new ArrayList<Ordertable>();
			Connection connection = DBConnection.getConnection();
			try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(GETALL_QUERY);
			
			while(res.next()) {
			  Ordertable od  = 	getOrdertableByResultSet(res);
			  list.add(od);
			}
		
			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
		
		}
		
		
		private static Ordertable getOrdertableByResultSet(ResultSet res) throws SQLException {
			
			int orderID = res.getInt("orderID");
			int userId = res.getInt("userId");
			int restaurantID = res.getInt("restaurantID");
			double totalAmount = res.getDouble("totalAmount");
			String status = res.getString("status");
			String paymentMethod = res.getString("paymentMethod");
			Ordertable od  = new Ordertable(orderID,userId,restaurantID,totalAmount,status,paymentMethod);
		    return od;
			
		}
		
		

   }


