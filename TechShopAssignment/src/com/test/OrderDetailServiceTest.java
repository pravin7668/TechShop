package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.ProductNotFoundException;
import com.model.Product;
import com.service.OrderDetailService;

public class OrderDetailServiceTest {
	OrderDetailService orderDetailService = new OrderDetailService();

	/*****************************************************************************************/
	@Test
	public void getOrderDetails() {
		int id = 1;
		List<OrderDetailsOfCustomersDto> expectedOutput = new ArrayList<>();
		OrderDetailsOfCustomersDto o1 = new OrderDetailsOfCustomersDto();
		o1.setOrderId(5);
		o1.setProductName("nothing");
		o1.setProductId(5);
		o1.setPrice(3500);
		o1.setTotalAmount(250);
		o1.setQuantity(10);
		expectedOutput.add(o1);
		try {
			Assert.assertEquals(expectedOutput, orderDetailService.getOrderDetail(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************************************/
	@Test
	public void getTotalAmount() {
		List<OrderDetailsOfCustomersDto> expectedOutput = new ArrayList<>();
		OrderDetailsOfCustomersDto o1 = new OrderDetailsOfCustomersDto();
		o1.setTotalAmount(5000);
		OrderDetailsOfCustomersDto o2 = new OrderDetailsOfCustomersDto();
		o1.setTotalAmount(10000);
		expectedOutput.add(o1);
		expectedOutput.add(o2);
		double expectedOut = 15000;
		Assert.assertEquals(expectedOut, orderDetailService.getTotalAmount(expectedOutput), expectedOut);

	}

	/*****************************************************************************************/
	@Test
	public void getPrice() {
		// Use Case "Valid Credentials"
		int id = 1;
		Product expectedOutput = new Product();
		expectedOutput.setProductPrice(55000);
		try {
			Assert.assertEquals(expectedOutput, orderDetailService.getPrice(id));
		} catch (SQLException | ProductNotFoundException e) {
		}

		// Use Case "Valid Credentials"
		id = 50;
		try {
			Assert.assertEquals(expectedOutput, orderDetailService.getPrice(id));
		} catch (SQLException | ProductNotFoundException e) {
			Assert.assertEquals("Product ID Not Found".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	/*****************************************************************************************/

}
