package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Inventory {
	@Override
	public int hashCode() {
		return Objects.hash(id, lastStockUpdate, productId, quantityInStock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return id == other.id && Objects.equals(lastStockUpdate, other.lastStockUpdate) && productId == other.productId
				&& quantityInStock == other.quantityInStock;
	}

	private int id;
	private int quantityInStock;
	private LocalDate lastStockUpdate;
	private int productId;

	public Inventory() {
	}

	public Inventory(int quantityInStock, LocalDate lastStockUpdate, int productId) {
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
		this.productId = productId;
	}

	public Inventory(int id, int quantityInStock, LocalDate lastStockUpdate, int productId) {
		this.id = id;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", quantityInStock=" + quantityInStock + ", lastStockUpdate=" + lastStockUpdate
				+ ", productId=" + productId + "]";
	}

}
