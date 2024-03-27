package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dto.InventoryProductDto;
import com.dto.TotalInventoryValueDto;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;

public interface InventoryDao {

	InventoryProductDto getProduct(int productId) throws SQLException, ProductNotFoundException;

	void insertProduct(String productName, String productDescription, double productPrice) throws SQLException;

	Product getProductId(String productName) throws SQLException;

	void insertInventory(int id, LocalDate now, int quantity) throws SQLException;

	List<InventoryProductDto> getAllProducts() throws SQLException;

	void deleteInventory(int id) throws SQLException;

	void deleteProduct(int id) throws SQLException;

	void updateStock(int id, LocalDate now, int quantity) throws SQLException;

	Inventory getQuantity(int productId1) throws SQLException,ProductNotFoundException;

	List<TotalInventoryValueDto> getTotalValue() throws SQLException;

	List<InventoryProductDto> getLowStockProducts(int threshold) throws SQLException;

	List<InventoryProductDto> getOutOfStockProducts() throws SQLException;

	

	
}
