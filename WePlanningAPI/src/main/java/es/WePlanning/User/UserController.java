package es.WePlanning.User;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
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

	public interface UserView extends User.BasicAtt, User.PlansAtt, Plan.BasicAtt, User.RolesAtt {
	}

	public interface UserAdd extends User.BasicAtt, User.passwordAtt {
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> users() {
		return userRepository.findAll();
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> usersID(@PathVariable String id) {
		User usu = userRepository.findById(id);
		if (usu != null) {
			return new ResponseEntity<>(usu, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(UserAdd.class)
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
		user.setRoles(Arrays.asList("ROLE_USER"));
		user.setProfilePhotoTitle("profiledefault.jpg");
		userRepository.save(user);
		return user;
	}

	@JsonView(User.BasicAtt.class)
	@RequestMapping(value = "/modifyProfile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> modifyProfile(@PathVariable String id, @RequestBody User userModify) {
			//Falta comprobar si el usuario es el logueado
			if (userRepository.findById(id) != null) {
				User user = userRepository.findById(id);

				user.setAge(userModify.getAge());
				user.setUname(userModify.getUname());
				user.setProvince(userModify.getProvince());
				user.setDescription(userModify.getDescription());
				user.setProfilePhotoTitle(userModify.getProfilePhotoTitle());
				userRepository.save(user);

				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/{id}/assistPlan/{idPlan}", method = RequestMethod.PUT)
	public ResponseEntity<Plan> asistPlan(@PathVariable String id, @PathVariable long idPlan) {
			//Falta comprobar si el usuario es el logueado
			User user = userRepository.findById(id);
			Plan plan = planRepository.findById(idPlan);
			if ((user != null) && (plan != null)) {
				plan.getAsistents().add(user);
				planRepository.save(plan);
				userRepository.save(user);
				return new ResponseEntity<>(plan, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}

}
