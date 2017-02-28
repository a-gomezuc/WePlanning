package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController {

	@Autowired
	private UsuarioCredenciales usuario;
	//private long idPlan;
	private List<Plan> planes = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public PrincipalController() {
		Plan plan1 = new Plan(1, "Torneo LOL", "Ocio", "Jorge", "Madrid", "URJC Móstoles", 12, new Date(2017, 2, 22),
				"Torneo del videojuego más famoso de la carrera de Ingeniería del Software");
		Plan plan2 = new Plan(2, "Salida Mountain-Bike", "Deporte", "Alex", "Toledo", "Montes de Toledo", 12,
				new Date(2017, 2, 22), "Ruta ciclista por los montes de Toledo.");
		planes.add(plan1);
		planes.add(plan2);

	}

	@RequestMapping("/")
	public String presentarInicio(Model model) {
		/*
		 * Plan plan1=new Plan("Pep","Tarragona",new Date(2017,2,22)); Plan
		 * plan2=new Plan("Patxi","Vizcaya",new Date(2017,2,22));
		 * planes.add(plan1); planes.add(plan2);
		 */
		model.addAttribute("planes", planes);
		return "index";

	}

	@RequestMapping("/inicioSesion")
	public String iniciaSesion(Model model, UsuarioCredenciales user, HttpSession sesion) {
		// model.addAttribute("bienvenida", sesion.isNew());
		usuario.setId(user.getId());
		model.addAttribute("id", usuario.getId());

		return "index-logged";

	}

	@RequestMapping("/plan/{id}")
	public String devuelvePlan(Model model, @PathVariable long id, Plan plan) {
		model.addAttribute("plan", planes.get((int) (plan.getId() - 1)));
		return "plan";
	}

}
