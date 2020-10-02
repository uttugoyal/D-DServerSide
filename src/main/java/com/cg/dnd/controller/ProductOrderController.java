package com.cg.dnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dnd.entity.Distributor;
import com.cg.dnd.entity.ProductOrder;
import com.cg.dnd.exception.ProductException;
import com.cg.dnd.service.ProductService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/productOrder")
public class ProductOrderController {

	@Autowired
	ProductService service;

	@PostMapping("/addOrder")
	public ResponseEntity<ProductOrder> addOrder(@RequestBody ProductOrder order) {
		ProductOrder productOrder=service.saveProductOrder(order);
		return new ResponseEntity<>(productOrder, HttpStatus.CREATED);
	}



	@GetMapping("/getDistributors")
	public ResponseEntity<List<Distributor>> getDistributor() {
		List<Distributor> distributerList = service.getAllDistributor();
		return new ResponseEntity<>(distributerList, HttpStatus.OK);

	}

	@GetMapping("/getProductOrders")
	public ResponseEntity<List<ProductOrder>> getProductOrders() throws ProductException{
		List<ProductOrder> productOrdersList= service.getProductOrders();
		return new ResponseEntity<>(productOrdersList, HttpStatus.OK);
	}
	
	@GetMapping("/trackOrder/{pid}")
	public ResponseEntity<ProductOrder> trackProductOrder(@PathVariable("pid") int id)
			throws ProductException {
		   ProductOrder order = null;

		order = service.trackProductOrder(id);

		return new ResponseEntity<>(order, HttpStatus.OK);

	}


	@GetMapping("/updateOrder/{id}/{status}")
	public ResponseEntity<String> updateOrder(@PathVariable int id, @PathVariable String status) throws ProductException{
		String message=service.updateProductOrder(id, status);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}
