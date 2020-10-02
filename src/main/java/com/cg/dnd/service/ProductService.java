package com.cg.dnd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dnd.entity.Distributor;
import com.cg.dnd.entity.ProductOrder;
import com.cg.dnd.exception.ProductException;


@Service
public interface ProductService  {

	public boolean doesProductOrderIdExists(int id) throws ProductException;

	public ProductOrder saveProductOrder(ProductOrder order) ;

	public ProductOrder trackProductOrder(int id) throws ProductException;

	public String updateProductOrder(int id, String status) throws ProductException;

	public List<Distributor> getAllDistributor();

	public Distributor getDistributorById(int id) throws ProductException;
	public List<ProductOrder> getProductOrders() throws ProductException;
	

}
