package com.example;

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

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
		User joselito = new User("joselito_95", "José", "López", "Madrid", 25, "jose@gmail.com", "miContraseña");
		User guillermito = new User("westernsquad", "Guille", "Navas", "Toledo", 22, "guillermitonavitas@gmail.com",
				"mmm");
		userRepository.save(guillermito);
		joselito.getFriends().add(guillermito);
		userRepository.save(joselito);
		for (int i = 0; i < 100; i++) {
			Plan planprueba = (new Plan("Torneo LOL", "Ocio", "Madrid", "URJC Móstoles", i, new Date(2017, 2, 22),
					"Torneo del videojuego más famoso de la carrera de Ingeniería del Software"));
			planprueba.setAuthor(joselito);
			planRepository.save(planprueba);
		}
		Plan planpruebaG = new Plan("Carrera", "Deporte", "Madrid", "URJC Vicálvaro", 12, new Date(2017, 2, 21),
				"Running en la universidad.");
		planpruebaG.setAuthor(guillermito);
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
		model.addAttribute("plan", planRepository.findOne(id));
		return "plan";
	}

	@RequestMapping("/user/{id}")
	public String devuelveUser(Model model, @PathVariable String id) {
		model.addAttribute("user", userRepository.findById(id));
		return "profileHTML";

	}

}
