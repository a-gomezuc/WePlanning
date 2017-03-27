package es.WePlanning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;
import es.WePlanning.Plan.PlanController.PlanView;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;
import es.WePlanning.User.UserController.UserView;

@RestController
@RequestMapping("/api")
public class IndexController {
	
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ContactRepository contactRepository;
	
	public interface PlanView extends Plan.BasicAtt, Plan.CommentAtt, Plan.UserAtt, User.BasicAtt, Comment.BasicAtt{}
	public interface UserView extends User.BasicAtt, User.PlansAtt, Plan.BasicAtt, User.RolesAtt {}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/searchPlans/title={title}/category={category}/place={place}", method= RequestMethod.GET)
	public List<Plan> searchplans(@PathVariable String title,@PathVariable String category,@PathVariable String place){
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
			
		}
		return null;
	}
	
	@JsonView(UserView.class)
	@RequestMapping(value="/searchUsers/filter={filter}/usearch={usearch}", method= RequestMethod.GET)
	public List<User> searchUser(@PathVariable String filter,@PathVariable String usearch){
		
			if ((!usearch.equals("")) && (filter.equals("ident"))) {
				ArrayList<User> resultados= new ArrayList<>();
				User userId=userRepository.findByIdIgnoreCase(usearch);
				if (userId!=null)
				resultados.add(userId);
				return resultados;
				
			}else if ((usearch.equals("")) && (filter.equals("name"))) {
				return userRepository.findByUnameIgnoreCase(usearch);
				
			}else if ((usearch.equals("")) && (filter.equals("province"))) {
				return userRepository.findByProvinceIgnoreCase(usearch);
			}else
				return userRepository.findAll();
			
	
	}
}
