package com.cg.dnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = ProductException.class)
	public ResponseEntity<Object> handleInvalidException(ProductException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ProductStockException.class)
	public ResponseEntity<Object> handleInvalidException(ProductStockException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RawMaterialException.class)
	public ResponseEntity<Object> handleInvalidException(RawMaterialException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RawMaterialStockException.class)
	public ResponseEntity<Object> handleInvalidException(RawMaterialStockException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
