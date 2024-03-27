package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.InventoryDetailsNotFound;
import com.exception.OrderNotFoundException;
import com.model.Inventory;
import com.service.OrdersService;

public class OrdersController {
public static void main(String[] args) {
	OrdersService ordersService= new OrdersService();
	Scanner sc = new Scanner(System.in);

	while (true) {
		System.out.println("********* PRODUCT OPS ***********");
		System.out.println("Press 1 to Calculate the Total amount for your orders");
		System.out.println("Press 2 to Retrieve order details");
		System.out.println("Press 3 to Cancel your Order and adjust stock levels");
		System.out.println("Press 0 to Exit");
		System.out.println("**********************************");
		int input = sc.nextInt();
		if (input == 0) {
			break;
		}
		switch (input) {
		case 1:
			System.out.println("Enter your Customer ID ");
			int cid=sc.nextInt();
			List<OrderDetailsOfCustomersDto> list=new ArrayList<OrderDetailsOfCustomersDto>();
			try {
				list=ordersService.getOrderDetailsOfCustomer(cid);
				for(OrderDetailsOfCustomersDto od:list) {
					System.out.println(od.getOrderId()+" "+od.getProductName()+" "+od.getTotalAmount()+" "+od.getQuantity());
				}
				
				System.out.println("Total Amount :" +ordersService.getTotalAmount(list));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			
			
			
			break;
		case 2:
			System.out.println("Enter your Customer ID ");
			int cid1=sc.nextInt();
//			List<OrderDetailsOfCustomers> list1=new ArrayList<OrderDetailsOfCustomers>();
			try {
				list=ordersService.getOrderDetailsOfCustomer(cid1);
				for(OrderDetailsOfCustomersDto od:list) {
					System.out.println(od.getOrderId()+" "+od.getProductName()+" "+od.getTotalAmount()+" "+od.getQuantity());
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			break;
		case 3:
			System.out.println("Enter your Customer ID ");
			int cid2=sc.nextInt();
			try {
				list=ordersService.getOrderDetailsOfCustomer(cid2);
				System.out.println("OrderId"+" "+"ProductName"+" "+"TotalAmount"+" "+"Quantity");
				for(OrderDetailsOfCustomersDto od:list) {
					System.out.println(od.getOrderId()+"        "+od.getProductName()+"      "+od.getTotalAmount()+"       "+od.getQuantity());
				}
				System.out.println();
				System.out.println("Enter OrderId to Delete ");
				int orderId=sc.nextInt();
				OrderDetailsOfCustomersDto od=ordersService.getOrderDeatils(orderId);
				System.out.println(od);
				Inventory i=ordersService.getInventoryDetails(od.getProductId());
				ordersService.updateQuantityInstock(od.getProductId(),od.getQuantity(),i.getQuantityInStock());
				ordersService.deleteOrderDetail(orderId);
				ordersService.deleteOrder(orderId);
				
			} catch (SQLException | OrderNotFoundException | InventoryDetailsNotFound e) {
				System.out.println(e.getMessage());
			}

			break;
			
		}
}
	sc.close();
}


}
