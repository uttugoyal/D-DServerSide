package com.cg.dnd.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dnd.dao.DistributorRepo;
import com.cg.dnd.dao.ProductStockRepo;
import com.cg.dnd.entity.Distributor;
import com.cg.dnd.entity.ProductStock;
import com.cg.dnd.exception.ProductException;
import com.cg.dnd.exception.RawMaterialException;


@Service
public class ProductStockServiceImpl implements ProductStockService {

	@Autowired
	ProductStockRepo productRepo;

	@Autowired
	DistributorRepo distributorRepo;

	@Transactional
	@Override
	public ProductStock addStock(ProductStock stock) {
		stock.setProcessDate(LocalDate.now());
		stock.setPrice(stock.getQuantityUnit()*stock.getPricePerUnit());
		return productRepo.saveAndFlush(stock);
	}

	@Transactional
	@Override
	public String updateProductStock(int id, int unit, double pricePerUnit, String qualityCheck) throws RawMaterialException {
		String message=null;
		if (productRepo.existsById(id)) {
			ProductStock stock=productRepo.getOne(id);
			stock.setQualityCheck(qualityCheck);
			stock.setPricePerUnit(pricePerUnit);
			stock.setQuantityUnit(stock.getQuantityUnit()+unit);
			stock.setPrice(stock.getQuantityUnit()*pricePerUnit);
			productRepo.saveAndFlush(stock);
			message= "Successfully updated";
		}
		else {
			throw new RawMaterialException("updation failed ! Enter valid stockId");
		}
		return message;
	}

	
	@Transactional
	@Override
	public Distributor addDistributor(Distributor distributor) throws RawMaterialException {
		
		Distributor distributor1= distributorRepo.saveAndFlush(distributor);
		if(distributor1==null) {
			throw new RawMaterialException("Unable to add distributor");
		}
		return distributor1;
	}

	@Transactional
	@Override
	public List<Integer> getProductStockIds() {
		return productRepo.getProductIds();
	}
	@Transactional
	@Override
	public List<Integer> getDistributorsIds() {
		return distributorRepo.getDistributorsIds();
	}

   
	@Transactional
	@Override
	public List<String> getProductStockNames() {
		return productRepo.getProductNames();
	}


	@Transactional
	@Override
	public List<ProductStock> getProductStock(){
		return productRepo.findAll();
	}
}
