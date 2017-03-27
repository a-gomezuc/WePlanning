package es.WePlanning.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import es.WePlanning.ApiService;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;
import es.WePlanning.Security.LoginController;

@RestController
public class UserController {

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private ApiService imageService;

	public interface UserView extends User.BasicAtt, User.PlansAtt, Plan.BasicAtt, User.RolesAtt {
	}

	public interface UserAdd extends User.BasicAtt, User.passwordAtt {
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public List<User> users() {
		return userRepository.findAll();
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> usersID(@PathVariable String id) {
		User usu = userRepository.findByIdIgnoreCase(id);
		if (usu != null) {
			return new ResponseEntity<>(usu, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/api/user/searchByName/{name}", method = RequestMethod.GET)
	public List<User> usersName(@PathVariable String name) {
		List<User> users = userRepository.findByUnameIgnoreCase(name);
		if (!users.isEmpty()) {
			return users;
		} else {
			return new ArrayList<>();
		}
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/api/user/searchByProvince/{province}", method = RequestMethod.GET)
	public List<User> userProvince(@PathVariable String province) {
		List<User> users = userRepository.findByProvinceIgnoreCase(province);
		if (!users.isEmpty()) {
			return users;
		} else {
			return new ArrayList<>();
		}

	}

	@JsonView(UserAdd.class)
	@RequestMapping(value = "/api/user/addUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User userSearch = userRepository.findByIdIgnoreCase(user.getId());
		if (userSearch == null) {
			user.setRoles(Arrays.asList("ROLE_USER"));
			user.setProfilePhotoTitle("profiledefault.jpg");
			if(user.getPasswordHash()==null){
				user.setPasswordHash("pass");
			}
			userRepository.save(user);
			User userChanged= userRepository.findById(user.getId());
			return new ResponseEntity<>(userChanged, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@JsonView(User.BasicAtt.class)
	@RequestMapping(value = "/api/user/modifyProfile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> modifyProfile(@PathVariable String id, @RequestBody User userModify) {

		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		if (user.getId().equals(id)) {

			if (userRepository.findByIdIgnoreCase(id) != null) {
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
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(UserView.class)
	@RequestMapping(value = "/api/user/{id}/modifyProfilePhoto", method = RequestMethod.PUT)
	public ResponseEntity<User> modifyProfilePhoto(@PathVariable String id, @RequestParam("file") MultipartFile file) {

		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		if (user.getId().equals(id)) {
			
			boolean changed = imageService.getImg().changePhoto(id, file);
			if(changed){
				
				user.setProfilePhotoTitle(imageService.getImg().getFileName());
				userRepository.save(user);
	
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	/*@JsonView(UserView.class)
	@RequestMapping(value = "/api/user/{id}/assistPlan/{idPlan}", method = RequestMethod.PUT)
	public ResponseEntity<User> asistPlan(@PathVariable String id, @PathVariable long idPlan) {
		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		if (user.getId().equals(id)) {
			Plan plan = planRepository.findById(idPlan);
			if ((user != null) && (plan != null)) {
				plan.getAsistents().add(user);
				planRepository.save(plan);
				userRepository.save(user);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
*/
	@JsonView(UserView.class)
	@RequestMapping(value = "/api/admin/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		User user = userRepository.findByIdIgnoreCase(id);
		if (user != null) {
			userRepository.delete(user);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
