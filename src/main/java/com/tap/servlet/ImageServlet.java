


package com.tap.servlet;

import java.io.IOException;
import java.io.OutputStream;

import com.tap.DAOImpl.restaurantDAOImpl;
import com.tap.model.restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(request.getParameter("id"));

        restaurantDAOImpl dao = new restaurantDAOImpl();

        restaurant r = dao.getrestaurant(restaurantId);

        if (r != null && r.getImage() != null) {

            response.setContentType("image/jpeg");

            OutputStream os = response.getOutputStream();

            os.write(r.getImage());

            os.flush();
            os.close();
        }
    }
}