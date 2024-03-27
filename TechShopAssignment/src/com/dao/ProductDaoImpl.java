package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.InventoryDetailsNotFound;
import com.model.Inventory;
import com.model.Product;
import com.util.DBUtil;

public class ProductDaoImpl {

	public List<Product> getProductDetails() throws SQLException{
		Connection conn=DBUtil.getDBConn();
		List<Product> list=new ArrayList<Product>();
		String sql="select * from product";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst =pstmt.executeQuery();
		while (rst.next()) {
			int id=rst.getInt("id");
			String productName=rst.getString("product_name");
			String description =rst.getString("description");
			double price=rst.getDouble("price");
			Product p=new Product(id,productName,description,price);
			list.add(p);
		}
		
		
		DBUtil.dbClose();
		
		return list;
	}

	public void updateProductName(int pid, String productName) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update product set product_name=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,productName);
		pstmt.setInt(2, pid);
		pstmt.executeUpdate();

		DBUtil.dbClose();
	}

	public void updateDescription(int pid, String description) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update product set description=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,description);
		pstmt.setInt(2, pid);
		pstmt.executeUpdate();

		DBUtil.dbClose();
	}

	public void updatePrice(int pid, double price) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update product set price=? where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setDouble(1,price);
		pstmt.setInt(2, pid);
		pstmt.executeUpdate();

		DBUtil.dbClose();		
	}

	public Inventory getInventoryDetails(int pid1) throws SQLException, InventoryDetailsNotFound {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from inventory where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, pid1);
		ResultSet rst =pstmt.executeQuery();
		if (rst.next()) {
			int productId=rst.getInt("product_id");
			int quantityInStock=rst.getInt("quantity_in_stock");
			Inventory i=new Inventory();
			i.setProductId(productId);
			i.setQuantityInStock(quantityInStock);
			return i;
		}
		
		
		DBUtil.dbClose();
		throw new InventoryDetailsNotFound("Item out of stock");
		
		
		
	}

}
