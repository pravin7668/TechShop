package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.InventoryDetailsNotFound;
import com.exception.OrderNotFoundException;
import com.model.Inventory;
import com.service.OrdersService;

public class OrdersServiceTest {
	OrdersService ordersService=new OrdersService();
	@Test
	/********************************************************************************/
	public void getOrderDetailsOfCustomer(){
		int id=1;
		List<OrderDetailsOfCustomersDto> expectedOutput=new ArrayList<>();
		OrderDetailsOfCustomersDto o1=new OrderDetailsOfCustomersDto();
		o1.setOrderId(5);
		o1.setProductName("nothing");
		o1.setTotalAmount(250);
		o1.setQuantity(10);
		expectedOutput.add(o1);
		try {
			Assert.assertEquals(expectedOutput, ordersService.getOrderDetailsOfCustomer(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/********************************************************************************/
	@Test
	public void getOrderDetails() {
		//Use Case "Valid Credentials"
		int id=2;
		OrderDetailsOfCustomersDto o1=new OrderDetailsOfCustomersDto();
		o1.setOrderId(2);
		o1.setProductName("samsung");
		o1.setProductId(2);
		o1.setTotalAmount(100);
		o1.setQuantity(3);
		try {
			Assert.assertEquals(o1, ordersService.getOrderDeatils(id));
		} catch (SQLException | OrderNotFoundException e) {
			e.printStackTrace();
		}
		
		//Use Case "InValid Credentials"
		id=50;
		try {
			Assert.assertEquals(o1, ordersService.getOrderDeatils(id));
		} catch (SQLException | OrderNotFoundException e) {
			Assert.assertEquals("Order Not Found".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	/********************************************************************************/
	@Test
	public void getInventoryDetailsTest() {
		//Use Case "Valid Credentials"
		int id=1;
		Inventory expectedOutput=new Inventory();
		expectedOutput.setQuantityInStock(34);
		try {
			Assert.assertEquals(expectedOutput, ordersService.getInventoryDetails(id));
		} catch (SQLException  | InventoryDetailsNotFound e) {}

		//Use Case "InValid Credentials"
		id=40;
		try {
			Assert.assertEquals(expectedOutput, ordersService.getInventoryDetails(id));
		} catch (SQLException |  InventoryDetailsNotFound e) {
			Assert.assertEquals("Inventory Details Not Found".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	/********************************************************************************/
	@Test
	public void getTotalAmountTest() {
		List<OrderDetailsOfCustomersDto> expectedOutput=new ArrayList<>();
		OrderDetailsOfCustomersDto o1=new OrderDetailsOfCustomersDto();
		o1.setTotalAmount(5000);
		OrderDetailsOfCustomersDto o2=new OrderDetailsOfCustomersDto();
		o1.setTotalAmount(10000);
		expectedOutput.add(o1);
		expectedOutput.add(o2);
		double expectedOut=15000;
		Assert.assertEquals(expectedOut,ordersService.getTotalAmount(expectedOutput),expectedOut);
		
	}
	/********************************************************************************/


	


























}
