package com.ecommerce.entities;

import java.sql.Date;

public class OrderModel extends ProductModel {
	private int orderId;
	private int productId;
	private int userId;
	private int orderQuantity;
	private String orderDate;
	
	public OrderModel() {
		
	}
	
	
	
	
	public OrderModel(int productId, int userId, int orderQuantity, String orderDate) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}




	public OrderModel(int orderId, int productId, int userId, int orderQuantity, String orderDate) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}




	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	

	

}
