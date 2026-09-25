package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.OrderitemDAOImpl;
import com.tap.model.Orderitem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderItemTest")
public class OrderItemTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Orderitem od = new Orderitem(
                1,      // orderItemID
                101,    // orderID
                305,    // menuID
                2,      // quantity
                180.00  // itemTotal
        );

        OrderitemDAOImpl orderItemDAOImpl = new OrderitemDAOImpl();

        orderItemDAOImpl.addOrderitem(od);

        resp.getWriter().println("OrderItem inserted successfully!");
    }
}