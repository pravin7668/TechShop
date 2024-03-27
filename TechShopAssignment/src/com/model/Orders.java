package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Orders {
	@Override
	public int hashCode() {
		return Objects.hash(customerId, id, orderDate, totalAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return customerId == other.customerId && id == other.id && Objects.equals(orderDate, other.orderDate)
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
	}

	private int id;
	private LocalDate orderDate;
	private double totalAmount;
	private int customerId;

	public Orders() {
	}

	public Orders(LocalDate orderDate, double totalAmount, int customerId) {
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.customerId = customerId;
	}

	public Orders(int id, LocalDate orderDate, double totalAmount, int customerId) {
		this.id = id;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", customerId="
				+ customerId + "]";
	}

}
