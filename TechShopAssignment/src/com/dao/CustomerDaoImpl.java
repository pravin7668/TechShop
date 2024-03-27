package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.CustomerNotFoundException;
import com.model.Customer;
import com.model.Orders;
import com.util.DBUtil;

public class CustomerDaoImpl {

	public List<Orders> calculateTotalOrders(int id) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		List<Orders> list=new ArrayList<Orders>();
		String sql="select * from orders where customer_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			int oid =rst.getInt("id");
			Orders o=new Orders();
			o.setId(oid);
			list.add(o);
			
		}
		
		DBUtil.dbClose();
		return list;

	}

	public Customer validateCustomer(int id) throws SQLException, CustomerNotFoundException {
		Connection conn=DBUtil.getDBConn();
		Customer c =new Customer();
		String sql="select * from customer where id=?";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst =pstmt.executeQuery();
		if (rst.next()) {
			int cid=rst.getInt("id");
			c.setId(cid);
			return c;
		}
		DBUtil.dbClose();
		
		throw new CustomerNotFoundException("Customer ID Invalid");
		

	}

	public Customer getCustomerDetails(int cid) throws SQLException, CustomerNotFoundException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from customer where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, cid);
		ResultSet rst=pstmt.executeQuery();
		if(rst.next()) {
			int id=rst.getInt("id");
			String firstName=rst.getString("first_name");
			String lastName=rst.getString("last_name");
			String email=rst.getString("email");
			String address=rst.getString("address");
			String phoneNumber=rst.getString("phone");
			Customer c =new Customer(id,firstName,lastName,email,phoneNumber,address);
			return c;

			
		}
		
		
		
		DBUtil.dbClose();
		throw new CustomerNotFoundException("Invalid Customer ID");
		
	}

	public void updateEmail(int cid1, String email) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update customer set email=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,email);
		pstmt.setInt(2, cid1);
		pstmt.executeUpdate();

		DBUtil.dbClose();

	}

	public void updatePhone(int cid1, String phoneNo) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update customer set phone=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,phoneNo);
		pstmt.setInt(2, cid1);
		pstmt.executeUpdate();

		DBUtil.dbClose();
	}

	public void updateAddress(int cid1, String address) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update customer set address=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,address);
		pstmt.setInt(2, cid1);
		pstmt.executeUpdate();

		DBUtil.dbClose();
	}
	

}
