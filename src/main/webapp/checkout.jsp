<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.tap.model.Cart" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="java.util.Map" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");

    Integer restaurantID =
            (Integer) session.getAttribute("restaurantID");

    double grandTotal = 0;

    if (cart != null && !cart.getItems().isEmpty()) {
        for (CartItem item : cart.getItems().values()) {
            grandTotal += item.getTotalPrice();
        }
    }

    double deliveryFee = 40.0;
    double platformFee = 5.0;
    double finalTotal = grandTotal + deliveryFee + platformFee;
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>FoodRush - Checkout</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background: #0f0f0f;
            color: white;
            min-height: 100vh;
        }

        .navbar {
            height: 70px;
            background: #111111;
            border-bottom: 1px solid #292929;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 7%;
        }

        .brand {
            font-size: 26px;
            font-weight: bold;
            color: #ff4d4d;
        }

        .back-link {
            text-decoration: none;
            color: white;
            font-size: 15px;
        }

        .back-link:hover {
            color: #ff4d4d;
        }

        .checkout-wrapper {
            width: 90%;
            max-width: 1200px;
            margin: 40px auto;
            display: grid;
            grid-template-columns: 1.5fr 1fr;
            gap: 30px;
        }

        .section {
            background: #171717;
            border: 1px solid #292929;
            border-radius: 15px;
            padding: 25px;
        }

        .section-title {
            font-size: 24px;
            margin-bottom: 25px;
        }

        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 18px 0;
            border-bottom: 1px solid #292929;
        }

        .item-details h3 {
            font-size: 17px;
            margin-bottom: 8px;
        }

        .item-details p {
            color: #aaaaaa;
            font-size: 14px;
        }

        .item-price {
            text-align: right;
        }

        .item-price .price {
            color: #ff9f43;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .item-price .quantity {
            color: #aaaaaa;
            font-size: 14px;
        }

        .customer-form {
            margin-top: 30px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #dddddd;
            font-size: 14px;
        }

        .form-group input,
        .form-group textarea,
        .form-group select {
            width: 100%;
            padding: 13px;
            background: #101010;
            border: 1px solid #333333;
            border-radius: 8px;
            color: white;
            outline: none;
        }

        .form-group textarea {
            height: 90px;
            resize: none;
        }

        .form-group input:focus,
        .form-group textarea:focus,
        .form-group select:focus {
            border-color: #ff4d4d;
        }

        .bill-section {
            height: fit-content;
            position: sticky;
            top: 30px;
        }

        .bill-row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            color: #bbbbbb;
        }

        .bill-row .value {
            color: white;
        }

        .total-row {
            border-top: 1px solid #333333;
            margin-top: 10px;
            padding-top: 18px;
            font-size: 19px;
            font-weight: bold;
        }

        .total-row .value {
            color: #ff9f43;
        }

        .payment-title {
            margin-top: 25px;
            margin-bottom: 15px;
            font-size: 17px;
        }

        .paymentMode {
            display: block;
            background: #101010;
            border: 1px solid #333333;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 10px;
            cursor: pointer;
        }

        .paymentMode input {
            margin-right: 10px;
        }

        .checkout-btn {
            width: 100%;
            margin-top: 25px;
            padding: 15px;
            border: none;
            border-radius: 10px;
            background: linear-gradient(
                135deg,
                #ff4d4d,
                #ff9f43,
                #feca57
            );
            color: #111111;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        .checkout-btn:hover {
            transform: scale(1.02);
        }

        .empty-message {
            text-align: center;
            padding: 40px;
            color: #aaaaaa;
        }

        @media(max-width: 850px) {

            .checkout-wrapper {
                grid-template-columns: 1fr;
            }

            .bill-section {
                position: static;
            }
        }

    </style>

</head>

<body>

    <!-- NAVBAR -->

    <div class="navbar">

        <div class="brand">
            FOODRUSH
        </div>

        <a href="cart.jsp" class="back-link">
            ← Back to Cart
        </a>

    </div>


    <div class="checkout-wrapper">


        <!-- LEFT SIDE -->

        <div class="section">

            <h2 class="section-title">
                Order Summary
            </h2>


            <%
                if (cart != null && !cart.getItems().isEmpty()) {

                    for (CartItem item : cart.getItems().values()) {
            %>


            <div class="cart-item">

                <div class="item-details">

                    <h3>
                        <%= item.getName() %>
                    </h3>

                    <p>
                        ₹<%= item.getPrice() %>
                    </p>

                </div>


                <div class="item-price">

                    <div class="price">
                        ₹<%= item.getTotalPrice() %>
                    </div>

                    <div class="quantity">
                        Quantity: <%= item.getQuantity() %>
                    </div>

                </div>

            </div>


            <%
                    }

                } else {
            %>

                <div class="empty-message">
                    Your cart is empty.
                </div>

            <%
                }
            %>


            <!-- CUSTOMER DETAILS -->

            <div class="customer-form">

                <h2 class="section-title">
                    Delivery Details
                </h2>


                <form action="checkout" method="post">


                    <div class="form-group">

                        <label>
                            Name
                        </label>

                        <input
                            type="text"
                            name="name"
                            placeholder="Enter your name"
                            required>

                    </div>


                    <div class="form-group">

                        <label>
                            Mobile Number
                        </label>

                        <input
                            type="tel"
                            name="mobile"
                            placeholder="Enter mobile number"
                            required>

                    </div>


                    <div class="form-group">

                        <label>
                            Delivery Address
                        </label>

                        <textarea
                            name="address"
                            placeholder="Enter your delivery address"
                            required></textarea>

                    </div>

                  <% session.setAttribute("finalTotal", finalTotal); %>
                  
                    <input
                        type="hidden"
                        name="restaurantID"
                        value="<%= restaurantID %>">


                    <input
                        type="hidden"
                        name="totalAmount"
                        value="<%= finalTotal %>">


                    <div class="payment-title">
                        Payment Method
                    </div>


                    <label class="paymentMode">

                        <input
                            type="radio"
                            name="paymentMethod"
                            value="COD"
                            checked>

                        Cash on Delivery

                    </label>


                    <label class="paymentMode">

                        <input
                            type="radio"
                            name="paymentMethod"
                            value="UPI">

                        UPI

                    </label>


                    <label class="paymentMode">

                        <input
                            type="radio"
                            name="paymentMethod"
                            value="CARD">

                        Card

                    </label>


                    <button
                        type="submit"
                        class="checkout-btn">

                        PLACE ORDER ₹<%= finalTotal %>

                    </button>


                </form>

            </div>

        </div>


        <!-- RIGHT SIDE BILL -->

        <div class="section bill-section">

            <h2 class="section-title">
                Bill Details
            </h2>


            <div class="bill-row">

                <span>
                    Item Total
                </span>

                <span class="value">
                    ₹<%= grandTotal %>
                </span>

            </div>


            <div class="bill-row">

                <span>
                    Delivery Fee
                </span>

                <span class="value">
                    ₹<%= deliveryFee %>
                </span>

            </div>


            <div class="bill-row">

                <span>
                    Platform Fee
                </span>

                <span class="value">
                    ₹<%= platformFee %>
                </span>

            </div>


            <div class="bill-row total-row">

                <span>
                    Total
                </span>

                <span class="value">
                    ₹<%= finalTotal %>
                </span>

            </div>

        </div>

    </div>

</body>
</html>