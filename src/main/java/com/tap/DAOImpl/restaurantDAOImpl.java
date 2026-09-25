package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.restaurantDAO;
import com.tap.Utility.DBConnection;
import com.tap.model.User;
import com.tap.model.restaurant;

public class restaurantDAOImpl implements restaurantDAO {

	
	private static final String INSERT_QUERY = "INSERT INTO restaurant(restaurantID,restName,cuisineType,deliveryTime,address, adminUserId,"
			+ "rating, isActive,image) VALUES(?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_QUERY =
		    "SELECT * FROM restaurant WHERE restaurantID = ?";
	
	private static final String GETALL_QUERY = "SELECT * FROM restaurant";
	
	private static final String UPDATE_QUERY = "UPDATE restaurant SET restName = ?, cuisineType = ?, deliveryTime=?,"
			+ " address=?, adminUserId =?, rating=?, isActive=? WHERE restaurantID = ?";
	
	private static final String DELETE_QUERY = "DELETE FROM restaurant WHERE restaurantID = ?";
	
	private static final String SEARCH_QUERY =
	        "SELECT * FROM restaurant WHERE restName LIKE ? OR cuisineType LIKE ?";
	
	@Override
	public void addrestaurant(restaurant r) {
		


		    Connection connection = DBConnection.getConnection();

		    try {
		    	//System.out.println(INSERT_QUERY);
		        PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

		        pstmt.setInt(1, r.getRestaurantID());
		        pstmt.setString(2, r.getRestName());
		        pstmt.setString(3, r.getCuisineType());
		        pstmt.setInt(4, r.getDeliveryTime());
		        pstmt.setString(5, r.getAddress());
		        pstmt.setInt(6, r.getAdminUserId());
		        pstmt.setDouble(7, r.getRating());
		        pstmt.setBoolean(8, r.getIsActive());

		        int i = pstmt.executeUpdate();
		        System.out.println(i);

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		
			


	@Override
	public void updaterestaurant(restaurant r) {
		Connection connection = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);
			
			pstmt.setString(1, r.getRestName());
			pstmt.setString(2, r.getCuisineType());
			pstmt.setInt(3, r.getDeliveryTime());
			pstmt.setString(4, r.getAddress());
			pstmt.setInt(5, r.getAdminUserId());
			pstmt.setDouble(6, r.getRating());
			pstmt.setBoolean(7, r.getIsActive());
			pstmt.setBytes(8, r.getImage());
			pstmt.setInt(9, r.getRestaurantID());
			
			
			int i = pstmt.executeUpdate();
			System.out.println(i);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		

	@Override
	public void deleterestaurant(int restaurantID) {
		Connection connection  = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1,restaurantID);
			
			int rows = pstmt.executeUpdate();
			
		    if(rows>0) {	
		    	System.out.println("RESTAURANT DELETED");
		    }else {
		    	System.out.println("RESTAURANT NOT FOUND");
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public restaurant getrestaurant(int restaurantID) {
		
	

		    Connection connection = DBConnection.getConnection();
		    restaurant r = null;

		    try {
		        PreparedStatement pstmt = connection.prepareStatement(GET_QUERY);
		        pstmt.setInt(1, restaurantID);

		        ResultSet res = pstmt.executeQuery();

		        while (res.next()) {
		            r = getRestaurantByResultSet(res);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return r;
		}


	@Override
	public List<restaurant> getAllrestaurant() {
		List<restaurant> list = new ArrayList<restaurant>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(GETALL_QUERY);
			
			while(res.next()) {
			  restaurant  r = 	getRestaurantByResultSet(res);
			  list.add(r);
			}
		
			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
		
		//return null;
	}
	
	public List<restaurant> searchRestaurants(String search) {

	    List<restaurant> list = new ArrayList<restaurant>();

	    Connection connection = DBConnection.getConnection();

	    try {

	        PreparedStatement pstmt = connection.prepareStatement(SEARCH_QUERY);

	        pstmt.setString(1, "%" + search + "%");
	        pstmt.setString(2, "%" + search + "%");

	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {

	            restaurant r = getRestaurantByResultSet(res);

	            list.add(r);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	
	private static restaurant getRestaurantByResultSet(ResultSet res) throws SQLException {
	
	int restaurantID = res.getInt("restaurantID");
	String restName = res.getString("restName");
	String cuisineType = res.getString("cuisineType");
	int deliveryTime = res.getInt("deliveryTime");
	String address = res.getString("address");
	int adminUserId = res.getInt("adminUserId");
    double rating = res.getDouble("rating");
	boolean isActive= res.getBoolean("isActive");
	byte[] image = res.getBytes("Image");
	
    restaurant  r = new restaurant( restaurantID,  restName,  cuisineType,  deliveryTime,  address,adminUserId, rating, isActive,image);
    return r;
	
	}

}
