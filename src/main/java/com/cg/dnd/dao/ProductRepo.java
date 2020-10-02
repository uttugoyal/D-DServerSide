package com.cg.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dnd.entity.ProductOrder;

@Repository
public interface ProductRepo extends JpaRepository<ProductOrder,Integer>{
	
	@Modifying
    @Query("UPDATE ProductOrder o SET o.deliveryStatus = :status WHERE o.orderId = :orderId")
    int updateStatus(@Param("orderId") int orderId, @Param("status") String status);
	

}
