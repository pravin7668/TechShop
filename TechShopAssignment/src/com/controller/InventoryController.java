package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dto.InventoryProductDto;
import com.dto.TotalInventoryValueDto;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;
import com.service.InventoryService;

public class InventoryController {
	public static void main(String[] args) {
		InventoryService inventoryService = new InventoryService();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("********* INVENTORY OPS ***********");
			System.out.println("Press 1 to Get Product");
			System.out.println("Press 2 to Get Quantity In Stock");
			System.out.println("Press 3 to Add To Inventory");
			System.out.println("Press 4 to Remove To Inventory");
			System.out.println("Press 5 to Update Quantity In Stock");
			System.out.println("Press 6 to Check The Product Is Available Or Not");
			System.out.println("Press 7 to List All Products");
			System.out.println("Press 8 to Get The Total Iventory Value");
			System.out.println("Press 9 to List Low Stock Products");
			System.out.println("Press 10 to List Out Of Stock Products");
			System.out.println("Press 0 to Exit");
			System.out.println("**********************************");
			int input = sc.nextInt();
			if (input == 0) {
				break;
			}
			switch (input) {
			case 1:
				System.out.println("Enter Product ID to Get Details");
				int productId = sc.nextInt();
				InventoryProductDto p;
				try {
					p = inventoryService.getProduct(productId);
					System.out.println(p.getProductId() + "  " + p.getProductName() + "  " + p.getProductPrice() + "  "
							+ p.getQuantityInStock());

				} catch (SQLException | ProductNotFoundException e) {
					e.printStackTrace();
				}

				break;
			case 2:
				System.out.println("Enter Product ID to Get Details");
				productId = sc.nextInt();

				try {
					p = inventoryService.getProduct(productId);
					System.out.println(p.getProductId() + "  " + p.getProductName() + "  " + p.getQuantityInStock());

				} catch (SQLException | ProductNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter the Product Name ");
				sc.nextLine();
				String productName = sc.nextLine();
				System.out.println("Enter the Product Description");
				String productDescription = sc.nextLine();
				System.out.println("Enter the Product Price");
				double productPrice = sc.nextDouble();
				try {
					inventoryService.insertProduct(productName, productDescription, productPrice);
					Product p1 = inventoryService.getProductId(productName);
					System.out.println("Enter No Of Quantity ");
					int quantity = sc.nextInt();
					inventoryService.insertInventory(p1.getId(), LocalDate.now(), quantity);
					System.out.println("Product and inventory updated succesfully");

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 4:
				System.out.println("All Products ");
				List<InventoryProductDto> list;
				try {
					list = inventoryService.getAllProducts();
					for (InventoryProductDto p1 : list) {
						System.out.println(p1.getProductId() + "  " + p1.getProductName() + "  " + p1.getQuantityInStock());
					}
					System.out.println("Enter Product ID to delete");
					int id=sc.nextInt();
					inventoryService.deleteInventory(id);
					inventoryService.deleteProduct(id);
					System.out.println("Product Has Been Removed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

				break;
			case 5:
				System.out.println("All Products ");
				try {
					list = inventoryService.getAllProducts();
					for (InventoryProductDto p1 : list) {
						System.out.println(p1.getProductId() + "  " + p1.getProductName() + "  " + p1.getQuantityInStock());
					}
					System.out.println("Enter Product ID to Update");
					int id=sc.nextInt();
					System.out.println("Enter No Of Quantity");
					int quantity=sc.nextInt();
					inventoryService.updateStock(id,LocalDate.now(),quantity);
					System.out.println("Update Successfull");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("All Products ");
				try {
					list = inventoryService.getAllProducts();
					for (InventoryProductDto p1 : list) {
						System.out.println(p1.getProductId() + "  " + p1.getProductName());
					}
					System.out.println("Enter product ID to check the Quantity");
					int productId1=sc.nextInt();
					System.out.println("Enter Quantity to Check");
					int quantity=sc.nextInt();
					Inventory i = inventoryService.getQuantity(productId1);
					boolean quantityAvailable=inventoryService.isQuantityAvailable(i.getQuantityInStock(),quantity);
					if(quantityAvailable) {
						System.out.println("Quantity in Stock..You Can Order :)");
					}
					else {
						System.out.println("Quantity Not In Stock..Sorry :(");
					}
				}
					catch (SQLException | ProductNotFoundException e) {
						e.printStackTrace();
					}
				break;
			case 7:
				System.out.println("All Products ");
				try {
					list = inventoryService.getAllProducts();
					for (InventoryProductDto p1 : list) {
						System.out.println(p1.getProductId() + "  " + p1.getProductName() + "  " + p1.getQuantityInStock());
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 8:
				System.out.println("Total Inventory Value");
				List<TotalInventoryValueDto> list1=new ArrayList<>();
				try {
					list1=inventoryService.getTotalValue();
					System.out.println("Product Name"+"  "+"Total Value");
					for(TotalInventoryValueDto i:list1) {
						
						System.out.println(i.getProductName()+"         "+i.getTotalValue()/100000+" Lakhs");
					}
					System.out.println("Total Value Of the Inventory : "+(inventoryService.getGrossInventoryTotal(list1))/10000000+" Cr");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case 9:
				System.out.println("Displaying Low Stock Products");
				List<InventoryProductDto> list2=new ArrayList<InventoryProductDto>();
				int threshold=40;
				try {
					list2=inventoryService.getLowStockProducts(threshold );
					for (InventoryProductDto i:list2) {
						System.out.println(i.getProductId() + "  " + i.getProductName() + "  " + i.getProductPrice() + "  "
								+ i.getQuantityInStock());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case 10:
				System.out.println("Displaying Out Of Stock Products");
				list2=new ArrayList<InventoryProductDto>();
				try {
					list2=inventoryService.getOutOfStockProducts();
					for (InventoryProductDto i:list2) {
						System.out.println(i.getProductId() + "  " + i.getProductName() + "  " + i.getProductPrice() + "  "
								+ i.getQuantityInStock());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		sc.close();

	}

}
