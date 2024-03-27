package com.model;

import java.util.Objects;

public class Product {
	@Override
	public int hashCode() {
		return Objects.hash(description, id, productName, productPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(productName, other.productName)
				&& Double.doubleToLongBits(productPrice) == Double.doubleToLongBits(other.productPrice);
	}

	private int id;
	private String productName;
	private String description;
	private double productPrice;

	public Product() {
	}

	public Product(String productName, String description, double productPrice) {
		this.productName = productName;
		this.description = description;
		this.productPrice = productPrice;
	}

	public Product(int id, String productName, String description, double productPrice) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.productPrice = productPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", productPrice="
				+ productPrice + "]";
	}

}
