package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.ecommerce.entities.CartModel;
import com.ecommerce.entities.ProductModel;
public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		
		this.con = con;
	}
	
	public List<ProductModel> getAllProduct(){
		List<ProductModel> products = new ArrayList<ProductModel>();
		try {
				query = "select * from products";
				pst = this.con.prepareStatement(query);
				rs = pst.executeQuery();
				while (rs.next()) {
				    ProductModel row = new ProductModel();
				    row.setProductId(rs.getInt("productId"));
				    row.setName(rs.getString("name"));
				    row.setCategory(rs.getString("category"));
				    row.setPrice(rs.getDouble("price"));
				    row.setImage(rs.getString("image"));
				    products.add(row);  
				}
					
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return products;
		
	}
	
	public List<CartModel> getCartProduct(ArrayList<CartModel> cartList){
		List<CartModel> products = new ArrayList<CartModel>();
		try {
			if(cartList.size() > 0) {
				for(CartModel item : cartList) {
					query = "select * from products where productId = ?";
					pst = this.con.prepareStatement(query);
				    pst.setInt(1, item.getProductId());
				    rs = pst.executeQuery();
				    while(rs.next()) {
				    	CartModel row = new CartModel();
				    	row.setProductId(rs.getInt("productId"));
				    	row.setName(rs.getString("name"));
				    	row.setCategory(rs.getString("category"));
				    	row.setCategory(rs.getString("category"));
				    	row.setPrice(rs.getDouble("price")*item.getQuantity());
				    	row.setQuantity(item.getQuantity());
				    	products.add(row);
				    	
				    }
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<CartModel> cartList) {
		double sum = 0;
		try {
			 if(cartList.size() > 0) {
				 for(CartModel item : cartList) {
					  query = "select price from products where productId = ?";
					  pst = this.con.prepareStatement(query);
					  pst.setInt(1, item.getProductId());
					  rs = pst.executeQuery();
					  while(rs.next()) {
						  sum +=  rs.getDouble("price")*item.getQuantity();
						  
					  }
				 }
			 }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return sum;
		
	}

	public ProductModel getSingleProduct(int id) {
		ProductModel row = null;
		try {
			  query = "select * from products where productId = ?";
			  pst = this.con.prepareStatement(query);
			  pst.setInt(1, id);
			  rs = pst.executeQuery();
			  while(rs.next()) {
				  row = new ProductModel();
				  row.setProductId(rs.getInt("productId"));
				  row.setName(rs.getString("name"));
				  row.setCategory(rs.getString("category"));
				  row.setPrice(rs.getDouble("price"));
				  row.setImage(rs.getString("image"));
				  
				  
			  }
			  
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return row;
		
	}
	
}
