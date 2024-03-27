package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.InventoryDetailsNotFound;
import com.exception.OrderNotFoundException;
import com.model.Inventory;
import com.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao{

	@Override
	public List<OrderDetailsOfCustomersDto> getOrderDetailsOfCustomer(int cid) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		List<OrderDetailsOfCustomersDto> list =new ArrayList<OrderDetailsOfCustomersDto>();
		
		String sql="select od.order_id,p.product_name,od.product_id,o.total_amount,od.quantity from order_detail od join orders o on o.id =od.order_id join product p on p.id =od.product_id where o.customer_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, cid);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int orderId=rst.getInt("order_id");
			String productName=rst.getString("product_name");
			double totalAmount=rst.getDouble("total_amount");
			int quantity=rst.getInt("quantity");
			OrderDetailsOfCustomersDto od=new OrderDetailsOfCustomersDto();
			od.setOrderId(orderId);
			od.setProductName(productName);
			od.setTotalAmount(totalAmount);
			od.setQuantity(quantity);
			list.add(od);
			
		}
		
		DBUtil.dbClose();
		
		return list;
	}

	@Override
	public OrderDetailsOfCustomersDto getOrderDeatils(int orderId) throws SQLException, OrderNotFoundException {
		Connection conn=DBUtil.getDBConn();
		String sql="select od.order_id,p.product_name,od.product_id,o.total_amount,od.quantity from order_detail od join orders o on o.id =od.order_id join product p on p.id =od.product_id where o.id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		ResultSet rst=pstmt.executeQuery();
		if(rst.next()) {
			int orderId1=rst.getInt("order_id");
			String productName=rst.getString("product_name");
			double totalAmount=rst.getDouble("total_amount");
			int quantity=rst.getInt("quantity");
			int productId=rst.getInt("product_id");
			OrderDetailsOfCustomersDto od=new OrderDetailsOfCustomersDto();
			od.setOrderId(orderId1);
			od.setProductName(productName);
			od.setTotalAmount(totalAmount);
			od.setQuantity(quantity);
			od.setProductId(productId);
			return od;
		}
		
		
		DBUtil.dbClose();
		throw new OrderNotFoundException("Order Not Found");
		
	}

	@Override
	public void updateQuantityInstock(int productId, int quantity) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update inventory set quantity_in_stock=? where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, quantity);
		pstmt.setInt(2, productId);
		pstmt.executeUpdate();

		
		
		DBUtil.dbClose();

	}

	@Override
	public Inventory getInventoryDetails(int productId) throws SQLException, InventoryDetailsNotFound {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from inventory where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, productId);
		ResultSet rst=pstmt.executeQuery();
		if (rst.next()) {
			int quantity=rst.getInt("quantity_in_stock");
			Inventory i=new Inventory();
			i.setQuantityInStock(quantity);
			return i;
		}
		
		
		DBUtil.dbClose();
		throw new InventoryDetailsNotFound("Inventory Details Not Found");
	}

	@Override
	public void deleteOrderDetail(int orderId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="delete from order_detail where order_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}

	@Override
	public void deleteOrder(int orderId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="delete from orders where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}
		

}
