package com.cg.dnd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.dnd.entity.ProductStock;


@Repository
public interface ProductStockRepo extends JpaRepository<ProductStock,Integer> {

    @Query("SELECT p.name FROM ProductStock p")
    List<String> getProductNames();
    
    @Query("SELECT p.stockId FROM ProductStock p")
    List<Integer> getProductIds();
    
}

