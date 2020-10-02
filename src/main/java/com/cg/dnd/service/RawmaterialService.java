package com.cg.dnd.service;

import java.util.List;

import com.cg.dnd.entity.RawMaterialOrder;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.RawMaterialException;


public interface RawmaterialService {

	public boolean doesRawMaterialOrderIdExists(int id);

	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order) throws RawMaterialException;

	public RawMaterialOrder trackRawmaterialOrder(int id) throws RawMaterialException;
	public String updateRawmaterialOrder(int id, String status) throws RawMaterialException ;

	public List<Supplier> getAllSupplier();

	public Supplier getSupplierById(int id);
	

	
	public List<RawMaterialOrder> getAllOrders();

}
