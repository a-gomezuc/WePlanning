package es.WePlanning.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;

@Service
public class PlanService {
	@Autowired
	private PlanRepository planRepository;
	
	public void savePlan(Plan plan){
		planRepository.save(plan);
	}
	
	public Plan findOne(long id){
		return planRepository.findOne(id);
	}
	
	public Page<Plan> findByAuthorIdPageable(String id, Pageable page){
		return planRepository.findByAuthorId(id,page);
	}
	
	public List<Plan> findAllPlans(){
		return planRepository.findAll();
	}
	public Page<Plan> findAllPlansPageable(Pageable page){
		return planRepository.findAll(page);
	}
	
	public Plan findById(long id){
		return planRepository.findById(id);
	}
	
	public List<Plan> findFriendsPlans (List<User> friends){
		return planRepository.findFriendsPlansNoPage(friends);
	}
	public Page<Plan> findFriendsPlansPageable (List<User> friends, Pageable page){
		return planRepository.findFriendsPlans(friends, page);
	}
	
	public void deletePlan(Plan plan){
		planRepository.delete(plan);
	}
	
	public List<Plan> searchPlan(String title, String category, String place){
		if ((title.equals("")) && (category.equals("")) && (place.equals(""))) {
			return  planRepository.findAll();
			
		}else if ((!title.equals("")) && (!category.equals("")) && (place.equals(""))) {
			return planRepository.findByTitleAndCategoryIgnoreCase(title, category);
			
		}else if ((title.equals("")) && (!category.equals("")) && (!place.equals(""))) {
			return  planRepository.findByCategoryAndPlaceIgnoreCase(category, place);
			
		}else if  ((!title.equals("")) && (category.equals("")) && (!place.equals(""))){
			return planRepository.findByTitleAndPlaceIgnoreCase(title, place);
			
		}else if  ((!title.equals("")) && (category.equals("")) && (place.equals(""))){
			return planRepository.findByTitleIgnoreCase(title);

		}else if ((title.equals("")) && (!category.equals("")) && (place.equals(""))){
			return planRepository.findByCategoryIgnoreCase(category);
			
		}else if ((title.equals("")) && (category.equals("")) && (!place.equals(""))){
			return planRepository.findByPlaceIgnoreCase(place);
			
		}else if (!(title.equals("")) && (!category.equals("")) && (!place.equals(""))){
			return planRepository.findByTitleAndCategoryAndPlaceIgnoreCase(title,category,place);
		}
		return null;
	}
	

}
