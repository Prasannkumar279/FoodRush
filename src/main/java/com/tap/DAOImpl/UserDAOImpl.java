package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.Utility.DBConnection;
import com.tap.model.User;

public class UserDAOImpl implements UserDAO {
	
	private static final String INSERT_QUERY = "INSERT INTO user(userName,password,email,address,"
			+ "role) VALUES(?,?,?,?,?)";
	
	private static final String SELECT_QUERY = "SELECT * FROM user WHERE userId = ?";
	
	private static final String SELECT_QUERY_02 = "SELECT * FROM user";
	
	private static final String UPDATE_QUERY = "UPDATE user SET userName = ?, email = ?,password=?,"
			+ " address=? WHERE userId = ?";
	
	private static final String DELETE_QUERY = " DELETE FROM user WHERE userId = ?";
			
	private static final String SELECT_BY_USERNAME =
	        "SELECT * FROM user WHERE userName = ?";
			
			

	@Override
	public int addUser(User u) {
	     Connection connection = DBConnection.getConnection();
	     
	     try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
	
		
			pstmt.setString(4,u.getAddress());
			pstmt.setString(5, u.getRole());
		
			
			int i = pstmt.executeUpdate();
            return i;
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	     return 0;
		
	}

	@Override
	public void updateUser(User u) {
		Connection connection = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);
			
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4,u.getAddress());
		//	pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, u.getUserId());
			
			int i = pstmt.executeUpdate();
			System.out.println(i);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int id) {
	 
		Connection connection  = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1,id);
			
			int rows = pstmt.executeUpdate();
			
		    if(rows>0) {	
		    	System.out.println("USER DELETED");
		    }else {
		    	System.out.println("USER NOT FOUND");
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
			

	@Override
	public User getUser(int id) {
		Connection connection  = DBConnection.getConnection();
		User u = null;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);
			pstmt.setInt(1,id);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				u = getUserByResultSet(res);
			
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return u;
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> list = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		try {
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(SELECT_QUERY_02);
		
		while(res.next()) {
		  User u = 	getUserByResultSet(res);
		  list.add(u);
		}
	
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	
	}
	
	
	@Override
	public User getUserByUserName(String userName) {

	    Connection connection = DBConnection.getConnection();
	    User user = null;

	    try {
	        PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_USERNAME);

	        pstmt.setString(1, userName);

	        ResultSet res = pstmt.executeQuery();

	        if (res.next()) {
	            user = getUserByResultSet(res);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	
	
	
	
	private static User getUserByResultSet(ResultSet res) throws SQLException {
	
	int userID = res.getInt("userId");
	String userName = res.getString("userName");
	String password = res.getString("password");
	String email = res.getString("email");
	String address = res.getString("address");
	String role = res.getString("role");
	Timestamp createdDate = res.getTimestamp("createdDate");
	Timestamp LastLoginDate = res.getTimestamp("LastLoginDate");
	
    User  u = new User( userID,  userName,  password,  email,  address,  role, createdDate,  LastLoginDate);	
    return u;
	
	}


}
