package es.WePlanning.Plan;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.ApiService;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.IndexController.PlanView;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;

import es.WePlanning.User.UserController.UserView;
@RestController
public class PlanController {
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	
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
	@Autowired
	private ApiService imageService;

	public interface PlanView extends Plan.BasicAtt, Plan.CommentAtt, Plan.UserAtt, User.BasicAtt, Comment.BasicAtt{}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans", method= RequestMethod.GET)
	public List<Plan> plans(){
		return planRepository.findAll();
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/viewFriendsPlans", method= RequestMethod.GET)
	public List<Plan> plansFriends(){
		User u= userRepository.findById(userComponent.getLoggedUser().getId());
		if(!u.getFriends().isEmpty()){
		List<Plan> plans=planRepository.findFriendsPlansNoPage(u.getFriends());
		return plans;
		}
		else{
			return new ArrayList<>();
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/addPlan", method= RequestMethod.POST)
	public ResponseEntity<Plan> newPlan(@RequestBody Plan plan) {
		plan.setAuthor(userComponent.getLoggedUser());
		plan.setId(0);
		planRepository.save(plan);
		return new ResponseEntity<>(plan,HttpStatus.CREATED);
	}
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}", method= RequestMethod.GET)
	public ResponseEntity<Plan> planIndividual(@PathVariable long id){
		Plan plan= planRepository.findOne(id);
		if (plan != null) {
			return new ResponseEntity<>(plan, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Plan> planIndividualModify(@PathVariable long id, @RequestBody Plan planModified){
		Plan plan= planRepository.findOne(id);
		User userConnected=userRepository.findById(userComponent.getLoggedUser().getId());
		if (plan != null) {
			if (plan.getAuthor().getId().equals(userConnected.getId())){
				planModified.setId(id);
				planModified.setAuthor(userConnected);
				planModified.setComments(plan.getComments());
				planModified.setAsistents(plan.getAsistents());
				planRepository.save(planModified);
				return new ResponseEntity<>(plan, HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Plan> deletePlan(@PathVariable long id){
		Plan plan= planRepository.findById(id);
		User userConnected=userRepository.findById(userComponent.getLoggedUser().getId());
		if (plan != null) {
			if (plan.getAuthor().getId().equals(userConnected.getId())){
				planRepository.delete(plan);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}
			else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/admin/plans/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Plan> deletePlanAdmin(@PathVariable long id){
		Plan plan= planRepository.findById(id);
		if (plan != null) {
				planRepository.delete(plan);
				return new ResponseEntity<>(null, HttpStatus.OK);
		}
			else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}/assist", method= RequestMethod.POST)
	public ResponseEntity<Plan> planIndividualAssist(@PathVariable long id){
		Plan plan= planRepository.findOne(id);
		if (plan != null) {
			User userConnected= userRepository.findById(userComponent.getLoggedUser().getId());
			if (!plan.getAsistents().contains(userConnected)){
			plan.getAsistents().add(userConnected);
			planRepository.save(plan);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			return new ResponseEntity<>(plan, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
	
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}/comment", method=RequestMethod.POST)
	public ResponseEntity<Plan> planIndividualComment(@PathVariable long id, @RequestBody Comment comment){
			Plan plan= planRepository.findOne(id);
			if (plan != null) {
				User userConnected= userRepository.findById(userComponent.getLoggedUser().getId());
				comment.setId(0);
				comment.setAuthor(userConnected);
				commentRepository.save(comment);
				plan.getComments().add(comment);
				planRepository.save(plan);
				return new ResponseEntity<>(plan, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
	@JsonView(PlanView.class)
	@RequestMapping(value = "/api/plans/{id}/modifyPlanPhoto", method = RequestMethod.PUT)
	public ResponseEntity<Plan> modifyPlanPhoto(@PathVariable long id, @RequestParam("file") MultipartFile file) {

		Plan plan = planRepository.findById(id);
		if (plan.getId()==id) {
			
			boolean changed = imageService.getImg().changePhotoPlan(id, file);
			if(changed){
				
				plan.setImagePlanTitle(imageService.getImg().getFileName());
				planRepository.save(plan);
	
				return new ResponseEntity<Plan>(plan, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/searchPlans/title={title}/category={category}/place={place}", method= RequestMethod.GET)
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
}
