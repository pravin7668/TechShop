package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Product;

public interface ProductDao {
	public List<Product> getProductDetails() throws SQLException;
	public void updateProductName(int pid, String productName) throws SQLException;
	
}
