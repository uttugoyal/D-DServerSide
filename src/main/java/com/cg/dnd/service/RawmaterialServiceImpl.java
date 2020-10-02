package com.cg.dnd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dnd.dao.RawMaterialOrderRepo;
import com.cg.dnd.dao.SupplierRepo;
import com.cg.dnd.entity.RawMaterialOrder;
import com.cg.dnd.entity.Supplier;
import com.cg.dnd.exception.ProductException;
import com.cg.dnd.exception.RawMaterialException;

@Service
public class RawmaterialServiceImpl implements RawmaterialService {

	@Autowired
	RawMaterialOrderRepo orderRepo;
	
	@Autowired
	SupplierRepo supplierRepo;


	@Override
	public boolean doesRawMaterialOrderIdExists(int id) {
		return orderRepo.existsById(id);
	}

	@Override
	@Transactional
	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order) throws RawMaterialException{
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		RawMaterialOrder rawMaterialOrder =orderRepo.saveAndFlush(order);
		if(rawMaterialOrder==null) {
			throw new RawMaterialException("Raw Material Not Ordered !");	
		}
		return rawMaterialOrder;
	}

	@Override
	@Transactional
	public RawMaterialOrder trackRawmaterialOrder(int id) throws RawMaterialException{
		if (orderRepo.existsById(id)) {
			RawMaterialOrder order=orderRepo.findById(id).orElse(null);
			return order;
		}
		else {
			throw new RawMaterialException("No order found with this order Id");
		}
	}

	@Override
	@Transactional
	public String updateRawmaterialOrder(int id, String status) throws RawMaterialException {
		String message=null;
		if (orderRepo.existsById(id)) {
			orderRepo.updateStatus(id, status);
			message="updated";
		}
		else {
			throw new RawMaterialException("Updation Failed !");
		}
		return message;
	}

	@Override
	@Transactional
	public List<Supplier> getAllSupplier() {
		return supplierRepo.findAll();
	}

	@Override
	@Transactional
	public Supplier getSupplierById(int id) {
		if (orderRepo.existsById(id)) {
			return supplierRepo.findById(id).orElse(null);
		}
		return new Supplier();
		
	}

	
	@Override
	@Transactional
	public List<RawMaterialOrder> getAllOrders() {
		return orderRepo.findAll();
	}


}
