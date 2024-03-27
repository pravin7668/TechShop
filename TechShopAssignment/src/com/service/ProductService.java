package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProductDaoImpl;
import com.exception.InventoryDetailsNotFound;
import com.model.Inventory;
import com.model.Product;

public class ProductService {
	ProductDaoImpl productDaoImpl=new ProductDaoImpl();
	public List<Product> getProductDetails() throws SQLException {
		return productDaoImpl.getProductDetails();
	}
	public void updateProductName(int pid, String productName) throws SQLException {
		productDaoImpl.updateProductName(pid, productName);
	}
	public void updateDescription(int pid, String description) throws SQLException {
		productDaoImpl.updateDescription(pid,description);
	}
	public void updatePrice(int pid, double price) throws SQLException {
		productDaoImpl.updatePrice(pid,price);
	}
	public boolean checkAvailability(Inventory i, int pid1) {
		if(i.getProductId()==pid1) {
			if(i.getQuantityInStock()>0) {
				return true;
			}
		}
		return false;
	}
	public Inventory getInventoryDetails(int pid1) throws SQLException, InventoryDetailsNotFound {
		return productDaoImpl.getInventoryDetails(pid1);
	}

}
