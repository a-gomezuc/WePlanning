package com.example;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		User miguelito= new User("miguel99", "Miguel", "Muñoz", "Vitoria", 25, "miguelon@gmail.com", "miContraseñaM", "ROLE_USER");
		User joselito = new User("joselito_95", "José", "López", "Madrid", 25, "jose@gmail.com", "miContraseña");
		User guillermito = new User("westernsquad", "Guille", "Navas", "Toledo", 22, "guillermitonavitas@gmail.com",
				"mmm", "ROLE_USER","ROLE_ADMIN");
		userRepository.save(miguelito);
		userRepository.save(guillermito);
		joselito.getFriends().add(guillermito);
		userRepository.save(joselito);
		guillermito.getFriends().add(joselito);
		userRepository.save(guillermito);
		for (int i = 0; i <1; i++) {
			Plan planprueba = (new Plan("Torneo LOL", "Cultura", "Madrid", "URJC Móstoles", i, "1/03/2017",
					"Torneo del videojuego más famoso de la carrera de Ingeniería del Software"));
			planprueba.setAuthor(joselito);
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
	public String presentarInicio(Model model, Pageable page) {
		Page<Plan> planes = planRepository.findAll(page);
		model.addAttribute("planes", planes);
		model.addAttribute("size", planes.getSize() + 10);
		model.addAttribute("showButton", !planes.isLast());
		return "index";

	}
	@RequestMapping("/logged")
	public String presentarInicioLogueado(Model model, Pageable page) {
//		Page<Plan> planes = planRepository.findAll(page);
//		model.addAttribute("planes", planes);
//		model.addAttribute("size", planes.getSize() + 10);
//		model.addAttribute("showButton", !planes.isLast());
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "index-logged";

	}

	/*
	 * @RequestMapping("/inicioSesion") public String iniciaSesion(Model model,
	 * UsuarioCredenciales user, HttpSession sesion) { //
	 * model.addAttribute("bienvenida", sesion.isNew());
	 * usuario.setId(user.getId()); model.addAttribute("id", usuario.getId());
	 * 
	 * return "index-logged";
	 * 
	 * }
	 */

	@RequestMapping("/plan/{id}")
	public String devuelvePlan(Model model, @PathVariable long id) {
		Plan planActual=planRepository.findOne(id);
		int asistentes=planActual.getAsistents().size();
		boolean noExistComment= planActual.getComments().isEmpty();
		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("numAsistents",asistentes);
		model.addAttribute("plan",planActual);
		return "plan";
	}

	@RequestMapping("/user/{id}")
	public String devuelveUser(Model model, @PathVariable String id) {
		User usuario=userRepository.findById(id);
		model.addAttribute("user", usuario);	
		return "ProfileHTML";

	}
	@RequestMapping("logged/user/{id}")
	public String devuelveUserLogged(Model model, @PathVariable String id) {
		User usuario=userRepository.findById(id);
		model.addAttribute("user", usuario);
		model.addAttribute("AllUsers",userRepository.findAll());
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "ProfileHTML-logged";

	}
	@RequestMapping("/aboutus")
	public String aboutUs() {
		return "aboutus";

	}
	@RequestMapping("/contact")
	public String contact() {
		return "contact";

	}
	@RequestMapping("/register")
	public String register() {
		return "register";

	}
	@RequestMapping(value="/registerUser",  method = RequestMethod.POST)
	public String registerUser (boolean sponsorCheckbox, String name, int age,
			String province, String username, String email, String pass){
		
		if(!sponsorCheckbox){//user
			
			User user =new User(name, name ,username, province, age, email, pass);
			userRepository.save(user);
			
			return "index";
		}else{//sponsor
			return "index";
		}
		
	}
	@RequestMapping("/newPlan")
	public String newPlan(Model model) {
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		return "NewPlan";

	}
	@RequestMapping("/createPlan")
	public String createPlan(Model model, Plan plan){
		plan.setAuthor(userComponent.getLoggedUser());
		planRepository.save(plan);
		model.addAttribute("planes",plan);
		model.addAttribute("id",plan.getAuthor().getId());
		model.addAttribute("idPlan",plan.getId());
		model.addAttribute("title",plan.getTitle());
		
		return"SuccesfulPlan";
	}
	@RequestMapping("/searchUsers")
	public String searchByName(Model model, String uname, String surname){
		ArrayList<User> users;
		boolean noUsers;
		
	
		if((!uname.equals(""))){//name
			users=(ArrayList<User>)userRepository.findByUnameIgnoreCase(uname);
			model.addAttribute("AllUsers",users);
			noUsers=users.isEmpty();
			model.addAttribute("noUser",noUsers);
			return "index";
		}
		return "ProfileHTML-Logged";
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
