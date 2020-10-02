package com.cg.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.dnd.entity.Supplier;



public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
