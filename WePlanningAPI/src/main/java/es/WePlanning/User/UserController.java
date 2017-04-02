package es.WePlanning.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import es.WePlanning.ApiService;

import es.WePlanning.Plan.Plan;

@RestController
public class UserController {
	
	private static final String[] provinces = { "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz", "Barcelona",
			"Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "La Coruña", "Cuenca",
			"Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "León",
			"Lérida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Las Palmas",
			"Pontevedra", "La Rioja", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona",
			"Santa Cruz de Tenerife", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora",
			"Zaragoza" };
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
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
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
	@RequestMapping(value = "/api/user/searchUsers/filter={filter}/usearch={usearch}", method = RequestMethod.GET)
	public List<User> searchUser(@PathVariable String filter, @PathVariable String usearch) {

		if ((!usearch.equals("")) && (filter.equals("ident"))) {
			ArrayList<User> resultados = new ArrayList<>();
			User userId = userRepository.findByIdIgnoreCase(usearch);
			if (userId != null)
				resultados.add(userId);
			return resultados;

		} else if ((usearch.equals("")) && (filter.equals("name"))) {
			return userRepository.findByUnameIgnoreCase(usearch);

		} else if ((usearch.equals("")) && (filter.equals("province"))) {
			return userRepository.findByProvinceIgnoreCase(usearch);
		} else
			return userRepository.findAll();

	}

	@JsonView(UserAdd.class)
	@RequestMapping(value = "/api/user/addUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User userSearch = userRepository.findByIdIgnoreCase(user.getId());
		if (userSearch == null) {
			if (Arrays.asList(provinces).contains(user.getProvince())) {
				user.setIdentifier(0);
				user.setRoles(Arrays.asList("ROLE_USER"));
				user.setProfilePhotoTitle("profiledefault.jpg");
				if (user.getPasswordHash() == null) {
					user.setPasswordHash("pass");
				}
				userRepository.save(user);
				User userChanged = userRepository.findById(user.getId());
				return new ResponseEntity<>(userChanged, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
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
				if (Arrays.asList(provinces).contains(userModify.getProvince())) {
					user.setAge(userModify.getAge());
					user.setUname(userModify.getUname());
					user.setProvince(userModify.getProvince());
					user.setUemail(userModify.getUemail());
					user.setDescription(userModify.getDescription());
					userRepository.save(user);

					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}
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
			if (changed) {

				user.setProfilePhotoTitle(imageService.getImg().getFileName());
				userRepository.save(user);

				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	@JsonView(Plan.BasicAtt.class)
	@RequestMapping(value="/api/user/myPlans", method=RequestMethod.GET)
	public ResponseEntity<List>viewMyPlans(){
		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		
		return new ResponseEntity<>(user.getPlans(),HttpStatus.OK);
	}
	@JsonView(User.BasicAtt.class)
	@RequestMapping(value = "/api/user/addFriend/{id}", method = RequestMethod.PUT)
	public ResponseEntity<List> addFriend(@PathVariable String id) {
		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		User friend = userRepository.findByIdIgnoreCase(id);
		if ((!user.getId().equals(id)) && (friend != null) && (!user.isSponsor())) {
			user.getFriends().add(friend);
			userRepository.save(user);
			return new ResponseEntity<>(user.getFriends(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(User.BasicAtt.class)
	@RequestMapping(value = "/api/user/removeFriend/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List> deleteFriend(@PathVariable String id) {
		User user = userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
		User friend = userRepository.findById(id);
		if ((user.getFriends().contains(friend)) && (friend.getFriends().contains(user))) {
			user.getFriends().remove(friend);
			friend.getFriends().remove(user);
			userRepository.save(user);
			userRepository.save(friend);
			return new ResponseEntity<>(user.getFriends(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @JsonView(UserView.class)
	 * 
	 * @RequestMapping(value = "/api/user/{id}/assistPlan/{idPlan}", method =
	 * RequestMethod.PUT) public ResponseEntity<User> asistPlan(@PathVariable
	 * String id, @PathVariable long idPlan) { User user =
	 * userRepository.findByIdIgnoreCase(userComponent.getLoggedUser().getId());
	 * if (user.getId().equals(id)) { Plan plan =
	 * planRepository.findById(idPlan); if ((user != null) && (plan != null)) {
	 * plan.getAsistents().add(user); planRepository.save(plan);
	 * userRepository.save(user); return new ResponseEntity<>(user,
	 * HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } } else { return new
	 * ResponseEntity<>(HttpStatus.UNAUTHORIZED); } }
	 */
	@JsonView(User.BasicAtt.class)
	@RequestMapping(value = "/api/user/{id}/viewFriends", method = RequestMethod.GET)
	public ResponseEntity<List<User>> viewFriends(@PathVariable String id) {
		User user = userRepository.findByIdIgnoreCase(id);
		if(user!=null)
		return new ResponseEntity<>(user.getFriends(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

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
