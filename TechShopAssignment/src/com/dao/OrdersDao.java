package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.InventoryDetailsNotFound;
import com.exception.OrderNotFoundException;
import com.model.Inventory;

public interface OrdersDao {

	List<OrderDetailsOfCustomersDto> getOrderDetailsOfCustomer(int cid) throws SQLException;

	OrderDetailsOfCustomersDto getOrderDeatils(int orderId) throws SQLException,OrderNotFoundException;

	void updateQuantityInstock(int productId, int quantity) throws SQLException;

	Inventory getInventoryDetails(int productId) throws SQLException,InventoryDetailsNotFound;

	void deleteOrderDetail(int orderId) throws SQLException;

	void deleteOrder(int orderId) throws SQLException;

}
