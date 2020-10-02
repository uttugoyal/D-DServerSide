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

import com.cg.dnd.entity.RawMaterialStock;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.RawMaterialStockException;
import com.cg.dnd.service.RawMaterialStockService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/rawStock")
public class RawMaterialStockController {

	@Autowired
	RawMaterialStockService service;

	@PostMapping("/addStock")
	public ResponseEntity<RawMaterialStock> addStock(@RequestBody RawMaterialStock stock) throws RawMaterialStockException {
		RawMaterialStock rawMaterialStock = service.addStock(stock);
		return new ResponseEntity<>(rawMaterialStock, HttpStatus.CREATED);

	}

	@GetMapping("/updateStock/{id}/{unit}/{price}/{quality}")
	public ResponseEntity<String> updateStock(@PathVariable int id, @PathVariable int unit, @PathVariable double price,
			@PathVariable String quality) {
		String message= service.updateRawStock(id, unit, price, quality);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}



	@PostMapping("/addSupplier")
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) throws RawMaterialStockException {
		Supplier ssupplier =service.addSupplier(supplier);
		return new ResponseEntity<>(ssupplier, HttpStatus.CREATED);

	}

	@GetMapping("/getRawMaterialNames")
	public ResponseEntity<List<String>> getRawmaterialNames() {
		List<String> rawMaterialsNames= service.getRawmaterialNames();
		return new ResponseEntity<>(rawMaterialsNames, HttpStatus.OK);

	}

	@GetMapping("/getRawMaterialIds")
	public ResponseEntity<List<Integer>> getRawMaterialIds() {
		List<Integer> rawMAterialIds= service.getRawmaterialIds();
		return new ResponseEntity<>(rawMAterialIds, HttpStatus.OK);

	}

}
