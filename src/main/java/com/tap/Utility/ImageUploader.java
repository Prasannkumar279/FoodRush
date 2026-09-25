	package com.tap.Utility;

	import java.io.FileInputStream;
	import java.sql.Connection;
	import java.sql.PreparedStatement;

	public class ImageUploader {

	    public static void main(String[] args) {

	        String folder = "C:\\Users\\PRASANNAKUMAR\\eclipse-workspace\\FoodRush\\src\\main\\webapp\\images";

	        String[] images = {
	                "Udupi.png",
	                "Meghansa.webp",
	                "Vidhyarthi Bhavan.jpg.webp",
	                "Olives.jpg",
	                "rameshwaram-cafe.jpg",
	                "brik-oven-palace-road.jpg",
	                "Pizzabakery.jpg",
	                "SiNoona's.png",
	                "dominos.webp",
	                "Pizza Hut.jpg",
	                "MTR.jpg",
	                "CTR.jpg",
	                "ATR.jpg",
	                "Nagarjuna.jpg",
	                "Empire.jpg",
	                "Tuffles.avif",
	                "Udupi Shree Krishna Bhawan.jpg",
	                "KFCStore.jpg",
	                "BurgerKing.jpg"
	        };

	        int[] restaurantIds = {
	                1,
	                2,
	                3,
	                4,
	                5,
	                7,
	                8,
	                9,
	                10,
	                11,
	                12,
	                13,
	                14,
	                15,
	                16,
	                17,
	                18,
	                19,
	                20
	        };

	        try {

	            Connection con = DBConnection.getConnection();

	            String query = "UPDATE restaurant SET Image=? WHERE restaurantID=?";

	            PreparedStatement pstmt = con.prepareStatement(query);

	            for (int i = 0; i < images.length; i++) {

	            	FileInputStream fis =
	            	        new FileInputStream(folder + "\\" + images[i]);

	                pstmt.setBinaryStream(1, fis, fis.available());

	                pstmt.setInt(2, restaurantIds[i]);

	                pstmt.executeUpdate();

	                fis.close();
	            }

	            System.out.println("All Images Uploaded Successfully!");

	            pstmt.close();
	            con.close();

	        } catch (Exception e) {

	            e.printStackTrace();

	        }

	    }

	}
	
	
	

