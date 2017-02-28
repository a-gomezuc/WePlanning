package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlanController {

	@Autowired
	private PlanRepository planRepository;
	private List<Plan> planes = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init(){
		User joselito=new User();
		ArrayList<User> asistents=new ArrayList<User>();
		for(int i=0;i<20;i++){
		planRepository.save(new Plan("Torneo LOL", "Ocio", joselito, "Madrid", "URJC Móstoles", i, new Date(2017, 2, 22),
				"Torneo del videojuego más famoso de la carrera de Ingeniería del Software",asistents));
		
		}
	}
	public PlanController() {
	}

	@RequestMapping("/")
	public String presentarInicio(Model model) {
		/*
		 * Plan plan1=new Plan("Pep","Tarragona",new Date(2017,2,22)); Plan
		 * plan2=new Plan("Patxi","Vizcaya",new Date(2017,2,22));
		 * planes.add(plan1); planes.add(plan2);
		 */
		model.addAttribute("planes", planRepository.findAll());
		return "index";

	}

	/*@RequestMapping("/inicioSesion")
	public String iniciaSesion(Model model, UsuarioCredenciales user, HttpSession sesion) {
		// model.addAttribute("bienvenida", sesion.isNew());
		usuario.setId(user.getId());
		model.addAttribute("id", usuario.getId());

		return "index-logged";

	}*/

	@RequestMapping("/plan/{id}")
	public String devuelvePlan(Model model, @PathVariable long id, Plan plan) {
		model.addAttribute("plan", planRepository.findOne(id));
		return "plan";
	}

}
