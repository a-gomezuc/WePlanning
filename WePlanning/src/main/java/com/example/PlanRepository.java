package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long>{
	List<Plan> findById(long id);
	List<Plan> findByTitle(String title);
	

}
