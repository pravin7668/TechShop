package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dto.InventoryProductDto;
import com.dto.TotalInventoryValueDto;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;
import com.util.DBUtil;

public class InventoryDaoImpl implements InventoryDao{

	@Override
	public InventoryProductDto getProduct(int productId) throws SQLException, ProductNotFoundException {
		Connection conn=DBUtil.getDBConn();
		String sql="select p.id,p.product_name,p.price,i.quantity_in_stock,i.last_stock_update from product p join inventory i on p.id=i.product_id where p.id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, productId);
		ResultSet rst=pstmt.executeQuery();
		if(rst.next()) {
			int productID=rst.getInt("id");
			String productName=rst.getString("product_name");
			double price=rst.getDouble("price");
			int quantityInStock=rst.getInt("quantity_in_stock");
			InventoryProductDto p=new InventoryProductDto();
			p.setProductId(productID);
			p.setProductName(productName);
			p.setProductPrice(price);
			p.setQuantityInStock(quantityInStock);
			return p;
		}
		
		DBUtil.dbClose();
		throw new ProductNotFoundException("Product ID Not Found");
		
	}

	@Override
	public void insertProduct(String productName, String productDescription, double productPrice) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="insert into product(product_name,description,price) values(?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);

		pstmt.setString(1,productName) ;
		pstmt.setString(2,productDescription) ;
		pstmt.setDouble(3,productPrice);
		pstmt.executeUpdate();
		DBUtil.dbClose();


	}

	@Override
	public Product getProductId(String productName) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		Product p=new Product();
		String sql="select * from product where product_name=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,productName) ;
		ResultSet rst=pstmt.executeQuery();
		if(rst.next()) {
			int productId=rst.getInt("id");
//			Product p=new Product();
			p.setId(productId);
		}
		DBUtil.dbClose();
		return p;
	}

	@Override
	public void insertInventory(int id, LocalDate now,int quantity) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="insert into inventory(quantity_in_stock,last_stock_update,product_id) values(?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, quantity);
		pstmt.setString(2,(now.toString()));
		pstmt.setDouble(3,id);
		pstmt.executeUpdate();
		DBUtil.dbClose();

	}

	@Override
	public List<InventoryProductDto> getAllProducts() throws SQLException {
		Connection conn=DBUtil.getDBConn();
		List<InventoryProductDto> list=new ArrayList<InventoryProductDto>();
		String sql="select p.id,p.product_name,p.price,i.quantity_in_stock,i.last_stock_update from product p join inventory i on p.id=i.product_id";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int productID=rst.getInt("id");
			String productName=rst.getString("product_name");
			double price=rst.getDouble("price");
			int quantityInStock=rst.getInt("quantity_in_stock");
			InventoryProductDto p=new InventoryProductDto();
			p.setProductId(productID);
			p.setProductName(productName);
			p.setProductPrice(price);
			p.setQuantityInStock(quantityInStock);
			list.add(p);
		}
		return list;
	}

	@Override
	public void deleteInventory(int id) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="delete from inventory where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,id) ;
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}

	@Override
	public void deleteProduct(int id) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="delete from product where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,id) ;
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}

	

	@Override
	public void updateStock(int id, LocalDate now, int quantity) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update inventory set quantity_in_stock=?,last_stock_update=? where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,quantity);
		pstmt.setString(2,now.toString());
		pstmt.setInt(3,id);
		pstmt.executeUpdate();
		DBUtil.dbClose();
	}

	@Override
	public Inventory getQuantity(int productId1) throws SQLException, ProductNotFoundException {
		Connection conn=DBUtil.getDBConn();
		Inventory i=new Inventory();
		String sql="select * from inventory where product_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,productId1);
		ResultSet rst=pstmt.executeQuery();
		if(rst.next()) {
			int quantity=rst.getInt("quantity_in_stock");
			i.setQuantityInStock(quantity);
			return i;
		}
		
		DBUtil.dbClose();
		throw new ProductNotFoundException("Product Not Found");
	}

	@Override
	public List<TotalInventoryValueDto> getTotalValue() throws SQLException {
		
		Connection conn=DBUtil.getDBConn();
		List<TotalInventoryValueDto> list=new ArrayList<>();
		String sql="select p.product_name,(p.price*i.quantity_in_stock) as total_value from product p join inventory i on p.id=i.product_id";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			String productName=rst.getString("product_name");
			double totalValue=rst.getDouble("total_value");
			TotalInventoryValueDto i=new TotalInventoryValueDto(productName,totalValue);
			list.add(i);
		
		}
		
		DBUtil.dbClose();
		return list;
	}

	@Override
	public List<InventoryProductDto> getLowStockProducts(int threshold) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		List<InventoryProductDto> list=new ArrayList<InventoryProductDto>();
		String sql="select p.id,p.product_name,p.price,i.quantity_in_stock,i.last_stock_update from product p join inventory i on p.id=i.product_id where i.quantity_in_stock<?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, threshold);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int productID=rst.getInt("id");
			String productName=rst.getString("product_name");
			double price=rst.getDouble("price");
			int quantityInStock=rst.getInt("quantity_in_stock");
			InventoryProductDto p=new InventoryProductDto();
			p.setProductId(productID);
			p.setProductName(productName);
			p.setProductPrice(price);
			p.setQuantityInStock(quantityInStock);
			list.add(p);
		}
		
		DBUtil.dbClose();
		return list;
	}

	@Override
	public List<InventoryProductDto> getOutOfStockProducts() throws SQLException {
		Connection conn=DBUtil.getDBConn();
		List<InventoryProductDto> list=new ArrayList<InventoryProductDto>();
		String sql="select p.id,p.product_name,p.price,i.quantity_in_stock,i.last_stock_update from product p join inventory i on p.id=i.product_id where i.quantity_in_stock=0";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		while(rst.next()) {
			int productID=rst.getInt("id");
			String productName=rst.getString("product_name");
			double price=rst.getDouble("price");
			int quantityInStock=rst.getInt("quantity_in_stock");
			InventoryProductDto p=new InventoryProductDto();
			p.setProductId(productID);
			p.setProductName(productName);
			p.setProductPrice(price);
			p.setQuantityInStock(quantityInStock);
			list.add(p);
		}
		
		DBUtil.dbClose();
		return list;
	}

}
