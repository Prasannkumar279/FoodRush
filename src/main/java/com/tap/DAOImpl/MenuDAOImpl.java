package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.Utility.DBConnection;
import com.tap.model.Menu;

public class MenuDAOImpl implements MenuDAO{
	
	private static final String INSERT_QUERY = "INSERT INTO menu(menuID,restaurantID,itemName,description,price,isAvailable,category)"
			+ "VALUES (?,?,?,?,?,?,?)";
	
	private static final String GET_QUERY =  "SELECT * FROM Menu WHERE menuID = ?";
	
	private static final String GETALLMENU_QUERY = "SELECT * FROM Menu";
	
	private static final String UPDATE_QUERY = "UPDATE Menu SET restaurantID=?, itemName=?, description=?, price=?, isAvailable=?, category=?, updatedAt=? WHERE menuID=?";
	

	private static final String DELETE_QUERY = "DELETE FROM Menu WHERE menuID=?";
	
	private static final String GET_MENU_BY_RESTAURANT_QUERY =
	        "SELECT * FROM Menu WHERE restaurantID = ?";

	@Override
	public void addMenu(Menu m) {
		  Connection connection = DBConnection.getConnection();
		  
		  try {

			    PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

			    pstmt.setInt(1, m.getMenuID());
			    pstmt.setInt(2, m.getRestaurantID());
			    pstmt.setString(3, m.getItemName());
			    pstmt.setString(4, m.getDescription());
			    pstmt.setDouble(5, m.getPrice());
			    pstmt.setBoolean(6, m.isAvailable());
			    pstmt.setString(7, m.getCategory());
			    pstmt.setString(8, m.getImagePath());
			    

			    int i = pstmt.executeUpdate();
			    System.out.println(i);

			} catch (SQLException e) {

			    e.printStackTrace();
		  
	    }
	}
		  
		    	 
		
	@Override
	public void updateMenu(Menu m) {
	    Connection connection = DBConnection.getConnection();

	    try {
	        PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

	        pstmt.setInt(1, m.getRestaurantID());
	        pstmt.setString(2, m.getItemName());
	        pstmt.setString(3, m.getDescription());
	        pstmt.setDouble(4, m.getPrice());
	        pstmt.setBoolean(5, m.isAvailable());
	        pstmt.setString(6, m.getCategory());
	        pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
	        
	        pstmt.setInt(8, m.getMenuID());

	        int i = pstmt.executeUpdate();
	        System.out.println(i);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		
	}

	@Override
	public void deleteMenu(int menuID) {
		 Connection connection = DBConnection.getConnection();

		    try {
		        PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);

		        pstmt.setInt(1, menuID);

		        int rows = pstmt.executeUpdate();

		        if (rows > 0) {
		            System.out.println("MENU DELETED");
		        } else {
		            System.out.println("MENU NOT FOUND");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	
	}

	@Override
	public Menu getMenu(int menuID) {

	    Connection connection = DBConnection.getConnection();
	    Menu m = null;

	    try {
	        PreparedStatement pstmt = connection.prepareStatement(GET_QUERY);

	        pstmt.setInt(1, menuID);

	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {
	            m = getMenuByResultSet(res);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return m;
	}

	@Override
	public List<Menu> getAllMenu() {
		
		    List<Menu> list = new ArrayList<Menu>();

		    Connection connection = DBConnection.getConnection();

		    try {

		        Statement stmt = connection.createStatement();

		        ResultSet res = stmt.executeQuery(GETALLMENU_QUERY);

		        while (res.next()) {

		            Menu m = getMenuByResultSet(res);
		            list.add(m);

		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return list;
		}
    
	@Override
	public List<Menu> getAllMenu(int restaurantID) {

	    List<Menu> list = new ArrayList<>();

	    Connection connection = DBConnection.getConnection();

	    try {

	        PreparedStatement pstmt =
	                connection.prepareStatement(GET_MENU_BY_RESTAURANT_QUERY);

	        pstmt.setInt(1, restaurantID);

	        ResultSet res = pstmt.executeQuery();

	        while (res.next()) {

	            Menu m = getMenuByResultSet(res);

	            list.add(m);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	

	
	private static Menu getMenuByResultSet(ResultSet res) throws SQLException {

	    int menuID = res.getInt("menuID");
	    int restaurantID = res.getInt("restaurantID");
	    String itemName = res.getString("itemName");
	    String description = res.getString("description");
	    double price = res.getDouble("price");
	    boolean isAvailable = res.getBoolean("isAvailable");
	    String category = res.getString("category");
	    Timestamp createdAt = res.getTimestamp("createdAt");
	    Timestamp updatedAt = res.getTimestamp("updatedAt");
	    Timestamp deletedAt = res.getTimestamp("deletedAt");
	    String imagePath = res.getString("imagePath");

	    Menu m = new Menu(menuID, restaurantID, itemName, description,
	            price, isAvailable, category,
	            createdAt, updatedAt, deletedAt,imagePath);

	    return m;
	}
	
	

}
