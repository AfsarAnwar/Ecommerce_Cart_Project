package com.ecommerce.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url = "jdbc:mysql://localhost:3306/ecommerce_cart";
	private static String user = "root";
	private static String password = "root123";
	
	private static Connection con = null;
	 public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url, user, password);
			 System.out.println("connected");
		} 
		 
		 return con;
		 
	 }
}
