package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.CustomerNotFoundException;
import com.model.Customer;
import com.model.Orders;

public interface CustomerDao {
	public List<Orders> calculateTotalOrders(int id) throws SQLException;
	public Customer validateCustomer(int id) throws SQLException, CustomerNotFoundException;
	public Customer getCustomerDetails(int cid) throws SQLException, CustomerNotFoundException;
	public void updateEmail(int cid1, String email) throws SQLException;
	public void updatePhone(int cid1, String phoneNo) throws SQLException;
	public void updateAddress(int cid1, String address) throws SQLException;
	


}
