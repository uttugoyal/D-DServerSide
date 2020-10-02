package com.cg.dnd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.dnd.entity.Distributor;



@Repository
public interface DistributorRepo extends JpaRepository<Distributor,Integer>{
	@Query("SELECT p.distributorId FROM Distributor p")
    List<Integer> getDistributorsIds();
}
