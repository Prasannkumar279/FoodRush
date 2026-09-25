<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="com.tap.model.Cart" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="com.tap.model.Menu" %>
 <%@ page import="com.tap.model.restaurant" %>
<%@ page import="java.util.*" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");

    Integer restaurantID = (Integer) session.getAttribute("restaurantID");

    double grandTotal = 0;
%>
    
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodRush - Cart</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background-color: #0d0d0d;
            color: #ffffff;
            min-height: 100vh;
            padding: 40px 20px;
        }

        /* Navigation Bar Placeholder */
        .navbar {
            max-width: 1100px;
            margin: 0 auto 30px auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-bottom: 20px;
            border-bottom: 1px solid #222;
        }

        .brand {
            font-size: 28px;
            font-weight: 700;
            background: linear-gradient(135deg, #ff4d4d, #feca57);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            letter-spacing: 1px;
        }

        .back-link {
            color: #b0b0b0;
            text-decoration: none;
            font-size: 14px;
            transition: color 0.3s ease;
        }

        .back-link:hover {
            color: #ff4d4d;
        }

        /* Cart Layout */
        .cart-wrapper {
            max-width: 1100px;
            margin: 0 auto;
            display: flex;
            gap: 40px;
            align-items: flex-start;
        }

        /* Left Side: Cart Items */
        .cart-items-section {
            flex: 2;
            background-color: #121212;
            border-radius: 16px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
            border: 1px solid #1e1e1e;
        }

        .section-title {
            font-size: 22px;
            font-weight: 600;
            margin-bottom: 25px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px 0;
            border-bottom: 1px solid #222;
        }

        .cart-item:last-of-type {
            border-bottom: none;
        }

        .item-info {
            display: flex;
            align-items: center;
            gap: 20px;
            flex: 1;
        }

        .item-image {
            width: 80px;
            height: 80px;
            border-radius: 12px;
            object-fit: cover;
            box-shadow: 0 4px 10px rgba(0,0,0,0.3);
        }

        .item-details h4 {
            font-size: 16px;
            font-weight: 500;
            margin-bottom: 5px;
        }

        .item-details p {
            font-size: 14px;
            color: #b0b0b0;
            margin-bottom: 8px;
        }

        .item-price {
            font-weight: 600;
            color: #feca57;
        }

        .item-actions {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            gap: 15px;
        }

        /* Quantity Controls */
        .quantity-control {
            display: flex;
            align-items: center;
            background-color: #1e1e1e;
            border-radius: 8px;
            border: 1px solid #333;
            overflow: hidden;
        }

        .quantity-btn {
            background: none;
            border: none;
            color: #ff4d4d;
            font-size: 18px;
            font-weight: 500;
            width: 32px;
            height: 32px;
            cursor: pointer;
            transition: background 0.3s;
        }

        .quantity-btn:hover {
            background-color: #2a2a2a;
        }

        .quantity-display {
            font-size: 14px;
            font-weight: 600;
            width: 30px;
            text-align: center;
            color: #ffffff;
        }

        .remove-btn {
            background: none;
            border: none;
            color: #666;
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 1px;
            cursor: pointer;
            transition: color 0.3s;
        }

        .remove-btn:hover {
            color: #ff4d4d;
        }

        /* Add More Items Button */
        .add-more-container {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px dashed #333;
        }

        .add-more-btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background: none;
            border: 1px solid #ff4d4d;
            color: #ff4d4d;
            padding: 10px 20px;
            border-radius: 8px;
            font-family: 'Poppins', sans-serif;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
        }

        .add-more-btn:hover {
            background-color: rgba(255, 77, 77, 0.1);
        }

        /* Right Side: Bill Summary */
        .bill-section {
            flex: 1;
            background-color: #121212;
            border-radius: 16px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
            border: 1px solid #1e1e1e;
            position: sticky;
            top: 40px;
        }

        .bill-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
            font-size: 14px;
            color: #b0b0b0;
        }

        .bill-row.total {
            border-top: 1px solid #333;
            padding-top: 15px;
            margin-top: 15px;
            font-size: 18px;
            font-weight: 600;
            color: #ffffff;
        }

        .bill-row .value {
            color: #ffffff;
            font-weight: 500;
        }

        .bill-row.total .value {
            color: #feca57;
        }

        /* Gradient Checkout Button */
        .btn-checkout {
            width: 100%;
            background: linear-gradient(135deg, #ff4d4d, #ff9f43, #feca57);
            background-size: 300% 300%;
            color: #121212;
            border: none;
            border-radius: 10px;
            padding: 16px;
            font-size: 16px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 25px;
            font-family: 'Poppins', sans-serif;
            text-transform: uppercase;
            letter-spacing: 1px;
            animation: gradientShift 6s infinite;
        }

        @keyframes gradientShift {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .btn-checkout:hover {
            box-shadow: 0 0 20px rgba(255, 77, 77, 0.4), 0 0 20px rgba(255, 159, 67, 0.4);
            transform: scale(1.02);
        }

        .btn-checkout:active {
            transform: scale(0.98);
        }

        /* Responsive Design */
        @media (max-width: 900px) {
            .cart-wrapper {
                flex-direction: column;
            }
            .bill-section {
                width: 100%;
                position: static;
            }
        }

        @media (max-width: 500px) {
            .item-info {
                flex-direction: column;
                align-items: flex-start;
                gap: 10px;
            }
            .cart-item {
                align-items: flex-start;
            }
        }
	        
		   .item-info {
		    display: flex;
		    align-items: center;
		    gap: 20px;
		}
		
		.item-image {
		    width: 100px;
		    height: 100px;
		    object-fit: cover;
		    border-radius: 12px;
		}
		
		.item-details {
		    display: flex;
		    flex-direction: column;
		    gap: 8px;
		}
		
		.add-more-container {
		    margin-top: 25px;
		    margin-bottom: 25px;
		    text-align: center;
		}
		
		.add-more-btn {
		    display: inline-block;
		    padding: 12px 28px;
		    border: 1px solid #ff4d4d;
		    border-radius: 8px;
		    color: #ff4d4d;
		    background: transparent;
		    text-decoration: none;
		    font-size: 16px;
		    font-weight: 600;
		    transition: all 0.3s ease;
		}
		
		.add-more-btn:hover {
		    background: #ff4d4d;
		    color: white;
		}
				
				.checkout {
		    display: block;
		    width: 100%;
		    box-sizing: border-box;
		    padding: 18px;
		    margin-top: 25px;
		    
		    background: linear-gradient(90deg, #ff4b4b, #ffad42);
		    color: #000;
		    
		    border: none;
		    border-radius: 12px;
		    
		    text-align: center;
		    text-decoration: none;
		    
		    font-size: 20px;
		    font-weight: 800;
		    letter-spacing: 1px;
		    
		    cursor: pointer;
		    transition: 0.3s ease;
		}
		
		.checkout:hover {
		    transform: translateY(-2px);
		    opacity: 0.9;
		}
		
  
    </style>
</head>
<body>

       
       <!-- Simple Navbar -->
<div class="navbar">
    <div class="brand">FOODRUSH</div>

    <%
        Object restaurantObj = session.getAttribute("restaurantID");
    %>

    <% if (restaurantObj != null) { %>

        <a href="MenuServlet?restaurantID=<%= restaurantObj %>">
            ← Back to Menu
        </a>

    <% } else { %>

        <a href="RestaurantServlet">
            ← Back to Menu
        </a>

    <% } %>
</div>
    
        
    </div>

    <div class="cart-wrapper">
        <!-- Left Side: Cart Items -->
        <div class="cart-items-section">
            <h2 class="section-title">Your Order</h2>

            <!-- Item 1 -->
					    
					    
	 <%
					      
	    if (cart != null && !cart.getItems().isEmpty()) {
	
	        for (CartItem item : cart.getItems().values()) {
	
	            grandTotal = grandTotal + item.getTotalPrice();
		%>
					
					<div class="cart-item">
					
					    <div class="item-info">
					    
					    
							    <img src="<%= item.getImagePath() %>"
							         alt="<%= item.getName() %>"
							         class="item-image">
					
					        <div class="item-details">
					        
					         
					            <h4><%= item.getName() %></h4>
					            <div class="item-price"> ₹<%= item.getPrice() %> </div>
					        </div>
					
					    </div>
					
					
					    <div class="item-actions">
					
					        <div class="quantity-control">
					
					            <!-- DECREASE -->
					            <form action="callCartServlet" method="post">
					
					                <input type="hidden"
					                       name="menuID"
					                       value="<%= item.getMenuID() %>">
					
					                <input type="hidden"
					                       name="restaurantID"
					                       value="<%= item.getRestaurantID() %>">
					
					                <input type="hidden"
					                       name="action"
					                       value="<%= item.getQuantity() - 1 <= 0 ? "delete" : "update" %>">
					
					                <input type="hidden"
					                       name="quantity"
					                       value="<%= item.getQuantity() - 1 %>">
					
					                <button class="quantity-btn" type="submit">−</button>
					
					            </form>
					
					
					            <!-- QUANTITY -->
					            <div class="quantity-display">
					                <%= item.getQuantity() %>
					            </div>
					
					
					            <!-- INCREASE -->
					            <form action="callCartServlet" method="post">
					
					                <input type="hidden"
					                       name="menuID"
					                       value="<%= item.getMenuID() %>">
					
					                <input type="hidden"
					                       name="restaurantID"
					                       value="<%= item.getRestaurantID() %>">
					
					                <input type="hidden"
					                       name="action"
					                       value="update">
					
					                <input type="hidden"
					                       name="quantity"
					                       value="<%= item.getQuantity() + 1 %>">
					
					                <button class="quantity-btn" type="submit">+</button>
					
					            </form>
					
					        </div>
					
					
					        <!-- REMOVE -->
					        <form action="callCartServlet" method="post">
					
					            <input type="hidden"
					                   name="menuID"
					                   value="<%= item.getMenuID() %>">
					
					            <input type="hidden"
					                   name="restaurantID"
					                   value="<%= item.getRestaurantID() %>">
					
					            <input type="hidden"
					                   name="action"
					                   value="delete">
					
					            <button class="remove-btn" type="submit">
					                Remove
					            </button>
					
					        </form>
					
					    </div>
					
					</div>
					
					<%
					        }
					    } else {
					%>
					
					<div class="empty-cart">
					
					    <h2>Your cart is empty</h2>
					
					    <p>Please add some food items from the menu.</p>
					
					    <a class="checkout-btn" href="RestaurantServlet">
					        Browse Restaurants
					    </a>
					
					</div>
					
					<%
					    }
					%>
		
			
			<%
			    double deliveryFee = 40.00;
			    double tax = grandTotal * 0.05;
			    double finalTotal = grandTotal + deliveryFee + tax;
			%>
			
							<!-- Add More Items Button -->
				<div class="add-more-container">
				    <a href="<%= request.getContextPath() %>/MenuServlet?restaurantID=<%= restaurantID %>"
				       class="add-more-btn">
				        + Add More Items
		 		    </a>
				</div>
							
			
			<div class="bill-row total">
			
			    <span>To Pay</span>
			
			    <span class="value">₹<%= finalTotal %></span>		
			</div>


            <!-- Checkout Button -->
            <a href="checkout.jsp" class="checkout">  PROCEED TO PAY</a>
  
        </div>
    </div>

</body>
</html>