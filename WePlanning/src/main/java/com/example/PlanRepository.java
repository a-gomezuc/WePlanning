package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long>{
	
	List<Plan> findById(long id);
	List<Plan> findByTitleIgnoreCase(String title);
	List<Plan> findByCategoryIgnoreCase(String category);
	List<Plan> findByPlaceIgnoreCase(String place);
	List<Plan> findByTitleAndCategoryIgnoreCase(String title, String category);
	List<Plan> findByTitleAndPlaceIgnoreCase(String title, String place);
	List<Plan> findByCategoryAndPlaceIgnoreCase(String category, String place);
	List<Plan> findByTitleAndCategoryAndPlaceIgnoreCase(String title, String category, String place);
}
