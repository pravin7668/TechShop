package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.InventoryDao;
import com.dao.InventoryDaoImpl;
import com.dto.InventoryProductDto;
import com.dto.TotalInventoryValueDto;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;

public class InventoryService {
	InventoryDao inventoryDao=new InventoryDaoImpl();
	public InventoryProductDto getProduct(int productId) throws SQLException, ProductNotFoundException {
		return inventoryDao.getProduct( productId);
	}
	public void insertProduct(String productName, String productDescription, double productPrice) throws SQLException {
		inventoryDao.insertProduct(productName,productDescription,productPrice);
	}
	public Product getProductId(String productName) throws SQLException {
		return inventoryDao.getProductId(productName);
	}
	public void insertInventory(int id, LocalDate now, int quantity) throws SQLException {
		inventoryDao.insertInventory(id,now,quantity);
	}
	public List<InventoryProductDto> getAllProducts() throws SQLException{
		return inventoryDao.getAllProducts();
	}
	public void deleteInventory(int id) throws SQLException{
		inventoryDao.deleteInventory(id);
	}
	public void deleteProduct(int id) throws SQLException{
		inventoryDao.deleteProduct( id);
	}
	public void updateStock(int id, LocalDate now, int quantity) throws SQLException {
		inventoryDao.updateStock(id,now,quantity);
	}
	public Inventory getQuantity(int productId1) throws SQLException, ProductNotFoundException{
		return inventoryDao.getQuantity(productId1);
	}
	public boolean isQuantityAvailable(int quantityInStock, int quantity) {
		if(quantityInStock>=quantity) {
			return true;
		}
		return false;
	}
	public List<TotalInventoryValueDto> getTotalValue() throws SQLException {
		// TODO Auto-generated method stub
		return inventoryDao.getTotalValue();
	}
	public double getGrossInventoryTotal(List<TotalInventoryValueDto> list1) {
		double grossTotal=0;
		for (TotalInventoryValueDto t:list1) {
			grossTotal+=t.getTotalValue();
		}
		return grossTotal;
	}
	public List<InventoryProductDto> getLowStockProducts(int threshold) throws SQLException {
		return inventoryDao.getLowStockProducts(threshold);
	}
	public List<InventoryProductDto> getOutOfStockProducts() throws SQLException {
		return inventoryDao.getOutOfStockProducts();
	}

}
