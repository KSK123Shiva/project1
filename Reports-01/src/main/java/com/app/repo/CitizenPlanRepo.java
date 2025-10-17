package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.CitizenPlan;

@Repository
public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

	@Query("select DISTINCT(planName) from CitizenPlan")
	public List<String> getPlanName();

	@Query("select DISTINCT(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
	
	

}
