package com.ecommerce.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.ecommerce.entities.OrderModel;
import com.ecommerce.entities.ProductModel;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet  rs;
	public OrderDao(Connection con) {
		this.con = con;
	}
	public boolean  insertOrder(OrderModel model) {
		boolean result = false;
	try {	
		query = "insert into orders(orderId,productId, userId, order_quantity, order_date) values(?,?, ?, ?, ?)";
	    pst = this.con.prepareStatement(query);
	    pst.setInt(1, model.getOrderId());
	    pst.setInt(2, model.getProductId());
	    pst.setInt(3, model.getUserId());
	    pst.setInt(4, model.getOrderQuantity());
	    pst.setString(5, model.getOrderDate());
	    System.out.println("Order Quantity: " + model.getOrderQuantity());
	    
	    int rowAffected = pst.executeUpdate();
	    result = (rowAffected > 0);
	    result = true;
	}catch(Exception e) {
	    e.printStackTrace(); // To get more details about the exception
	}
	return result;
}
	public List<OrderModel> userOrder(int id){
		List<OrderModel> list = new ArrayList<>();
		try {
			query = "select * from orders where userId = ? order by orders.orderId desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderModel order = new OrderModel();
				ProductDao pDao = new ProductDao(this.con);
				int pId = rs.getInt("productId");
				
				
				
				ProductModel product = pDao.getSingleProduct(pId);
				
				if(product != null) {
					order.setOrderId(rs.getInt("orderId"));
					order.setUserId(pId);
					order.setName(product.getName());
					order.setCategory(product.getCategory());
					order.setPrice(product.getPrice()*rs.getInt("order_quantity"));
					order.setOrderQuantity(rs.getInt("order_quantity"));
					order.setOrderDate(rs.getString("order_date"));
					list.add(order);
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where orderid = ?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
