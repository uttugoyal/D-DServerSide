package com.cg.dnd.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dnd.dao.RawMaterialStockRepo;
import com.cg.dnd.dao.SupplierRepo;
import com.cg.dnd.entity.RawMaterialStock;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.RawMaterialException;
import com.cg.dnd.exception.RawMaterialStockException;

@Service
public class RawMaterialStockServiceImpl implements RawMaterialStockService {

	@Autowired
	RawMaterialStockRepo rawRepo;
	
	@Autowired
	SupplierRepo supRepo;

	
	@Override
	@Transactional
	public RawMaterialStock addStock(RawMaterialStock stock) throws RawMaterialStockException {
		double totalPrice = stock.getPricePerUnit() * stock.getQuantityUnit();
		stock.setProcessDate(LocalDate.now());
		stock.setPrice(totalPrice);
		RawMaterialStock rawMaterialStock=rawRepo.saveAndFlush(stock);
		if(rawMaterialStock==null) {
			throw new RawMaterialStockException("Raw Material Not Added to stock");
		}
		return rawMaterialStock;
	}

	@Override
	@Transactional
	public String updateRawStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		if (rawRepo.existsById(id)) {
			RawMaterialStock stock=rawRepo.getOne(id);
			stock.setQualityCheck(qualityCheck);
			stock.setQuantityUnit(unit);
			stock.setPricePerUnit(pricePerUnit);
			stock.setPrice(unit*pricePerUnit);
			rawRepo.saveAndFlush(stock);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	@Transactional
	public Supplier addSupplier(Supplier supplier) throws RawMaterialStockException{
		Supplier supplier1= supRepo.saveAndFlush(supplier);
		if(supplier1==null) {
			throw new RawMaterialStockException("Supplier Not Added !");
		}
		return supplier1;
	}

	
	@Override
	@Transactional
	public List<String> getRawmaterialNames() {
		return rawRepo.getRawMaterialNames();
	}

	@Override
	@Transactional
	public List<Integer> getRawmaterialIds() {
		return rawRepo.getRawMaterialIds();
	}


}
