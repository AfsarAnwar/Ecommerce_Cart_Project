package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.ecommerce.entities.UserModel;

public class UserDao {
	private static Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		this.con =con;
	}
	
	public UserModel userLogin(String userEmail , String Password) {
		UserModel user = null;
		try {
			query = "select * from users where userEmail = ? and password = ?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, userEmail);
			pst.setString(2, Password);
			rs=pst.executeQuery();
			if(rs.next()) {
				user = new UserModel();
				user.setUserId(rs.getInt("userId"));
				user.setName(rs.getString("name"));
				user.setUserEmail(rs.getString("userEmail"));
				
			}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			 }
		return  user;
		}
}
