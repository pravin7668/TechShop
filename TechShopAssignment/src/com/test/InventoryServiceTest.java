package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.InventoryProductDto;
import com.dto.TotalInventoryValueDto;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;
import com.service.InventoryService;

public class InventoryServiceTest {
	InventoryService inventoryService=new InventoryService();
	/*******************************************************************************/
	@Test
	public void getProduct() {
		//Use Case "Valid Credentials"
		int id=1;
		InventoryProductDto expectedOutput=new InventoryProductDto();
		expectedOutput.setProductId(1);
		expectedOutput.setProductName("apple");
		expectedOutput.setProductPrice(55000);
		expectedOutput.setQuantityInStock(34);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getProduct(id));
		} catch (SQLException | ProductNotFoundException e) {}
		
		//Use Case "Valid Credentials"
		id=80;
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getProduct(id));
		} catch (SQLException | ProductNotFoundException e) {
			Assert.assertEquals("Product ID Not Found".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	/*******************************************************************************/
	@Test
	public void getProductIdTest() {
		String name="apple";
		Product expectedOutput=new Product();
		expectedOutput.setId(1);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getProductId(name));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/*******************************************************************************/
	@Test
	public void getQuantityTest() {
		//Use Case "Valid Credentials"
		int id=1;
		Inventory expectedOutput=new Inventory();
		expectedOutput.setQuantityInStock(34);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getQuantity(id));
		} catch (SQLException | ProductNotFoundException e) {}
		
		//Use Case "InValid Credentials"
		id=34;
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getQuantity(id));
		} catch (SQLException | ProductNotFoundException e) {
			Assert.assertEquals("Product Not Found".toLowerCase(), e.getMessage().toLowerCase());

		}

	}
	/*******************************************************************************/
	@Test
	public void getLowStockProducts() {
		int threshold=30;
		List<InventoryProductDto> expectedOutput=new ArrayList<InventoryProductDto>();
		InventoryProductDto i=new InventoryProductDto();
		i.setProductId(4);
		i.setProductName("dell");
		i.setProductPrice(60000);
		i.setQuantityInStock(10);
		expectedOutput.add(i);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getLowStockProducts(threshold));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/*******************************************************************************/
	@Test
	public void getLowStockProductTest() {
		List<InventoryProductDto> expectedOutput=new ArrayList<InventoryProductDto>();
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getOutOfStockProducts());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*******************************************************************************/
	@Test
	public void getTotalValueTest() {
		List<TotalInventoryValueDto> expectedOutput=new ArrayList<TotalInventoryValueDto>();
		TotalInventoryValueDto i1=new TotalInventoryValueDto("apple",1870000);
		TotalInventoryValueDto i2=new TotalInventoryValueDto("samsung",2800000);
		TotalInventoryValueDto i3=new TotalInventoryValueDto("asus",28000000);
		TotalInventoryValueDto i4=new TotalInventoryValueDto("dell",600000);
		TotalInventoryValueDto i5=new TotalInventoryValueDto("nothing",122500);
		expectedOutput.add(i1);
		expectedOutput.add(i2);
		expectedOutput.add(i3);
		expectedOutput.add(i4);
		expectedOutput.add(i5);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getTotalValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*******************************************************************************/
	@Test
	public void getGrossInventoryTotalTest() {
		List<TotalInventoryValueDto> list=new ArrayList<TotalInventoryValueDto>();
		TotalInventoryValueDto t1=new TotalInventoryValueDto();
		t1.setTotalValue(25000);
		TotalInventoryValueDto t2=new TotalInventoryValueDto();
		t1.setTotalValue(25000);
		double expectedOutput=50000;
		Assert.assertEquals(expectedOutput, inventoryService.getGrossInventoryTotal(list),expectedOutput);

		
	}
	/*******************************************************************************/
	@Test
	public void isQuantityAvailable() {
		//Use Case 1
		int quantityInStock=50;
		int quantity=20;
		boolean expectedOutput=true;
		Assert.assertEquals(expectedOutput, inventoryService.isQuantityAvailable(quantityInStock, quantity));
		
		//Use Case 2
		quantityInStock=50;
		quantity=200;
		expectedOutput=false;
		Assert.assertEquals(expectedOutput, inventoryService.isQuantityAvailable(quantityInStock, quantity));
	}
	/*******************************************************************************/
	@Test
	public void getAllProducts() {
		List<InventoryProductDto> expectedOutput=new ArrayList<InventoryProductDto>();
		InventoryProductDto i1=new InventoryProductDto(1,"apple",55000,34);
		InventoryProductDto i2=new InventoryProductDto(2,"samsung",40000,70);
		InventoryProductDto i3=new InventoryProductDto(3,"asus",700000,40);
		InventoryProductDto i4=new InventoryProductDto(4,"dell",60000,10);
		InventoryProductDto i5=new InventoryProductDto(5,"nothing",3500,35);
		expectedOutput.add(i1);
		expectedOutput.add(i2);
		expectedOutput.add(i3);
		expectedOutput.add(i4);
		expectedOutput.add(i5);
		try {
			Assert.assertEquals(expectedOutput, inventoryService.getAllProducts());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/*******************************************************************************/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
