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

import com.cg.dnd.entity.RawMaterialOrder;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.RawMaterialException;
import com.cg.dnd.service.RawmaterialService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/rawOrder")
public class RawmaterialOrderController {

	@Autowired
	RawmaterialService service;
	RawMaterialOrder order;

	@PostMapping("/addOrder")
	public ResponseEntity<RawMaterialOrder> addOrder(@RequestBody RawMaterialOrder order) throws RawMaterialException {
		RawMaterialOrder rawMaterialOrder=service.saveRawmaterialOrder(order) ;
		return new ResponseEntity<>(rawMaterialOrder, HttpStatus.CREATED);

	}

	@GetMapping("/updateOrder/{id}/{status}")
	public ResponseEntity<String> updateOrder(@PathVariable int id, @PathVariable String status) throws RawMaterialException{
		String message=service.updateRawmaterialOrder(id, status);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@GetMapping("/trackOrder/{id}")
	public ResponseEntity<RawMaterialOrder> trackOrder(@PathVariable int id) throws RawMaterialException{
		RawMaterialOrder order=service.trackRawmaterialOrder(id);
		return new ResponseEntity<>(order, HttpStatus.OK);

	}

	@GetMapping("/supplier/{id}")
	public ResponseEntity<Supplier> getSupplier(@PathVariable int id) {
		Supplier supplier=service.getSupplierById(id);
		return new ResponseEntity<>(supplier, HttpStatus.OK);

	}

	

	@GetMapping("/getSuppliers")
	public ResponseEntity<List<Supplier>> getSuppliers() {
		List<Supplier> suppliersList= service.getAllSupplier();
		return new ResponseEntity<>(suppliersList, HttpStatus.OK);

	}

	@GetMapping("/getRawmaterialOrders")
	public ResponseEntity<List<RawMaterialOrder>> getAllOrders() {
		List<RawMaterialOrder> rawMaterialOrderList= service.getAllOrders();
		return new ResponseEntity<>(rawMaterialOrderList, HttpStatus.OK);

	}

}
