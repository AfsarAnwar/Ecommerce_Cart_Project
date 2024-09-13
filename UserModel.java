package com.ecommerce.entities;

public class UserModel {
	private int userId;
	private String name;
	private String userEmail;
	private String password;
	
	
	
	public UserModel() {
		
	}
	
	public UserModel(int userId, String name, String userEmail, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.userEmail = userEmail;
		this.password = password;
	}
	



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	
	
	




	

}
