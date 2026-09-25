package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderitemDAO;
import com.tap.Utility.DBConnection;
import com.tap.model.Orderitem;



public class OrderitemDAOImpl implements OrderitemDAO {
	
	 private static final String INSERT_QUERY =
	            "INSERT INTO OrderItem(orderID, menuID, quantity, itemTotal) "
	            + "VALUES (?,?,?,?)";

	    private static final String GET_QUERY =
	            "SELECT * FROM OrderItem WHERE orderItemID = ?";

	    private static final String GETALL_QUERY =
	            "SELECT * FROM OrderItem";

	    private static final String UPDATE_QUERY =
	            "UPDATE OrderItem SET orderID=?, menuID=?, quantity=?, itemTotal=? "
	            + "WHERE orderItemID=?";

	    private static final String DELETE_QUERY =
	            "DELETE FROM OrderItem WHERE orderItemID=?";

	@Override
	public void addOrderitem(Orderitem od) {
		   Connection connection = DBConnection.getConnection();

	        try {

	            PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

	
	            pstmt.setInt(1, od.getOrderID());
	            pstmt.setInt(2, od.getMenuID());
	            pstmt.setInt(3, od.getQuantity());
	            pstmt.setDouble(4, od.getItemTotal());

	            int i = pstmt.executeUpdate();

	            System.out.println("OrderItem INSERT result = " +i);

	        } catch (SQLException e) {
	        	 e.printStackTrace();
	        	
	        }
 	
	}

	@Override
	public Orderitem getOrderitem(int orderItemID) {
		
		Connection connection = DBConnection.getConnection();

	        Orderitem od = null;

	        try {

	            PreparedStatement pstmt = connection.prepareStatement(GET_QUERY);

	            pstmt.setInt(1, orderItemID);

	            ResultSet res = pstmt.executeQuery();

	            while (res.next()) {

	                od = getOrderItemByResultSet(res);
	            }

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
		
		
		return od;
	}

	@Override
	public List<Orderitem> getAllOrderItem() {
		
		
        List<Orderitem> list = new ArrayList<Orderitem>();

        Connection connection = DBConnection.getConnection();

        try {

            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery(GETALL_QUERY);

            while (res.next()) {

                Orderitem od = getOrderItemByResultSet(res);

                list.add(od);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        
		return list;
	}

	@Override
	public void updateOrderitem(Orderitem od) {
	    Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

            pstmt.setInt(1, od.getOrderID());
            pstmt.setInt(2, od.getMenuID());
            pstmt.setInt(3, od.getQuantity());
            pstmt.setDouble(4, od.getItemTotal());
            pstmt.setInt(5, od.getOrderItemID());

            int i = pstmt.executeUpdate();

            System.out.println(i);

        } catch (SQLException e) {

            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteOrderitem(int orderItemID) {

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_QUERY);

            pstmt.setInt(1, orderItemID);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {

                System.out.println("ORDER ITEM DELETED");

            } else {

                System.out.println("ORDER ITEM NOT FOUND");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
		
	}
	

    private static Orderitem getOrderItemByResultSet(ResultSet res) throws SQLException {

        int orderItemID = res.getInt("orderItemID");
        int orderID = res.getInt("orderID");
        int menuID = res.getInt("menuID");
        int quantity = res.getInt("quantity");
        double itemTotal = res.getDouble("itemTotal");

        Orderitem od = new Orderitem(orderItemID,orderID,menuID,quantity,itemTotal);

        return od;
    }
}
