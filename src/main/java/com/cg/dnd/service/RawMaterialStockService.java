package com.cg.dnd.service;

import java.util.List;

import com.cg.dnd.entity.RawMaterialStock;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.RawMaterialStockException;


public interface RawMaterialStockService {

	public RawMaterialStock addStock(RawMaterialStock stock) throws RawMaterialStockException;

	public String updateRawStock(int id, int unit, double pricePerUnit, String qualityCheck);

	
	public Supplier addSupplier(Supplier supplier) throws RawMaterialStockException;
	
	public List<String> getRawmaterialNames();
	
	public List<Integer> getRawmaterialIds();
}
