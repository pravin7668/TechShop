package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dto.OrderDetailsOfCustomersDto;
import com.exception.ProductNotFoundException;
import com.model.Product;
import com.service.OrderDetailService;

public class OrderDetailController {
	public static void main(String[] args) {
		OrderDetailService orderDetailService = new OrderDetailService();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("********* PRODUCT OPS ***********");
			System.out.println("Press 1 to Calculate the SubTotal for your Orders");
			System.out.println("Press 2 to Get Order Details Info");
			System.out.println("Press 3 to Update the Quantity for your Existing Order");
			System.out.println("Press 4 to Applies discount for this order");
			System.out.println("Press 0 to Exit");
			System.out.println("**********************************");
			int input = sc.nextInt();
			if (input == 0) {
				break;
			}
			switch (input) {
			case 1:
				System.out.println("Enter your Customer ID ");
				int cid = sc.nextInt();
				List<OrderDetailsOfCustomersDto> list = new ArrayList<OrderDetailsOfCustomersDto>();
				try {
					list = orderDetailService.getOrderDetail(cid);
					for (OrderDetailsOfCustomersDto od : list) {
						System.out.println(od.getOrderId() + " " + od.getProductName() + " " + od.getTotalAmount() + " "
								+ od.getQuantity());
					}
					System.out.println("Total Amount :" +orderDetailService.getTotalAmount(list));

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			
			case 2:
				System.out.println("Enter your Customer ID ");
				int cid1 = sc.nextInt();
				list = new ArrayList<OrderDetailsOfCustomersDto>();
				try {
					list = orderDetailService.getOrderDetail(cid1);
					for (OrderDetailsOfCustomersDto od : list) {
						System.out.println(od.getOrderId() + " " + od.getProductName() + " " + od.getTotalAmount() + " "
								+ od.getQuantity());
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				
				break;
			case 3:
				System.out.println("Enter your Customer ID ");
				int cid2 = sc.nextInt();
				list = new ArrayList<OrderDetailsOfCustomersDto>();
				try {
					list = orderDetailService.getOrderDetail(cid2);
					for (OrderDetailsOfCustomersDto od : list) {
						System.out.println(od.getOrderId() + " " + od.getProductName() + " " + od.getTotalAmount() + " "
								+ od.getQuantity());
					}
				System.out.println("Enter Order Id to Update Quantity");
				int orderId=sc.nextInt();
				System.out.println("Enter New Quantity To Update");
				int quantity=sc.nextInt();
				orderDetailService.updateQuantity(orderId,quantity);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter your Customer ID ");
				int cid3 = sc.nextInt();
				list = new ArrayList<OrderDetailsOfCustomersDto>();
				try {
					list = orderDetailService.getOrderDetail(cid3);
					for (OrderDetailsOfCustomersDto od : list) {
						System.out.println(od.getProductId() + " " + od.getProductName() + " " + od.getTotalAmount() + " "
								+ od.getQuantity());
					}
				System.out.println("Enter Product Id to Add 10% Discount");
				int productId=sc.nextInt();
				Product p=orderDetailService.getPrice(productId);
				
				orderDetailService.updateDiscount(productId,(p.getProductPrice() - p.getProductPrice()*0.1));
				} catch (SQLException | ProductNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}
		sc.close();
	}

	
}
