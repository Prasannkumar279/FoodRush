<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodRush | Premium Dining</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        /* CSS Reset & Variables */
        :root {
            --bg-dark: #050505;
            --card-bg: #141414;
            --card-hover: #1f1f1f;
            --accent-color: #f59e0b;
            --text-main: #f3f4f6;
            --text-muted: #9ca3af;
            --success: #10b981;
            --danger: #ef4444;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Montserrat', sans-serif;
        }

        body {
            background-color: var(--bg-dark);
            color: var(--text-main);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* ================= NAVBAR ================= */
        nav {
            position: sticky;
            top: 0;
            width: 100%;
            background: rgba(5, 5, 5, 0.85);
            backdrop-filter: blur(12px);
            -webkit-backdrop-filter: blur(12px);
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 5%;
            z-index: 1000;
            border-bottom: 1px solid #222;
        }

        .logo {
            font-size: 1.6rem;
            font-weight: 800;
            color: var(--accent-color);
            display: flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
            letter-spacing: -0.5px;
        }

        .logo svg {
            width: 26px;
            height: 26px;
            fill: var(--accent-color);
        }

        .nav-links {
            display: flex;
            gap: 30px;
            list-style: none;
        }

        .nav-links li a {
            color: var(--text-main);
            text-decoration: none;
            font-weight: 500;
            font-size: 0.95rem;
            transition: color 0.3s ease;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .nav-links li a:hover {
            color: var(--accent-color);
        }
        
        .nav-links svg {
            width: 18px;
            height: 18px;
            fill: currentColor;
        }

        /* Header Section */
        header {
            width: 100%;
            padding: 60px 20px 40px;
            text-align: center;
            background: radial-gradient(circle at top, #1a1a1a, var(--bg-dark));
            border-bottom: 1px solid #222;
        }

        header h1 {
            font-size: 3rem;
            font-weight: 800;
            letter-spacing: -1px;
            margin-bottom: 15px;
            background: linear-gradient(90deg, #fff, #a3a3a3);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        header p {
            color: var(--text-muted);
            font-size: 1.1rem;
            max-width: 600px;
            margin: 0 auto;
        }

        /* Grid Container */
        .container {
            width: 100%;
            max-width: 1300px;
            padding: 50px 20px;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
            gap: 35px;
        }

        /* Restaurant Card */
        .card {
            background-color: var(--card-bg);
            border-radius: 20px;
            overflow: hidden;
            border: 1px solid #2a2a2a;
            transition: all 0.4s ease;
            position: relative;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
            display: flex;
            flex-direction: column;
        }

        .card:hover {
            transform: translateY(-8px);
            background-color: var(--card-hover);
            border-color: #404040;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.7), 0 0 15px rgba(245, 158, 11, 0.1);
        }

        /* Image Wrapper */
        .image-wrapper {
            position: relative;
            height: 240px;
            width: 100%;
            overflow: hidden;
        }

        .image-wrapper img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.5s ease;
        }

        .card:hover .image-wrapper img {
            transform: scale(1.08);
        }

        /* Overlays & Badges */
        .overlay-top {
            position: absolute;
            top: 15px;
            left: 15px;
            right: 15px;
            display: flex;
            justify-content: space-between;
            z-index: 2;
        }

        .badge-status {
            padding: 6px 14px;
            border-radius: 30px;
            font-size: 0.8rem;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            backdrop-filter: blur(8px);
            display: flex;
            align-items: center;
            gap: 6px;
        }

        .status-active {
            background: rgba(16, 185, 129, 0.2);
            color: var(--success);
            border: 1px solid rgba(16, 185, 129, 0.4);
        }

        .status-active::before {
            content: "";
            width: 8px;
            height: 8px;
            background-color: var(--success);
            border-radius: 50%;
            box-shadow: 0 0 8px var(--success);
        }

        .status-inactive {
            background: rgba(239, 68, 68, 0.2);
            color: var(--danger);
            border: 1px solid rgba(239, 68, 68, 0.4);
        }

        .status-inactive::before {
            content: "";
            width: 8px;
            height: 8px;
            background-color: var(--danger);
            border-radius: 50%;
        }

        .badge-time {
            background: rgba(0, 0, 0, 0.75);
            color: white;
            padding: 6px 14px;
            border-radius: 30px;
            font-size: 0.85rem;
            font-weight: 600;
            backdrop-filter: blur(5px);
            border: 1px solid #333;
        }

        /* Card Content */
        .card-content {
            padding: 25px 20px;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }

        .title-row {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 8px;
            gap: 15px;
        }

        .rest-name {
            font-size: 1.4rem;
            font-weight: 700;
            color: #ffffff;
            line-height: 1.3;
        }

        .rating-badge {
            background: var(--accent-color);
            color: #000;
            padding: 4px 10px;
            border-radius: 8px;
            font-weight: 800;
            font-size: 0.95rem;
            display: flex;
            align-items: center;
            gap: 4px;
            flex-shrink: 0;
        }

        .cuisine {
            color: var(--accent-color);
            font-size: 0.9rem;
            font-weight: 500;
            margin-bottom: 20px;
            letter-spacing: 0.5px;
            text-transform: uppercase;
        }

        .address-box {
            margin-top: auto;
            padding-top: 15px;
            border-top: 1px solid #2a2a2a;
            display: flex;
            align-items: flex-start;
            gap: 10px;
            color: var(--text-muted);
            font-size: 0.9rem;
            line-height: 1.5;
        }
        
        .address-box svg {
            width: 18px;
            height: 18px;
            fill: var(--text-muted);
            flex-shrink: 0;
            margin-top: 2px;
        }
        
        .address-box svg {
	    width: 18px;
	    height: 18px;
	    fill: var(--text-muted);
	    flex-shrink: 0;
	    margin-top: 2px;
	}
	
	
	          .search-form {
			    margin: 0;
			    padding: 0;
			}
			
			.search-box {
			    height: 44px;
			    width: 360px;
			    display: flex;
			    align-items: center;
			    background: #111;
			    border: 1px solid #333;
			    border-radius: 25px;
			    overflow: hidden;
			    transition: 0.3s ease;
			}
			
			.search-box:focus-within {
			    border-color: #ff4d4d;
			    box-shadow: 0 0 0 3px rgba(255, 77, 77, 0.12);
			}
			
			.search-box svg {
			    width: 21px;
			    height: 21px;
			    margin-left: 16px;
			    margin-right: 10px;
			    fill: #aaa;
			    flex-shrink: 0;
			}
			
			.search-box input {
			    flex: 1;
			    height: 100%;
			    border: none;
			    outline: none;
			    background: transparent;
			    color: #fff;
			    font-size: 14px;
			    padding: 0 8px;
			}
			
			.search-box input::placeholder {
			    color: #888;
			}
			
			.search-box button {
			    height: 100%;
			    border: none;
			    padding: 0 20px;
			    background: linear-gradient(135deg, #ff4d4d, #ff9f43);
			    color: #111;
			    font-size: 14px;
			    font-weight: 700;
			    cursor: pointer;
			    transition: 0.3s ease;
			}
			
			.search-box button:hover {
			    background: linear-gradient(135deg, #ff3838, #ffb347);
			}

/* ===== View Restaurant Button ===== */
		
		.view-btn{
		    margin-top:20px;
		}
		
		.view-btn a{
		    display:block;
		    width:100%;
		    text-align:center;
		    text-decoration:none;
		    background:linear-gradient(135deg,#ff6b00,#ff3d00);
		    color:#fff;
		    padding:12px 0;
		    border-radius:12px;
		    font-weight:700;
		    font-size:15px;
		    transition:0.3s;
		    box-shadow:0 8px 20px rgba(255,80,0,.3);
		}
		
		.view-btn a:hover{
		    background:linear-gradient(135deg,#ff8c00,#ff4500);
		    transform:translateY(-2px);
		    box-shadow:0 12px 25px rgba(255,80,0,.45);
		}


        /* Responsive Navbar */
        @media (max-width: 768px) {
            .nav-links a span {
                display: none;
            }
        }

    </style>
</head>
<body>

    <!-- ================= ADDED NAVBAR ================= -->
    <nav>
        <a href="MenuServlet" class="logo">
            <svg viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1 17.93c-3.95-.49-7-3.85-7-7.93 0-.62.08-1.21.21-1.79L9 15v1c0 1.1.9 2 2 2v1.93zm6.9-2.54c-.26-.81-1-1.39-1.9-1.39h-1v-3c0-.55-.45-1-1-1H8v-2h2c.55 0 1-.45 1-1V7h2c1.1 0 2-.9 2-2v-.41c2.93 1.19 5 4.06 5 7.41 0 2.08-.8 3.97-2.1 5.39z"/></svg>
            FoodRush
        </a>
        <ul class="nav-links">
        
       <li>
            
         <form action="RestaurantServlet" method="get" class="search-form">
                     <div class="search-box">
				        <svg viewBox="0 0 24 24">
				            <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5
				            16 5.91 13.09 3 9.5 3S3 5.91 3 9.5
				            5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57
				            l.27.28v.79l5 4.99L20.49 19l-4.99-5z
				            M9.5 14C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5
				            14 7.01 14 9.5 11.99 14 9.5 14z"/>
				        </svg>
				
				        <input type="text"
				               name="search"
				               placeholder="Search restaurants..."
				               required>
				
				        <button type="submit">Search</button>
                      </div>
           </form>
         </li>
            <li><a href="registration.html">
                <svg viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
                <span>Sign In</span>
            </a></li>
            <li><a href="cart.jsp">
                <svg viewBox="0 0 24 24"><path d="M7 18c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2-.9-2-2-2zM1 2v2h2l3.6 7.59-1.35 2.45c-.16.28-.25.61-.25.96 0 1.1.9 2 2 2h12v-2H7.42c-.14 0-.25-.11-.25-.25l.03-.12.9-1.63h7.45c.75 0 1.41-.41 1.75-1.03l3.58-6.49c.08-.14.12-.31.12-.48 0-.55-.45-1-1-1H5.21l-.94-2H1zm16 16c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2 2-.9 2-2-.9-2-2-2z"/></svg>
                <span>Cart</span>
            </a></li>         
			   <li>	<a href="login.html">
				   <svg viewBox="0 0 24 24">
				       <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
				   </svg>
				      <span>Login</span>
				    </a></li>
				            
            
            
        </ul>
    </nav>
    <!-- ================= END NAVBAR ================= -->

    <header>
        <h1>For Foodie Feaks</h1>
        <p>Enjoy the Food with Loving and Sharing</p>
    </header>

    <div class="container">
    
       <% 
      
  	List<restaurant> restaurantList  = (List<restaurant>)request.getAttribute("allrestaurant");
	for (restaurant r : restaurantList) {
		
		%>
         <!-- Restaurant Card 1 -->
	        <div class="card">
	            <div class="image-wrapper">
	                <img src="ImageServlet?id=<%= r.getRestaurantID() %>" alt="Restaurant">
	                <div class="overlay-top">
	                    <!-- Attribute: isActive = true -->
	                    <span class="badge-status status-active">Open now</span>
	                    <!-- Attribute: deliveryTime -->
	                    <span class="badge-time"><%=r.getDeliveryTime()%> Minutes</span>
	                </div>
	            </div>
	            <div class="card-content">
	                <div class="title-row">
	                    <!-- Attribute: restName -->
	                    <h2 class="rest-name"><%=r.getRestName() %></h2>
	                    <!-- Attribute: rating -->
	                    <span class="rating-badge"><%=r.getRating() %></span>
	                </div>
	                <!-- Attribute: cuisineType -->
	                <div class="cuisine"><%=r.getCuisineType() %></div>
	                
	                <!-- Attribute: address -->
	                <div class="address-box">
	                    <svg viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
	                    <span><%=r.getAddress() %></span>
	                </div>
	                
					<div class="view-btn">
					    <a href="MenuServlet?restaurantID=<%=r.getRestaurantID()%>">
					        View Restaurant
					    </a>
				</div>
	           </div>
	        </div>
   
	<%  
	
	   }
		
   %>
 
    	        <!-- Restaurant Card 2 -->
	     <!--    <div class="card">
	            <div class="image-wrapper">
	                <img src="https://images.unsplash.com/photo-1553621042-f6e147245754?auto=format&fit=crop&w=800&q=80" alt="Sushi">
	                <div class="overlay-top">
	                    Attribute: isActive = true
	                    <span class="badge-status status-active">Open Now</span>
	                    Attribute: deliveryTime
	                    <span class="badge-time">⏱ 45 mins</span>
	                </div>
	            </div>
	            <div class="card-content">
	                <div class="title-row">
	                    Attribute: restName
	                    <h2 class="rest-name">Midnight Sushi</h2>
	                    Attribute: rating
	                    <span class="rating-badge">★ 4.9</span>
	                </div>
	                Attribute: cuisineType
	                <div class="cuisine">Japanese • Seafood • Sake</div>
	                
	                Attribute: address
	                <div class="address-box">
	                    <svg viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
	                    <span>42 Neon Alley, Indiranagar, Bengaluru</span>
	                </div>
	            </div>
	        </div>
          </div>
 -->
      
        <!-- Restaurant Card 3 -->
	     <!--    <div class="card">
	            <div class="image-wrapper">
	                <img src="https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=800&q=80" alt="Pizza">
	                <div class="overlay-top">
	                    Attribute: isActive = false
	                    <span class="badge-status status-inactive">Closed</span>
	                    Attribute: deliveryTime
	                    <span class="badge-time">⏱ 35 mins</span>
	                </div>
	            </div>
	            <div class="card-content">
	                <div class="title-row">
	                    Attribute: restName
	                    <h2 class="rest-name">The Rusty Oven</h2>
	                    Attribute: rating
	                    <span class="rating-badge">★ 4.3</span>
	                </div>
	                Attribute: cuisineType
	                <div class="cuisine">Italian • Wood-fired Pizza</div>
	                
	                Attribute: address
	                <div class="address-box">
	                    <svg viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
	                    <span>Old Town District, 4th Ave, Koramangala</span>
	                </div>
	            </div>
	        </div>
	        
	        
        Restaurant Card 4
        <div class="card">
            <div class="image-wrapper">
                <img src="https://images.unsplash.com/photo-1585937421612-70a008356fbe?auto=format&fit=crop&w=800&q=80" alt="Curry">
                <div class="overlay-top">
                    Attribute: isActive = true
                    <span class="badge-status status-active">Open Now</span>
                    Attribute: deliveryTime
                    <span class="badge-time">⏱ 50 mins</span>
                </div>
            </div>
            <div class="card-content">
                <div class="title-row">
                    Attribute: restName
                    <h2 class="rest-name">Spice Eclipse</h2>
                    Attribute: rating
                    <span class="rating-badge">★ 4.7</span>
                </div>
                Attribute: cuisineType
                <div class="cuisine">North Indian • Mughlai</div>
                
                Attribute: address
                <div class="address-box">
                    <svg viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
                    <span>88 Horizon Blvd, Whitefield, Bengaluru</span>
                </div>
            </div>
        </div>

        Restaurant Card 5
        <div class="card">
            <div class="image-wrapper">
                <img src="https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=800&q=80" alt="Tacos">
                <div class="overlay-top">
                    Attribute: isActive = true
                    <span class="badge-status status-active">Open Now</span>
                    Attribute: deliveryTime
                    <span class="badge-time">⏱ 20 mins</span>
                </div>
            </div>
            <div class="card-content">
                <div class="title-row">
                    Attribute: restName
                    <h2 class="rest-name">Taco Cosmos</h2>
                    Attribute: rating
                    <span class="rating-badge">★ 4.6</span>
                </div>
                Attribute: cuisineType
                <div class="cuisine">Mexican • Street Food</div>
                
                Attribute: address
                <div class="address-box">
                    <svg viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
                    <span>Starlight Plaza, East Wing, Malleshwaram</span>
                </div>
            </div>
        </div>
      
 -->
    

</body>
</html>