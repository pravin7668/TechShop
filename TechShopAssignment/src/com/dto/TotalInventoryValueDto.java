package com.dto;

import java.util.Objects;

public class TotalInventoryValueDto {
@Override
	public int hashCode() {
		return Objects.hash(productName, totalValue);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TotalInventoryValueDto other = (TotalInventoryValueDto) obj;
		return Objects.equals(productName, other.productName)
				&& Double.doubleToLongBits(totalValue) == Double.doubleToLongBits(other.totalValue);
	}
private String productName;
private double totalValue;
public TotalInventoryValueDto() {
	super();
}
public TotalInventoryValueDto(String productName, double totalValue) {
	super();
	this.productName = productName;
	this.totalValue = totalValue;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
@Override
public String toString() {
	return "TotalInventoryValueDto [productName=" + productName + ", totalValue=" + totalValue + "]";
}
public double getTotalValue() {
	return totalValue;
}
public void setTotalValue(double totalValue) {
	this.totalValue = totalValue;
}

}
