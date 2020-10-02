package com.cg.dnd.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dnd.dao.DistributorRepo;
import com.cg.dnd.dao.ProductRepo;
import com.cg.dnd.entity.Distributor;
import com.cg.dnd.entity.ProductOrder;
import com.cg.dnd.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	DistributorRepo disRepo;
	
	@Autowired
	ProductRepo productRepo;
	


	@Override
	public boolean doesProductOrderIdExists(int id){
		return productRepo.existsById(id);
	}

	@Override
	@Transactional
	public ProductOrder saveProductOrder(ProductOrder order) {
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		return productRepo.saveAndFlush(order);
	}

	@Override
	@Transactional
	public ProductOrder trackProductOrder(int id) throws ProductException{
		if (productRepo.existsById(id)) {
			ProductOrder order=productRepo.findById(id).orElse(null);
			return order;
		}
		else {
			throw new ProductException("No order found with this order Id");
		}
	}

	@Override
	@Transactional
	public String updateProductOrder(int id, String status) throws ProductException{
		if (productRepo.existsById(id)) {
			productRepo.updateStatus(id, status);
			
			return "updated Successfully";
		}
		
		else {
			throw new ProductException("Updation Failed");
		}
	}

	@Override
	@Transactional
	public List<Distributor> getAllDistributor() {
		return disRepo.findAll();
		
	}



	@Override
	@Transactional
	public Distributor getDistributorById(int id) throws ProductException {
		Distributor distributor=null;
		if(distributor!=null) {
			distributor= disRepo.getOne(id);
		}
		else {
			throw new ProductException("Distributor with this Id.. not found");
		}
		return distributor;
	}

	@Override
	@Transactional
	public List<ProductOrder> getProductOrders() throws ProductException{
		return productRepo.findAll();
	}

}
