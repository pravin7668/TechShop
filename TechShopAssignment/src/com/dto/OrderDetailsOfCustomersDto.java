package com.dto;

import java.util.Objects;

public class OrderDetailsOfCustomersDto {
	private int orderId;
	private String productName;
	private int productId;
	private double price;
	private double totalAmount;
	private int quantity;
	public OrderDetailsOfCustomersDto() {
		super();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId, price, productId, productName, quantity, totalAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsOfCustomersDto other = (OrderDetailsOfCustomersDto) obj;
		return orderId == other.orderId && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& productId == other.productId && Objects.equals(productName, other.productName)
				&& quantity == other.quantity
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
	}

	public OrderDetailsOfCustomersDto(int orderId, String productName, int productId, double price, double totalAmount,
			int quantity) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderDetailsOfCustomers [orderId=" + orderId + ", productName=" + productName + ", productId="
				+ productId + ", price=" + price + ", totalAmount=" + totalAmount + ", quantity=" + quantity + "]";
	}
	
	
}
