package es.WePlanning;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner{
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

	@PostConstruct
	public void init() {
		User admin = new User(false, "admin", "Administrador", "del Sistema", "Planning City", 0, "admin@gmail.com",
				"adminpass", "ROLE_USER","ROLE_ADMIN");
		User miguelito = new User(false, "miguel99", "Miguel", "Muñoz", "Vitoria", 25, "miguelon@gmail.com",
				"miContraseñaM", "ROLE_USER");
		User joselito = new User(false, "joselito_95", "José", "López", "Madrid", 25, "jose@gmail.com", "mmmm",
				"ROLE_USER");
		User guillermito = new User(false, "westernsquad", "Guille", "Navas", "Toledo", 22,
				"guillermitonavitas@gmail.com", "mmm", "ROLE_USER");
		User desnet = new User(true, "desnet", "DesNet", "Company", "Madrid", 1, "desnet@gmail.com", "perrete",
				"ROLE_USER");
		admin.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(admin);
		miguelito.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(miguelito);
		userRepository.save(guillermito);
		desnet.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(desnet);
		joselito.getFriends().add(guillermito);
		joselito.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(joselito);
		guillermito.getFriends().add(joselito);
		guillermito.getFriends().add(miguelito);
		guillermito.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(guillermito);

		for (int i = 0; i < 1; i++) {
			Plan planprueba = (new Plan("Plan de prueba -" + (i + 1), "Cultura", "Madrid", "URJC Móstoles", i,
					"1/03/2017", "Torneo del videojuego más famoso de la carrera de Ingeniería del Software"));
			planprueba.setAuthor(joselito);
			planprueba.setImagePlanTitle("planprueba" + i + ".jpg");
			planRepository.save(planprueba);

		}

		Plan planpruebaG = new Plan("Carrera", "Deportes", "Madrid", "URJC Vicálvaro", 12, "12/03/2017",
				"Running en la universidad.");
		planpruebaG.setAuthor(guillermito);
		planpruebaG.setImagePlanTitle("carrera.jpg");

		Plan planpruebaJ = new Plan("Curso universidad", "Cultura", "Albacete", "Universidad de Albacete", 250,
				"15/03/2017", "Curso de programación web");
		planpruebaJ.setAuthor(miguelito);
		planpruebaJ.setImagePlanTitle("curso.jpg");
		planRepository.save(planpruebaJ);
		Comment comentario1 = new Comment("10/01/2017", "Me ha gustado mucho");
		comentario1.setAuthor(joselito);
		Comment comentario2 = new Comment("10/02/2016", "Menuda castaña de plan");
		comentario2.setAuthor(miguelito);
		commentRepository.save(comentario1);
		commentRepository.save(comentario2);
		planpruebaG.getComments().add(comentario1);
		planpruebaG.getComments().add(comentario2);
		planpruebaG.getAsistents().add(guillermito);
		planpruebaG.getAsistents().add(miguelito);
		planRepository.save(planpruebaG);

	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
