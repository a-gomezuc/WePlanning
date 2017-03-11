package com.example;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanController {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserComponent userComponent;

	@PostConstruct
	public void init() {
		User miguelito= new User(false,"miguel99", "Miguel", "Muñoz", "Vitoria", 25, "miguelon@gmail.com", "miContraseñaM", "ROLE_USER");
		User joselito = new User(false,"joselito_95", "José", "López", "Madrid", 25, "jose@gmail.com", "miContraseña", "ROLE_USER");
		User guillermito = new User(false,"westernsquad", "Guille", "Navas", "Toledo", 22, "guillermitonavitas@gmail.com",
				"mmm", "ROLE_USER");
		User desnet = new User(true,"desnet", "DesNet", "Company", "Madrid", 1, "desnet@gmail.com",
				"perrete", "ROLE_USER");
		userRepository.save(miguelito);
		userRepository.save(guillermito);
		userRepository.save(desnet);
		joselito.getFriends().add(guillermito);
		userRepository.save(joselito);
		guillermito.getFriends().add(joselito);
		userRepository.save(guillermito);
		for (int i = 0; i <30; i++) {
			Plan planprueba = (new Plan("Torneo LOL", "Cultura", "Madrid", "URJC Móstoles", i, "1/03/2017",
					"Torneo del videojuego más famoso de la carrera de Ingeniería del Software"));
			planprueba.setAuthor(joselito);
			planRepository.save(planprueba);
		}
		for (int i = 0; i <12; i++) {
			Plan planprueba = (new Plan("Desarrollo web", "Cultura", "Las Palmas", "Universidad de Gran Canaria", i, "1/06/2017",
					"Vamos a aprender a desarrollar una web"));
			planprueba.setAuthor(desnet);
			planRepository.save(planprueba);
		}
		Plan planpruebaG = new Plan("Carrera", "Deportes", "Madrid", "URJC Vicálvaro", 12, "12/03/2017",
				"Running en la universidad.");
		planpruebaG.setAuthor(guillermito);
		
		
		Plan planpruebaJ=new Plan("Curso universidad", "Cultura", "Albacete", "Universidad de Albacete", 250, "15/03/2017",
				"Curso de programación web");
		planpruebaJ.setAuthor(miguelito);
		planRepository.save(planpruebaJ);
		Comment comentario1=new Comment ("10/1/2017", "Me ha gustado mucho");
		comentario1.setAuthor(joselito);
		Comment comentario2=new Comment ("10/2/2016", "Menuda castaña de plan");
		comentario2.setAuthor(miguelito);
		commentRepository.save(comentario1);
		commentRepository.save(comentario2);
		planpruebaG.getComments().add(comentario1);
		planpruebaG.getComments().add(comentario2);
		planpruebaG.getAsistents().add(guillermito);
		planRepository.save(planpruebaG);
	}

	public PlanController() {
	}

	@RequestMapping("/")
	public String start(Model model, Pageable page) {
		Page<Plan> planes = planRepository.findAll(new PageRequest(0,10));
		model.addAttribute("planes", planes);
		model.addAttribute("showButton", !planes.isLast());
		return "index";
	}
	@RequestMapping("/morePlans")
	public String moreStart(Model model, @RequestParam int page){
		Page<Plan> planes= planRepository.findAll(new PageRequest(page,10));
		model.addAttribute("planes", planes);
		return "plansList";
		
	}
	@RequestMapping("/morePlansUser")
	public String moreStartUser(Model model, @RequestParam int page, @RequestParam String id){
		Page<Plan> planes= planRepository.findByAuthorId(id, new PageRequest(page,10));
		model.addAttribute("plans", planes);
		return "plansListUser";
		
	}
	@RequestMapping("/morePlansUserLogged")
	public String moreStartUserLogged(Model model, @RequestParam int page, @RequestParam String id){
		Page<Plan> planes= planRepository.findByAuthorId(id, new PageRequest(page,10));
		model.addAttribute("plans", planes);
		return "plansListUserLogged";
		
	}
	
	
	@RequestMapping("/logged")
	public String startLogged(Model model, Pageable page) {
//		Page<Plan> planes = planRepository.findAll(page);
//		model.addAttribute("planes", planes);
//		model.addAttribute("size", planes.getSize() + 10);
//		model.addAttribute("showButton", !planes.isLast());
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		model.addAttribute("id",userComponent.getLoggedUser().getId());
		return "index-logged";
	}
	@RequestMapping("/plan/{id}")
	public String retPlan(Model model, @PathVariable long id) {
		Plan planActual=planRepository.findOne(id);
		int asistentes=planActual.getAsistents().size();
		boolean noExistComment= planActual.getComments().isEmpty();
		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("numAsistents",asistentes);
		model.addAttribute("plan",planActual);
		return "plan";
	}
	
	@RequestMapping("/logged/plan/{id}")
	public String retPlanLogged(Model model, @PathVariable long id){
		Plan planActual=planRepository.findOne(id);
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		boolean assist = false;
				if(planActual.getAsistents().contains(user)){
					assist=true;
				}
		boolean noAssist = !assist;
		int asistentes=planActual.getAsistents().size();
		boolean noExistComment= planActual.getComments().isEmpty();
		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("numAsistents",asistentes);
		model.addAttribute("assist", assist);
		model.addAttribute("plan",planActual);
		model.addAttribute("noAssist",noAssist);
		return "plan-logged";
	}
	@RequestMapping (value="/logged/plan/{id}/addComment", method = RequestMethod.POST)
	public String addComments(Model model, @PathVariable long id, String cont){
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		Plan plan = planRepository.findOne(id);
		Comment comment = new Comment("1/1/1", cont);
		comment.setAuthor(userComponent.getLoggedUser());
		commentRepository.save(comment);
		plan.getComments().add(comment);	
		planRepository.save(plan);
		return "SuccessfulComment";
	}
	@RequestMapping(value="/logged/plan/{id}/assist", method = RequestMethod.POST)
	public String assistPlan(Model model, @PathVariable long id){
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		Plan plan = planRepository.findOne(id);
		User user=userComponent.getLoggedUser();
		plan.getAsistents().add(user);
		userRepository.save(user);
		planRepository.save(plan);
		return "SuccesfulAssist";
	}
	@RequestMapping("/user/{id}")
	public String retUser(Model model, @PathVariable String id) {
		User user=userRepository.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("plansUser",planRepository.findByAuthorId(id, new PageRequest(0,10)));
		if(!user.isSponsor()){
		return "ProfileHTML";
		}
		else{
			return "SponsorHTML";
		}
	}
	@RequestMapping("logged/user/{id}")
	public String retUserLogged(Model model, @PathVariable String id) {
		User usuario=userRepository.findById(id);
		model.addAttribute("user", usuario);
		model.addAttribute("plansUser",planRepository.findByAuthorId(id, new PageRequest(0,10)));
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		model.addAttribute("AllUsers",userRepository.findAll());
		if(id.equals(userComponent.getLoggedUser().getId())&&(!usuario.isSponsor())){
			return "ProfileHTML-logged";
		}
		else if(!id.equals(userComponent.getLoggedUser().getId())&&(!usuario.isSponsor())){
			return "ProfileHTML-viewlogged";
		}
		else if(id.equals(userComponent.getLoggedUser().getId())&&(usuario.isSponsor())){
			return "SponsorHTML-logged";
		}
		else{
			return "SponsorHTML-viewlogged";
		}

	}
	@RequestMapping("/aboutus")
	public String aboutUs() {
		return "aboutus";

	}
	@RequestMapping("/logged/aboutus")
	public String loggedAboutUs(Model model) {
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "aboutus-logged";

	}
	@RequestMapping("/contact")
	public String contact() {
		return "contact";

	}
	@RequestMapping("/logged/contact")
	public String loggedContact(Model model) {
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "contact-logged";

	}
	@RequestMapping("/register")
	public String register() {
		return "register";

	}
	@RequestMapping("/logged/register")
	public String loggedRegister(Model model) {
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "register";

	}
	@RequestMapping(value="/registerUser",  method = RequestMethod.POST)
	public String registerUser (boolean sponsorCheckbox, String name, String surname, int age,
			String province, String username, String email, String pass){	
			User user =new User(sponsorCheckbox,username ,name, surname ,province, age, email, pass, "ROLE_USER");
			userRepository.save(user);
			return "SuccesfulRegister";
	}
	@RequestMapping("/newPlan")
	public String newPlan(Model model) {
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "NewPlan";

	}
	@RequestMapping("/createPlan")
	public String createPlan(Model model, Plan plan){
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		plan.setAuthor(userComponent.getLoggedUser());
		planRepository.save(plan);
		model.addAttribute("planes",plan);
		model.addAttribute("id",plan.getAuthor().getId());
		model.addAttribute("idPlan",plan.getId());
		model.addAttribute("title",plan.getTitle());
		
		return"SuccessfulPlan";
	}
	
	@RequestMapping("/logged/user/{id}/searchUsers")
	public String searchAnUser(Model model, @PathVariable String id,String usearch, String filter){
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		model.addAttribute("user",userRepository.findById(id));
		ArrayList<User> users;
		User u;
		boolean noUsers;
		if((!usearch.equals(""))&&(filter.equals("name"))){
			users=(ArrayList<User>)userRepository.findByUnameIgnoreCase(usearch);
			model.addAttribute("AllUsers",users);
			noUsers=users.isEmpty();
			model.addAttribute("noUsers",noUsers);
			
		}else if((!usearch.equals(""))&&(filter.equals("ident"))){
			u=userRepository.findByIdIgnoreCase(usearch);
			model.addAttribute("AllUsers",u);
			noUsers=(u.equals(""));
			model.addAttribute("noUsers",noUsers);
			
		}
		else if((!usearch.equals(""))&&(filter.equals("province"))){
			users=(ArrayList<User>)userRepository.findByProvinceIgnoreCase(usearch);
			model.addAttribute("AllUsers",users);
			noUsers=users.isEmpty();
			model.addAttribute("noUsers",noUsers);
			
		}		
		else{
			users=(ArrayList<User>)userRepository.findAll();
			model.addAttribute("AllUsers",users);
			noUsers=users.isEmpty();
			model.addAttribute("noUsers",noUsers);
			
		}
		return "ProfileHTML-logged";
		}
	
	@RequestMapping("/searchPlans")
	public String searchbyTitle(Model model, String title,String category, String place){
			ArrayList<Plan> planes;
			boolean noPlanes;
			
			if((!title.equals(""))&&(!category.equals(""))&&(!place.equals(""))){//title, category and place
				planes=(ArrayList<Plan>)planRepository.findByTitleAndCategoryAndPlaceIgnoreCase(title, category, place);
				model.addAttribute("planes",planes);
				noPlanes=planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if((!title.equals(""))&&(!category.equals(""))&&(place.equals(""))){//title and category
				planes=(ArrayList<Plan>)planRepository.findByTitleAndCategoryIgnoreCase(title, category);
				model.addAttribute("planes",planes);
				noPlanes=planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if ((title.equals(""))&&(!category.equals(""))&&(!place.equals(""))){//category and place
				planes=(ArrayList<Plan>)planRepository.findByCategoryAndPlaceIgnoreCase(category, place);
				model.addAttribute("planes",planes);noPlanes= planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if((!title.equals(""))&&(category.equals(""))&&(!place.equals(""))){//title and place
				planes=(ArrayList<Plan>)planRepository.findByTitleAndPlaceIgnoreCase(title, place);
				model.addAttribute("planes",planes);
				noPlanes= planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if((!title.equals(""))&&(category.equals(""))&&(place.equals(""))){//title
				planes=(ArrayList<Plan>)planRepository.findByTitleIgnoreCase(title);
				model.addAttribute("planes",planes);
				noPlanes= planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if((title.equals(""))&&(!category.equals(""))&&(place.equals(""))){//category
				planes=(ArrayList<Plan>)planRepository.findByCategoryIgnoreCase(category);
				model.addAttribute("planes",planes);
				noPlanes= planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else if((title.equals(""))&&(category.equals(""))&&(!place.equals(""))){//place
				planes=(ArrayList<Plan>)planRepository.findByPlaceIgnoreCase(place);
				model.addAttribute("planes",planes);
				noPlanes= planes.isEmpty();
				model.addAttribute("noPlanes",noPlanes);
			}else{//nothing
				planes=(ArrayList<Plan>)planRepository.findAll();
				model.addAttribute("planes",planes);
				noPlanes=planes.isEmpty();
				model.addAttribute(noPlanes);
			}
			return "index";
		
	}
	@RequestMapping("/login")
	public String log(){
		return "index";
	}
	@RequestMapping("/loginerror")
	public String logError(){
		return "loginerror";
	}
}
