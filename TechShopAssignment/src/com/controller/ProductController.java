package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exception.InventoryDetailsNotFound;
import com.model.Inventory;
import com.model.Product;
import com.service.ProductService;

public class ProductController {
	public static void main(String[] args) {
		ProductService ps= new ProductService();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("********* PRODUCT OPS ***********");
			System.out.println("Press 1 to  get the product details");
			System.out.println("Press 2 to update product info");
			System.out.println("Press 3 to check if the product is in stock");
			System.out.println("Press 0 to Exit");
			System.out.println("**********************************");
			int input = sc.nextInt();
			if (input == 0) {
				break;
			}
			switch (input) {
			case 1:
				List<Product> list=new ArrayList<Product>();
				System.out.println("All Products");
				try {
					list =ps.getProductDetails();
					for (Product p:list) {
						System.out.println(p.getId()+"  "+p.getProductName()+"    "+p.getDescription()+"  "+p.getProductPrice());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("All Products");
				try {
					list =ps.getProductDetails();
					for (Product p:list) {
						System.out.println(p.getId()+"  "+p.getProductName()+"    "+p.getDescription()+"  "+p.getProductPrice());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Enter the Product ID to Update");
				int pid = sc.nextInt();

				System.out.println("Press 1 to update Product Name");
				System.out.println("Press 2 to update Product Description");
				System.out.println("Press 3 to update Product Price");
				int input1 = sc.nextInt();
				switch (input1) {
				case 1:
					System.out.println("Enter Product Name to update");
					sc.nextLine();
					String productName = sc.nextLine();
					try {

						ps.updateProductName(pid, productName);
						System.out.println("update Succesfull");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.println("Enter Product Description to update");
					sc.nextLine();
					String description = sc.nextLine();
					try {

						ps.updateDescription(pid, description);
						System.out.println("update Succesfull");
					} catch (SQLException e) {
						e.printStackTrace();
					}

					break;
				case 3:
					System.out.println("Enter Product Price to update");
					double price = sc.nextDouble();
					try {

						ps.updatePrice(pid, price);
						System.out.println("update Succesfull");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					

				break;
				}
				
			case 3:
				System.out.println("All Products");
				try {
					list =ps.getProductDetails();
					for (Product p:list) {
						System.out.println(p.getId()+"  "+p.getProductName()+"    "+p.getDescription()+"  "+p.getProductPrice());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Enter the Product ID to Check If it is Available or not");
				int pid1 = sc.nextInt();
				try {
					Inventory i=ps.getInventoryDetails(pid1);
					boolean productAvailable=ps.checkAvailability(i,pid1);
					if(!productAvailable) {
						System.out.println("Product not in Stock Check after sometime");
					}
					else {
						System.out.println("Product in stock you can Order");
					}
				} catch (SQLException | InventoryDetailsNotFound e) {
					System.out.println(e.getMessage());
				}
				
				
				
				
				break;
}
		}
		sc.close();
	}
}
