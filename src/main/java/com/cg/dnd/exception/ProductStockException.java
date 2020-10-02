package com.cg.dnd.exception;

public class ProductStockException extends Exception{
	private static final long serialVersionUID = 1L;

	public ProductStockException() {

	}

	public ProductStockException(String message) {
		super(message);
	}
}