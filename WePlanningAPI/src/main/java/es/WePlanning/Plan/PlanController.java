package es.WePlanning.Plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import es.WePlanning.User.PlanService;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;

@RestController
public class PlanController {
	
	private static final String[] provinces = {"Álava","Albacete","Alicante","Almería","Asturias","Ávila","Badajoz","Barcelona","Burgos","Cáceres",
            "Cádiz","Cantabria","Castellón","Ciudad Real","Córdoba","La Coruña","Cuenca","Gerona","Granada","Guadalajara",
            "Guipúzcoa","Huelva","Huesca","Islas Baleares","Jaén","León","Lérida","Lugo","Madrid","Málaga","Murcia","Navarra",
            "Orense","Palencia","Las Palmas","Pontevedra","La Rioja","Salamanca","Segovia","Sevilla","Soria","Tarragona",
            "Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid","Vizcaya","Zamora","Zaragoza"};
	
	private static final String[] categories= {"Deportes","Música", "Cine","Fiestas","Naturaleza","Cultura"};
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ApiService imageService;
	@Autowired
	private PlanService planService;

	public interface PlanView extends Plan.BasicAtt, Plan.CommentAtt, Plan.UserAtt, User.BasicAtt, Comment.BasicAtt{}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans", method= RequestMethod.GET)
	public List<Plan> plans(){
		return planService.findAllPlans();
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/viewFriendsPlans", method= RequestMethod.GET)
	public List<Plan> plansFriends(){
		User u= userRepository.findById(userComponent.getLoggedUser().getId());
		if(!u.getFriends().isEmpty()){
		List<Plan> plans=planService.findFriendsPlans(u.getFriends());
		return plans;
		}
		else{
			return new ArrayList<>();
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/addPlan", method= RequestMethod.POST)
	public ResponseEntity<Plan> newPlan(@RequestBody Plan plan) {
		String[] categories= {"Deportes","Música","Cine","Fiestas","Naturaleza","Cultura"};
		
		if(Arrays.asList(provinces).contains(plan.getPlace()) && (Arrays.asList(categories).contains(plan.getCategory()))){
		
		plan.setAuthor(userComponent.getLoggedUser());
		plan.setId(0);
		plan.setImagePlanTitle("planDefault.jpg");
		planService.savePlan(plan);
		return new ResponseEntity<>(plan,HttpStatus.CREATED);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}", method= RequestMethod.GET)
	public ResponseEntity<Plan> planIndividual(@PathVariable long id){
		Plan plan= planService.findOne(id);
		if (plan != null) {
			return new ResponseEntity<>(plan, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Plan> planIndividualModify(@PathVariable long id, @RequestBody Plan planModified){
		Plan plan= planService.findOne(id);
		User userConnected=userRepository.findById(userComponent.getLoggedUser().getId());
		if (plan != null) {
			if (plan.getAuthor().getId().equals(userConnected.getId()) && Arrays.asList(provinces).contains(planModified.getPlace()) && (Arrays.asList(categories).contains(planModified.getCategory()))){
				planModified.setId(id);
				planModified.setAuthor(userConnected);
				planModified.setComments(plan.getComments());
				planModified.setAsistents(plan.getAsistents());
				planModified.setImagePlanTitle(plan.getImagePlanTitle());
				planService.savePlan(planModified);
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
		Plan plan= planService.findById(id);
		User userConnected=userRepository.findById(userComponent.getLoggedUser().getId());
		if (plan != null) {
			if (plan.getAuthor().getId().equals(userConnected.getId())){
				planService.deletePlan(plan);
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
		Plan plan= planService.findById(id);
		if (plan != null) {
				planService.deletePlan(plan);
				return new ResponseEntity<>(null, HttpStatus.OK);
		}
			else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/api/plans/{id}/assist", method= RequestMethod.PUT)
	public ResponseEntity<Plan> planIndividualAssist(@PathVariable long id, @RequestBody Plan planAssist){
		Plan plan= planService.findOne(id);
		if (plan != null) {
			User userConnected= userRepository.findById(userComponent.getLoggedUser().getId());
			if (!plan.getAsistents().contains(userConnected)){
			plan.getAsistents().add(userConnected);
			planService.savePlan(plan);
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
			Plan plan= planService.findOne(id);
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			if (plan != null) {
				User userConnected= userRepository.findById(userComponent.getLoggedUser().getId());
				comment.setId(0);
				comment.setAuthor(userConnected);
				comment.setDate(format.format(date));
				commentRepository.save(comment);
				plan.getComments().add(comment);
				planService.savePlan(plan);
				return new ResponseEntity<>(plan, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
	@JsonView(PlanView.class)
	@RequestMapping(value = "/api/plans/{id}/modifyPlanPhoto", method = RequestMethod.PUT)
	public ResponseEntity<Plan> modifyPlanPhoto(@PathVariable long id, @RequestParam("file") MultipartFile file) {

		Plan plan = planService.findById(id);
		User userConnected= userRepository.findById(userComponent.getLoggedUser().getId());
		if (plan.getId()==id && plan.getAuthor().getId().equals(userConnected.getId())) {
			
			boolean changed = imageService.getImg().changePhotoPlan(id, file);
			if(changed){
				
				plan.setImagePlanTitle(imageService.getImg().getFileName());
				planService.savePlan(plan);
	
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
		return planService.searchPlan(title, category, place);
	}
}


