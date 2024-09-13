package com.ecommerce.entities;

public class ProductModel {
	private int productId;
	private String name;
	private String category;
	private double price;
	private String image;
	
	public ProductModel() {
		
	}

	public ProductModel(int productId, String name, String category, double price, String image) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.image = image;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", image=" + image + "]";
	}
	
	

}
