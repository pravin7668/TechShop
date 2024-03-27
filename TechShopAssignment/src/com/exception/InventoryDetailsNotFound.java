package com.exception;

public class InventoryDetailsNotFound extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	public InventoryDetailsNotFound(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
}
