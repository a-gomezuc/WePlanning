package com.example;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlanController {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
		User miguelito= new User("miguel99", "Miguel", "Muñoz", "Vitoria", 25, "miguelon@gmail.com", "miContraseñaM");
		User joselito = new User("joselito_95", "José", "López", "Madrid", 25, "jose@gmail.com", "miContraseña");
		User guillermito = new User("westernsquad", "Guille", "Navas", "Toledo", 22, "guillermitonavitas@gmail.com",
				"mmm");
		userRepository.save(miguelito);
		userRepository.save(guillermito);
		joselito.getFriends().add(guillermito);
		userRepository.save(joselito);
		guillermito.getFriends().add(joselito);
		userRepository.save(guillermito);
		for (int i = 0; i <8; i++) {
			Plan planprueba = (new Plan("Torneo LOL", "Cultura", "Madrid", "URJC Móstoles", i, new Date(),
					"Torneo del videojuego más famoso de la carrera de Ingeniería del Software"));
			planprueba.setAuthor(joselito);
			planRepository.save(planprueba);
		}
		Plan planpruebaG = new Plan("Carrera", "Deportes", "Madrid", "URJC Vicálvaro", 12, new Date(2017, 2, 21),
				"Running en la universidad.");
		planpruebaG.setAuthor(guillermito);
		Comment comentario1=new Comment (new Date(), "Me ha gustado mucho");
		comentario1.setAuthor(joselito);
		Comment comentario2=new Comment (new Date(), "Menuda castaña de plan");
		comentario2.setAuthor(miguelito);
		commentRepository.save(comentario1);
		commentRepository.save(comentario2);
		planpruebaG.getComments().add(comentario1);
		planpruebaG.getComments().add(comentario2);
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
		boolean noExistComment= planActual.getComments().isEmpty();
		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("plan",planActual);
		return "plan";
	}

	@RequestMapping("/user/{id}")
	public String devuelveUser(Model model, @PathVariable String id) {
		model.addAttribute("user", userRepository.findById(id));
		return "profileHTML";

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
	@RequestMapping("/searchByTitle")
	public String searchbyTitle(Model model, String title){
		ArrayList<Plan> planes=(ArrayList<Plan>) planRepository.findByTitle(title);
		model.addAttribute("planes",planes);
		boolean noPlanes= planes.isEmpty();
		model.addAttribute("noPlanes",noPlanes);
		return "index";
		
	}
	@RequestMapping("/searchBy{category}")
		public String searchByCategory(Model model, @PathVariable String category){
		ArrayList<Plan> planes=(ArrayList<Plan>) planRepository.findByCategory(category);
		model.addAttribute("planes",planes);
		boolean noPlanes= planes.isEmpty();
		model.addAttribute("noPlanes",noPlanes);
		return "index";
	}
}
