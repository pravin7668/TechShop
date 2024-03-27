package com.dto;

import java.time.LocalDate;
import java.util.Objects;

public class InventoryProductDto {
	private int productId;
	private String productName;
	private double productPrice;
	private int quantityInStock;
	private LocalDate lastStockUpdate;
	
	@Override
	public int hashCode() {
		return Objects.hash(lastStockUpdate, productId, productName, productPrice, quantityInStock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryProductDto other = (InventoryProductDto) obj;
		return Objects.equals(lastStockUpdate, other.lastStockUpdate) && productId == other.productId
				&& Objects.equals(productName, other.productName)
				&& Double.doubleToLongBits(productPrice) == Double.doubleToLongBits(other.productPrice)
				&& quantityInStock == other.quantityInStock;
	}
	

	public InventoryProductDto(int productId, String productName, double productPrice, int quantityInStock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityInStock = quantityInStock;
	}

	public InventoryProductDto(int productId, String productName, double productPrice, int quantityInStock,
			LocalDate lastStockUpdate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}

	public InventoryProductDto() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public LocalDate getLastStockUpdate() {
		return lastStockUpdate;
	}

	public void setLastStockUpdate(LocalDate lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	@Override
	public String toString() {
		return "InventoryProductDto [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", quantityInStock=" + quantityInStock + ", lastStockUpdate=" + lastStockUpdate + "]";
	}

}
