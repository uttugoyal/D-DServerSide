package com.cg.dnd.service;

import java.util.List;

import com.cg.dnd.entity.Distributor;
import com.cg.dnd.entity.ProductStock;
import com.cg.dnd.exception.RawMaterialException;


public interface ProductStockService {

	public ProductStock addStock(ProductStock stock);

	public String updateProductStock(int id, int unit, double pricePerUnit, String qualityCheck) throws RawMaterialException;

	public List<Integer> getDistributorsIds();
	public Distributor addDistributor(Distributor supplier) throws RawMaterialException ;
	
	public List<Integer> getProductStockIds();
	
	public List<String> getProductStockNames();
	public List<ProductStock> getProductStock();
}
