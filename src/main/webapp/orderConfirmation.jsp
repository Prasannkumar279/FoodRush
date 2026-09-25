<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    Integer orderID = (Integer) session.getAttribute("orderID");
    Double finalTotal = (Double) session.getAttribute("finalTotal");

    if (orderID == null) {
        orderID = 0;
    }

    if (finalTotal == null) {
        finalTotal = 0.0;
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Order Confirmed - FoodRush</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #0b0b0b;
            color: #ffffff;
            min-height: 100vh;
        }

        /* ================= HEADER ================= */

        .header {
            height: 90px;
            border-bottom: 1px solid #292929;

            display: flex;
            align-items: center;
            justify-content: space-between;

            padding: 0 7%;
        }

        .logo {
            font-size: 32px;
            font-weight: 800;

            background: linear-gradient(
                90deg,
                #ff4b4b,
                #ff9f43,
                #feca57
            );

            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .back-cart {
            color: #d6d6d6;
            text-decoration: none;
            font-size: 16px;

            transition: 0.3s;
        }

        .back-cart:hover {
            color: #ff5a4f;
        }

        /* ================= MAIN ================= */

        .container {
            width: 90%;
            max-width: 900px;

            margin: 70px auto;
        }

        /* ================= SUCCESS CARD ================= */

        .success-card {
            background: #151515;

            border: 1px solid #292929;
            border-radius: 20px;

            padding: 60px 50px;

            text-align: center;

            box-shadow:
                0 20px 60px rgba(0, 0, 0, 0.5);
        }

        /* ================= SUCCESS ICON ================= */

        .success-icon {
            width: 90px;
            height: 90px;

            margin: 0 auto 25px;

            border-radius: 50%;

            display: flex;
            align-items: center;
            justify-content: center;

            background: linear-gradient(
                135deg,
                #ff4b4b,
                #ff9f43
            );

            font-size: 48px;

            box-shadow:
                0 0 30px rgba(255, 75, 75, 0.25);
        }

        /* ================= TEXT ================= */

        .success-card h1 {
            font-size: 36px;
            margin-bottom: 12px;
        }

        .success-card h1 span {
            background: linear-gradient(
                90deg,
                #ff4b4b,
                #ff9f43,
                #feca57
            );

            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .message {
            color: #a8a8a8;
            font-size: 17px;
            margin-bottom: 35px;
        }

        /* ================= ORDER DETAILS ================= */

        .order-details {
            width: 100%;

            background: #101010;

            border: 1px solid #292929;
            border-radius: 14px;

            padding: 25px 30px;

            margin-bottom: 35px;
        }

        .detail-row {
            display: flex;

            justify-content: space-between;
            align-items: center;

            padding: 16px 0;

            border-bottom: 1px solid #292929;
        }

        .detail-row:last-child {
            border-bottom: none;
        }

        .detail-label {
            color: #929292;
            font-size: 16px;
        }

        .detail-value {
            color: #ffffff;
            font-size: 17px;
            font-weight: 600;
        }

        .order-number {
            color: #ff6b5f;
        }

        .amount {
            color: #feca57;
            font-size: 20px;
        }

        /* ================= STATUS ================= */

        .status {
            display: inline-block;

            padding: 8px 18px;

            border-radius: 20px;

            background: rgba(34, 197, 94, 0.12);

            color: #4ade80;

            font-size: 14px;
            font-weight: 600;
        }

        /* ================= INFO ================= */

        .info {
            color: #8f8f8f;

            font-size: 14px;

            line-height: 1.6;

            margin-bottom: 30px;
        }

        /* ================= BUTTONS ================= */

        .buttons {
            display: flex;

            justify-content: center;

            gap: 15px;

            flex-wrap: wrap;
        }

        .btn {
            display: inline-block;

            padding: 15px 30px;

            border-radius: 10px;

            text-decoration: none;

            font-size: 16px;

            font-weight: 700;

            transition: 0.3s;
        }

        .btn-primary {
            background: linear-gradient(
                135deg,
                #ff4d4d,
                #ff914d,
                #feca57
            );

            color: #111;

            min-width: 190px;
        }

        .btn-primary:hover {
            transform: translateY(-2px);

            box-shadow:
                0 8px 25px rgba(255, 90, 70, 0.25);
        }

        .btn-secondary {
            background: transparent;

            border: 1px solid #3a3a3a;

            color: #ffffff;

            min-width: 190px;
        }

        .btn-secondary:hover {
            border-color: #ff4d4d;

            color: #ff6b5f;
        }

        /* ================= FOOTER ================= */

        .footer {
            text-align: center;

            color: #555;

            font-size: 13px;

            margin-top: 35px;
        }

        /* ================= RESPONSIVE ================= */

        @media (max-width: 600px) {

            .header {
                padding: 0 5%;
            }

            .logo {
                font-size: 26px;
            }

            .container {
                width: 94%;
                margin: 35px auto;
            }

            .success-card {
                padding: 40px 20px;
            }

            .success-card h1 {
                font-size: 28px;
            }

            .detail-row {
                gap: 15px;
            }

        }

    </style>
</head>

<body>

    <!-- ================= HEADER ================= -->

    <header class="header">

        <div class="logo">
            FOODRUSH
        </div>

        <a href="cart.jsp" class="back-cart">
            ← Back to Cart
        </a>

    </header>


    <!-- ================= MAIN ================= -->

    <main class="container">

        <div class="success-card">

            <!-- SUCCESS ICON -->

            <div class="success-icon">
                ✓
            </div>


            <!-- MESSAGE -->

            <h1>
                Order <span>Confirmed!</span>
            </h1>

            <p class="message">
                Your order has been placed successfully.
                Thank you for ordering with FoodRush!
            </p>


            <!-- ORDER DETAILS -->

            <div class="order-details">

                <div class="detail-row">

                    <span class="detail-label">
                        Order ID
                    </span>

                    <span class="detail-value order-number">
                        #<%= orderID %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Order Status
                    </span>

                    <span class="status">
                        Pending
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Total Amount
                    </span>

                    <span class="detail-value amount">
                        ₹<%= String.format("%.2f", finalTotal) %>
                    </span>

                </div>

            </div>


            <!-- INFO -->

            <p class="info">
                Your order has been received by the restaurant.
                You can continue exploring restaurants and dishes
                on FoodRush.
            </p>


            <!-- BUTTONS -->

            <div class="buttons">

                <a href="RestaurantServlet" class="btn btn-primary">
                    Order More Food
                </a>

                <a href="index.html" class="btn btn-secondary">
                    Back to Home
                </a>

            </div>

        </div>


        <div class="footer">
            © 2026 FoodRush. Delicious food, delivered fast.
        </div>

    </main>

</body>

</html>