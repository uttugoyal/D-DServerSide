package com.cg.dnd.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "rawmaterial_stocks")
@SequenceGenerator(name="seq", initialValue=1001, allocationSize=100)
public class RawMaterialStock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "pk_stock_id")
	int stockId;

	@Column(name = "name")
	@NotNull
	String name;

	@Column(name = "price_per_unit")
	@NotNull
	double pricePerUnit;

	@Column(name = "quantity_unit")
	@NotNull
	int quantityUnit;

	@Column(name = "price")
	@NotNull
	double price;

	@Column(name = "manufacturing_date")
	@NotNull
	LocalDate manufacturingDate;

	@Column(name = "expiry_date")
	@NotNull
	LocalDate expiryDate;

	@Column(name = "quality_check")
	@NotNull
	String qualityCheck;

	@Column(name = "process_date")
	@NotNull
	LocalDate processDate;

	public RawMaterialStock() {
	}

	
	public int getStockId() {
		return stockId;
	}


	public void setStockId(int stockId) {
		this.stockId = stockId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}


	public int getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}




	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getQualityCheck() {
		return qualityCheck;
	}

	public void setQualityCheck(String qualityCheck) {
		this.qualityCheck = qualityCheck;
	}

	public LocalDate getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDate processDate) {
		this.processDate = processDate;
	}

}
