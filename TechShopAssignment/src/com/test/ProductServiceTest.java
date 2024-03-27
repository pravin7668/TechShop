package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InventoryDetailsNotFound;
import com.model.Inventory;
import com.model.Product;
import com.service.ProductService;

public class ProductServiceTest {
	ProductService productService = new ProductService();

	/********************************************************************************/
	@Test
	public void getProductDetails() {
		List<Product> expectedOutput = new ArrayList<Product>();
		Product p1 = new Product(1, "apple", "mobile", 55000);
		Product p2 = new Product(2, "samsung", "mobile", 40000);
		Product p3 = new Product(3, "asus", "laptop", 700000);
		Product p4 = new Product(4, "dell", "laptop", 60000);
		Product p5 = new Product(5, "nothing", "mobile", 3500);
		Product p6 = new Product(6, "Acer", "desktop", 50000);
		expectedOutput.add(p1);
		expectedOutput.add(p2);
		expectedOutput.add(p3);
		expectedOutput.add(p4);
		expectedOutput.add(p5);
		expectedOutput.add(p6);
		try {
			Assert.assertEquals(expectedOutput, productService.getProductDetails());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/********************************************************************************/

	@Test
	public void checkAvailability() {
		// Use Case "Valid Credentials"
		int pid = 1;
		Inventory i = new Inventory();
		i.setProductId(1);
		i.setQuantityInStock(100);
		boolean expectedOutput = true;
		Assert.assertEquals(expectedOutput, productService.checkAvailability(i, pid));
		// Use Case "invalid credentials"
		i.setProductId(1);
		i.setQuantityInStock(0);
		expectedOutput = false;
		Assert.assertEquals(expectedOutput, productService.checkAvailability(i, pid));
	}

	/********************************************************************************/
	@Test
	public void getInventoryDetails() {
		// Use Case "Valid Credentials"
		int id = 1;
		Inventory expectedOutput = new Inventory();
		expectedOutput.setProductId(1);
		expectedOutput.setQuantityInStock(34);
		try {
			Assert.assertEquals(expectedOutput, productService.getInventoryDetails(id));
		} catch (SQLException | InventoryDetailsNotFound e) {
		}

		// Use Case "Invalid credentials"
		id = 50;
		try {
			Assert.assertEquals(expectedOutput, productService.getInventoryDetails(id));
		} catch (SQLException | InventoryDetailsNotFound e) {
			Assert.assertEquals("Item out of stock".toLowerCase(), e.getMessage().toLowerCase());

		}
	}

	/********************************************************************************/

}
