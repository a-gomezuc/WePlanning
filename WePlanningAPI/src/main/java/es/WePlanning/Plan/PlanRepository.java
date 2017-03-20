package es.WePlanning.Plan;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.WePlanning.User.User;

public interface PlanRepository extends JpaRepository<Plan, Long>{
	
	Plan findById(long id);
	List<Plan> findByTitleIgnoreCase(String title);
	List<Plan> findByCategoryIgnoreCase(String category);
	List<Plan> findByPlaceIgnoreCase(String place);
	List<Plan> findByTitleAndCategoryIgnoreCase(String title, String category);
	List<Plan> findByTitleAndPlaceIgnoreCase(String title, String place);
	List<Plan> findByCategoryAndPlaceIgnoreCase(String category, String place);
	List<Plan> findByTitleAndCategoryAndPlaceIgnoreCase(String title, String category, String place);
	Page<Plan> findByAuthorId(String id, Pageable page);
	
	@Query("SELECT p FROM Plan p WHERE p.author IN :u")
	Page<Plan> findFriendsPlans(@Param("u")List<User> u,Pageable page);
}
