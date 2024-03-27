package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.exception.CustomerNotFoundException;
import com.model.Customer;
import com.model.Orders;
import com.service.CustomerService;

public class CustomerServiceTest {
	CustomerService customerService=new CustomerService();
	
/***************************************************************************************/
	@Test
	public void calculateTotalOrdersTest() {
		int id=3;
		List<Orders> expectedOutput=new ArrayList<Orders>();
		Orders o=new Orders();
		Orders o1=new Orders();
		o1.setId(6);
		o.setId(3);
		expectedOutput.add(o);
		expectedOutput.add(o1);
		try {
			Assert.assertEquals(expectedOutput, customerService.calculateTotalOrders(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
/*******************************************************************************************/
	@Test
	public void validateCustomer() {
		//Use Case Valid Credentials
		int id=1;
		Customer expectedOutput=new Customer();
		expectedOutput.setId(1);
		try {
			Assert.assertEquals(expectedOutput, customerService.validateCustomer(id));
		} catch (SQLException | CustomerNotFoundException e) {}
		
		//Use Case InValid Credentials
		id=50;
		try {
			Assert.assertEquals(expectedOutput, customerService.validateCustomer(id));
		} catch (SQLException | CustomerNotFoundException e) {
			Assert.assertEquals("Customer ID Invalid".toLowerCase(), e.getMessage().toLowerCase());
		}
	}
	
/*******************************************************************************************/
	@Test
	public void  getCustomerDetails() {
		int id=1;
		Customer expectedOutput=new Customer(1,"luffy","D","pravin@gmail.com","6379253389","mumbai");
		try {
			Assert.assertEquals(expectedOutput, customerService.getCustomerDetails(id));
		} catch (SQLException | CustomerNotFoundException e) {}
		
		//Use Case InValid Credentials
		id=50;
		try {
			Assert.assertEquals(expectedOutput, customerService.getCustomerDetails(id));
		} catch (SQLException | CustomerNotFoundException e) {
			Assert.assertEquals("Invalid Customer ID".toLowerCase(), e.getMessage().toLowerCase());
		}

	}
	
/*******************************************************************************************/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
