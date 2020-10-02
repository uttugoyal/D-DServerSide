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
import com.cg.dnd.entity.ProductStock;
import com.cg.dnd.exception.RawMaterialException;
import com.cg.dnd.service.ProductStockService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/productStock")
public class ProductStockController {

	@Autowired
	ProductStockService service;

	@PostMapping("/addStock")
	public ResponseEntity<ProductStock> addStock(@RequestBody ProductStock stock) {
		ProductStock productStock=service.addStock(stock);
		return new ResponseEntity<>(productStock, HttpStatus.CREATED);

	}

	@GetMapping("/updateStock/{id}/{unit}/{price}/{quality}")
	public ResponseEntity<String> updateStock(@PathVariable int id, @PathVariable int unit, @PathVariable double price,
			@PathVariable String quality) throws RawMaterialException {
		String message= service.updateProductStock(id, unit, price, quality);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}


	@PostMapping("/addDistributor")
	public ResponseEntity<Distributor> addDistributor(@RequestBody Distributor distributor) throws RawMaterialException {
		Distributor distributer= service.addDistributor(distributor);
		return new ResponseEntity<>(distributer, HttpStatus.OK);

	}
	@GetMapping("/getDistributorsIds")
	public ResponseEntity<List<Integer>> getDistributorsIds() {
		List<Integer> distributorIdList= service.getDistributorsIds();
		return new ResponseEntity<>(distributorIdList, HttpStatus.OK);

	}

	@GetMapping("/getProductIds")
	public ResponseEntity<List<Integer>> getProductStockIds() {
		List<Integer> stockIdList= service.getProductStockIds();
		return new ResponseEntity<>(stockIdList, HttpStatus.OK);

	}

	@GetMapping("/getProductNames")
	public ResponseEntity<List<String>> getProductStockNames() {
		List<String> productNamesList= service.getProductStockNames();
		return new ResponseEntity<>(productNamesList, HttpStatus.OK);

	}
	
	@GetMapping("/getProductStock")
	public ResponseEntity<List<ProductStock>> getProductStock() {
		List<ProductStock> productStockList= service.getProductStock();
		return new ResponseEntity<>(productStockList, HttpStatus.OK);

	}


}
