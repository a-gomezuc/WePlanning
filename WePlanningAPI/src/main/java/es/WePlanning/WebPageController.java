package es.WePlanning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.Contact;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
import es.WePlanning.Plan.PlanRepository;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;
import es.WePlanning.User.UserService;

@Controller
public class WebPageController {

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
	@Autowired
	private UserService userService;

	public WebPageController() {
	}

	@RequestMapping("/")
	public String start(Model model) {
		Page<Plan> planes = planRepository.findAll(new PageRequest(0, 10));
		model.addAttribute("planes", planes);
		model.addAttribute("showButton", !planes.isLast());
		return "index";
	}

	@RequestMapping("/morePlans")
	public String moreStart(Model model, @RequestParam int page) {
		Page<Plan> planes = planRepository.findAll(new PageRequest(page, 10));
		model.addAttribute("planes", planes);
		return "plansList";

	}

	@RequestMapping("/morePlansLogged")
	public String moreStartLogged(Model model, @RequestParam int page) {
		Page<Plan> planes = planRepository.findAll(new PageRequest(page, 10));
		model.addAttribute("planes", planes);
		return "plansListLogged";

	}

	@RequestMapping("/morePlansFriendsLogged")
	public String moreStartFriendsLogged(Model model, @RequestParam int page) {
		User u = userRepository.findById(userComponent.getLoggedUser().getId());
		Page<Plan> userplansPage = planRepository.findFriendsPlans(u.getFriends(), new PageRequest(page, 10));
		model.addAttribute("planes", userplansPage);
		return "plansListLogged";

	}

	@RequestMapping("/morePlansUser")
	public String moreStartUser(Model model, @RequestParam int page, @RequestParam String id) {
		Page<Plan> planes = planRepository.findByAuthorId(id, new PageRequest(page, 10));
		model.addAttribute("plans", planes);
		return "plansListUser";

	}

	@RequestMapping("/morePlansUserLogged")
	public String moreStartUserLogged(Model model, @RequestParam int page, @RequestParam String id) {
		Page<Plan> planes = planRepository.findByAuthorId(id, new PageRequest(page, 10));
		model.addAttribute("plans", planes);
		return "plansListUserLogged";

	}

	@RequestMapping("/moreFriends")
	public String moreFriends(Model model, @RequestParam int page, @RequestParam String id) {
		User user = userRepository.findById(id);
		Page<User> friends = userRepository.findUsers(user.getFriends(), new PageRequest(page, 10));
		model.addAttribute("friendsUser", friends);
		return "friendsList";

	}

	@RequestMapping("/moreFriendsLogged")
	public String moreFriendsLogged(Model model, @RequestParam int page, @RequestParam String id) {
		User user = userRepository.findById(id);
		Page<User> friends = userRepository.findUsers(user.getFriends(), new PageRequest(page, 1));
		model.addAttribute("friendsUser", friends);
		return "friendsLoggedList";

	}

	@RequestMapping("/moreAssistents")
	public String moreAssistents(Model model, @RequestParam int page, @RequestParam long id) {
		Plan plan = planRepository.findOne(id);
		if (!plan.getAsistents().isEmpty()) {
			Page<User> assistents = userRepository.findUsers(plan.getAsistents(), new PageRequest(page, 1));
			model.addAttribute("assistentsPlan", assistents);
		} else {
			model.addAttribute("assistentsPlan", false);
		}
		return "assistentList";

	}

	@RequestMapping("/moreAssistentsLogged")
	public String moreAssistentsLogged(Model model, @RequestParam int page, @RequestParam long id) {
		Plan plan = planRepository.findOne(id);
		if (!plan.getAsistents().isEmpty()) {
			Page<User> assistents = userRepository.findUsers(plan.getAsistents(), new PageRequest(page, 1));
			model.addAttribute("assistentsPlan", assistents);
		} else {
			model.addAttribute("assistentsPlan", false);
		}
		return "assistentListLogged";

	}

	@RequestMapping("/moreUsersSearch")
	public String moreUsersSearch(Model model, @RequestParam int page) {
		Page<User> users = userRepository.findAll(new PageRequest(page, 1));
		model.addAttribute("AllUsers", users);
		return "userSearchList";
	}

	@RequestMapping("/moreComments")
	public String moreComments(Model model, @RequestParam int page, @RequestParam long id) {
		Plan plan = planRepository.findOne(id);
		if (!plan.getComments().isEmpty()) {
			Page<Comment> comments = commentRepository.findComments(plan.getComments(), new PageRequest(page, 1));
			model.addAttribute("commentsPlan", comments);
		} else {
			model.addAttribute("commentsPlan", false);
		}
		return "commentList";

	}

	@RequestMapping("/moreCommentsLogged")
	public String moreCommentsLogged(Model model, @RequestParam int page, @RequestParam long id) {
		Plan plan = planRepository.findOne(id);
		if (!plan.getComments().isEmpty()) {
			Page<Comment> comments = commentRepository.findComments(plan.getComments(), new PageRequest(page, 1));
			model.addAttribute("commentsPlan", comments);
		} else {
			model.addAttribute("commentsPlan", false);
		}
		return "commentListLogged";

	}

	@RequestMapping("/logged")
	public String startLogged(Model model, Pageable page) {
		// Page<Plan> planes = planRepository.findAll(page);
		// model.addAttribute("planes", planes);
		// model.addAttribute("size", planes.getSize() + 10);
		// model.addAttribute("showButton", !planes.isLast());
		/*
		 * User newUser =
		 * userRepository.findById(userComponent.getLoggedUser().getId());
		 * List<Plan> userplans = new ArrayList<>(); List<User> friends =
		 * newUser.getFriends(); for (User u : friends) { for (Plan p :
		 * u.getPlans()) { userplans.add(p); } }
		 */
		User u = userRepository.findById(userComponent.getLoggedUser().getId());
		if (!u.getFriends().isEmpty()) {
			Page<Plan> userplansPage = planRepository.findFriendsPlans(u.getFriends(), new PageRequest(0, 10));
			model.addAttribute("userPlans", userplansPage);
		} else {
			model.addAttribute("userPlans", false);
			model.addAttribute("noPlanesFriends", true);
		}
		Page<Plan> plans = planRepository.findAll(new PageRequest(0, 10));
		model.addAttribute("planes", plans);
		model.addAttribute("size", plans.getSize() + 10);
		model.addAttribute("showButton", !plans.isLast());
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		return "index-logged";

	}

	@RequestMapping("/plan/{id}")
	public String retPlan(Model model, @PathVariable long id) {
		Plan planActual = planRepository.findOne(id);
		int asistentes = planActual.getAsistents().size();
		boolean noExistComment = planActual.getComments().isEmpty();

		if (!planActual.getAsistents().isEmpty()) {
			Page<User> assistents = userRepository.findUsers(planActual.getAsistents(), new PageRequest(0, 1));
			model.addAttribute("assistentsPlan", assistents);
		} else {
			model.addAttribute("assistentsPlan", false);
		}
		if (!planActual.getComments().isEmpty()) {
			Page<Comment> comments = commentRepository.findComments(planActual.getComments(), new PageRequest(0, 1));
			model.addAttribute("commentsPlan", comments);
		} else {
			model.addAttribute("commentsPlan", false);
			model.addAttribute("noExistComment", true);
		}
		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("numAsistents", asistentes);
		model.addAttribute("plan", planActual);
		return "plan";
	}

	@RequestMapping("/logged/plan/{id}")
	public String retPlanLogged(Model model, @PathVariable long id) {
		Plan planActual = planRepository.findOne(id);
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		boolean assist = false;
		if (planActual.getAsistents().contains(user)) {
			assist = true;
		}
		boolean noAssist = !assist;
		int asistentes = planActual.getAsistents().size();
		boolean noExistComment = planActual.getComments().isEmpty();

		if (!planActual.getAsistents().isEmpty()) {
			Page<User> assistents = userRepository.findUsers(planActual.getAsistents(), new PageRequest(0, 1));
			model.addAttribute("assistentsPlan", assistents);
		} else {
			model.addAttribute("assistentsPlan", false);
		}
		if (!planActual.getComments().isEmpty()) {
			Page<Comment> comments = commentRepository.findComments(planActual.getComments(), new PageRequest(0, 1));
			model.addAttribute("commentsPlan", comments);
		} else {
			model.addAttribute("commentsPlan", false);
			model.addAttribute("noExistComment", true);
		}

		model.addAttribute("noExistComment", noExistComment);
		model.addAttribute("numAsistents", asistentes);
		model.addAttribute("assist", assist);
		model.addAttribute("plan", planActual);
		model.addAttribute("noAssist", noAssist);
		return "plan-logged";
	}

	@RequestMapping(value = "/logged/plan/{id}/addComment", method = RequestMethod.POST)
	public String addComments(Model model, @PathVariable long id, String cont) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		Plan plan = planRepository.findOne(id);

		/* Date */
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Calendar actualDate = Calendar.getInstance();
		/* /Date */
		Comment comment = new Comment(format.format(date), cont);
		comment.setAuthor(userComponent.getLoggedUser());
		commentRepository.save(comment);
		plan.getComments().add(comment);
		planRepository.save(plan);
		return "SuccessfulComment";
	}

	@RequestMapping(value = "/logged/plan/{id}/assist", method = RequestMethod.POST)
	public String assistPlan(Model model, @PathVariable long id) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		Plan plan = planRepository.findOne(id);
		User user = userComponent.getLoggedUser();
		plan.getAsistents().add(user);
		userRepository.save(user);
		planRepository.save(plan);
		return "SuccessfulAssist";
	}

	@RequestMapping("/user/{id}")
	public String retUser(Model model, @PathVariable String id) {
		User user = userRepository.findById(id);
		model.addAttribute("user", user);
		if (!user.getFriends().isEmpty()) {
			model.addAttribute("friendsUser", userRepository.findUsers(user.getFriends(), new PageRequest(0, 10)));
		} else {
			model.addAttribute("friendsUser", false);
		}
		model.addAttribute("plansUser", planRepository.findByAuthorId(id, new PageRequest(0, 10)));
		if (!user.isSponsor()) {
			return "ProfileHTML";
		} else {
			return "SponsorHTML";
		}
	}

	@RequestMapping("logged/user/{id}")
	public String retUserLogged(Model model, @PathVariable String id) {
		User userlog = userRepository.findById(userComponent.getLoggedUser().getId());
		User user = userRepository.findById(id);
		boolean noSponsor = true;
		if (userlog.isSponsor()) {
			noSponsor = false;
		}
		if (!user.getFriends().isEmpty()) {
			model.addAttribute("friendsUser", userRepository.findUsers(user.getFriends(), new PageRequest(0, 1)));
		} else {
			model.addAttribute("friendsUser", false);
		}
		model.addAttribute("noSponsor", noSponsor);
		model.addAttribute("user", user);
		model.addAttribute("plansUser", planRepository.findByAuthorId(id, new PageRequest(0, 10)));
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		model.addAttribute("AllUsers", userRepository.findAll(new PageRequest(0, 1)));
		boolean noFriends = !(userlog.getFriends().contains(user));
		boolean yesFriends = !noFriends;

		if (id.equals(userComponent.getLoggedUser().getId()) && (!user.isSponsor())) {
			return "ProfileHTML-logged";
		} else if (!id.equals(userComponent.getLoggedUser().getId()) && (!user.isSponsor())) {

			model.addAttribute("noFriends", noFriends);
			model.addAttribute("yesFriends", yesFriends);
			return "ProfileHTML-viewlogged";
		} else if (id.equals(userComponent.getLoggedUser().getId()) && (user.isSponsor())) {
			return "SponsorHTML-logged";
		} else {
			model.addAttribute("noFriends", noFriends);
			model.addAttribute("yesFriends", yesFriends);
			return "SponsorHTML-viewlogged";
		}

	}

	@RequestMapping(value = "/logged/user/{id}/addFriend", method = RequestMethod.POST)
	public String addFriend(Model model, @PathVariable String id) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		User friend = userRepository.findById(id);
		user.getFriends().add(friend);
		userRepository.save(user);

		if (friend.isSponsor()) {
			friend.getFriends().add(user);
			userRepository.save(friend);
			return "successfulSponsor";
		} else {

			return "successfulFriend";
		}
	}

	@RequestMapping(value = "/logged/user/{id}/removeFriend", method = RequestMethod.POST)
	public String removeFriend(Model model, @PathVariable String id) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		User friend = userRepository.findById(id);
		user.getFriends().remove(friend);
		if (friend.getFriends().contains(user)) {
			friend.getFriends().remove(user);
		}
		userRepository.save(user);
		userRepository.save(friend);
		if (friend.isSponsor()) {
			return "successfulRemoveSponsor";
		} else {

			return "successfulRemoveFriend";
		}
	}

	@RequestMapping("/aboutus")
	public String aboutUs() {
		return "aboutus";

	}

	@RequestMapping("/logged/aboutus")
	public String loggedAboutUs(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		return "aboutus-logged";

	}

	@RequestMapping("/contact")
	public String contact() {
		return "contact";

	}

	@RequestMapping("/registerContact")
	public String registercontact(String C_FirstName, String C_LastName, String C_Company, String C_BusPhone,
			String C_EmailAddress, String description) {
		Contact contact = new Contact(C_FirstName, C_FirstName, C_LastName, C_Company, C_BusPhone, C_EmailAddress,
				description);
		contactRepository.save(contact);

		return "SuccesfulContact";
	}

	@RequestMapping("/logged/registerContact")
	public String registercontactLogged(String C_FirstName, String C_LastName, String C_Company, String C_BusPhone,
			String C_EmailAddress, String description) {
		Contact contact = new Contact(C_FirstName, C_FirstName, C_LastName, C_Company, C_BusPhone, C_EmailAddress,
				description);
		contactRepository.save(contact);

		return "SuccesfulContactLogged";
	}

	@RequestMapping("/logged/contact")
	public String loggedContact(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		return "contact-logged";

	}

	@RequestMapping("/register")
	public String register() {
		return "register";

	}

	@RequestMapping("/logged/register")
	public String loggedRegister(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		return "register";

	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(Model model, boolean sponsorCheckbox, String name, String surname, int age,
			String province, String username, String email, String pass) {
		if (userRepository.findById(username) == null) {
			User user = new User(sponsorCheckbox, username, name, surname, province, age, email, pass, "ROLE_USER");
			userRepository.save(user);
			return "SuccesfulRegister";
		} else {
			model.addAttribute("userName", username);
			return "usernameNotAvailable";
		}
	}

	@RequestMapping("/newPlan")
	public String newPlan(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		return "NewPlan";

	}

	@RequestMapping("/createPlan")
	public String createPlan(Model model, Plan plan, @RequestParam("file") MultipartFile file) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		String FILES_FOLDER = "src\\main\\resources\\static\\planImages";
		Random rnd = new Random();
		int cod = rnd.nextInt(1000000);
		String fileName = cod + user.getId() + user.getPlans().size() + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

			} catch (Exception e) {

				return "newPlan";
			}
		}

		plan.setAuthor(userComponent.getLoggedUser());
		plan.setImagePlanTitle(fileName);
		planRepository.save(plan);
		model.addAttribute("planes", plan);
		model.addAttribute("id", plan.getAuthor().getId());
		model.addAttribute("idPlan", plan.getId());
		model.addAttribute("title", plan.getTitle());

		return "SuccessfulPlan";
	}

	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		/*String filePath = "src/main/resources/static/planImages/" + fileName + ".jpg";

		File file = new File(filePath);

		if (file.exists()) {
			res.setContentType("image/jpg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}*/
		userService.getImg().handleFileDownload(fileName, res);
	}

	@RequestMapping("/logged/user/{id}/searchUsers")
	public String searchAnUser(Model model, @PathVariable String id, String usearch, String filter) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		model.addAttribute("user", userRepository.findById(id));
		ArrayList<User> users;
		User u;
		boolean noUsers;
		if ((!usearch.equals("")) && (filter.equals("name"))) {
			users = (ArrayList<User>) userRepository.findByUnameIgnoreCase(usearch);
			model.addAttribute("AllUsers", users);
			noUsers = users.isEmpty();
			model.addAttribute("noUsers", noUsers);

		} else if ((!usearch.equals("")) && (filter.equals("ident"))) {
			u = userRepository.findByIdIgnoreCase(usearch);
			model.addAttribute("AllUsers", u);
			noUsers = (u.equals(""));
			model.addAttribute("noUsers", noUsers);

		} else if ((!usearch.equals("")) && (filter.equals("province"))) {
			users = (ArrayList<User>) userRepository.findByProvinceIgnoreCase(usearch);
			model.addAttribute("AllUsers", users);
			noUsers = users.isEmpty();
			model.addAttribute("noUsers", noUsers);

		} else {
			users = (ArrayList<User>) userRepository.findAll();
			model.addAttribute("AllUsers", users);
			noUsers = users.isEmpty();
			model.addAttribute("noUsers", noUsers);

		}
		return "ProfileHTML-logged";
	}

	@RequestMapping("/searchPlans")
	public String search(Model model, String title, String category, String place) {
		ArrayList<Plan> planes;
		boolean noPlanes;

		if ((!title.equals("")) && (!category.equals("")) && (!place.equals(""))) {// title,
																					// category
																					// and
																					// place
			planes = (ArrayList<Plan>) planRepository.findByTitleAndCategoryAndPlaceIgnoreCase(title, category, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (!category.equals("")) && (place.equals(""))) {// title
																							// and
																							// category
			planes = (ArrayList<Plan>) planRepository.findByTitleAndCategoryIgnoreCase(title, category);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (!category.equals("")) && (!place.equals(""))) {// category
																							// and
																							// place
			planes = (ArrayList<Plan>) planRepository.findByCategoryAndPlaceIgnoreCase(category, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (category.equals("")) && (!place.equals(""))) {// title
																							// and
																							// place
			planes = (ArrayList<Plan>) planRepository.findByTitleAndPlaceIgnoreCase(title, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (category.equals("")) && (place.equals(""))) {// title
			planes = (ArrayList<Plan>) planRepository.findByTitleIgnoreCase(title);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (!category.equals("")) && (place.equals(""))) {// category
			planes = (ArrayList<Plan>) planRepository.findByCategoryIgnoreCase(category);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (category.equals("")) && (!place.equals(""))) {// place
			planes = (ArrayList<Plan>) planRepository.findByPlaceIgnoreCase(place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else {// nothing
			planes = (ArrayList<Plan>) planRepository.findAll();
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute(noPlanes);
		}

		return "index";

	}

	@RequestMapping("/logged/searchPlans")
	public String searchLogged(Model model, String title, String category, String place) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		ArrayList<Plan> planes;
		boolean noPlanes;

		if ((!title.equals("")) && (!category.equals("")) && (!place.equals(""))) {// title,
																					// category
																					// and
																					// place
			planes = (ArrayList<Plan>) planRepository.findByTitleAndCategoryAndPlaceIgnoreCase(title, category, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (!category.equals("")) && (place.equals(""))) {// title
																							// and
																							// category
			planes = (ArrayList<Plan>) planRepository.findByTitleAndCategoryIgnoreCase(title, category);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (!category.equals("")) && (!place.equals(""))) {// category
																							// and
																							// place
			planes = (ArrayList<Plan>) planRepository.findByCategoryAndPlaceIgnoreCase(category, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (category.equals("")) && (!place.equals(""))) {// title
																							// and
																							// place
			planes = (ArrayList<Plan>) planRepository.findByTitleAndPlaceIgnoreCase(title, place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((!title.equals("")) && (category.equals("")) && (place.equals(""))) {// title
			planes = (ArrayList<Plan>) planRepository.findByTitleIgnoreCase(title);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (!category.equals("")) && (place.equals(""))) {// category
			planes = (ArrayList<Plan>) planRepository.findByCategoryIgnoreCase(category);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else if ((title.equals("")) && (category.equals("")) && (!place.equals(""))) {// place
			planes = (ArrayList<Plan>) planRepository.findByPlaceIgnoreCase(place);
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute("noPlanes", noPlanes);
		} else {// nothing
			planes = (ArrayList<Plan>) planRepository.findAll();
			model.addAttribute("planes", planes);
			noPlanes = planes.isEmpty();
			model.addAttribute(noPlanes);
		}

		return "index-logged";

	}

	@RequestMapping("/login")
	public String log() {
		return "index";
	}

	@RequestMapping("/loginerror")
	public String logError() {
		return "loginerror";
	}

	@RequestMapping("/logged/change/{id}")
	public String change(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		model.addAttribute("user", userRepository.findById(userComponent.getLoggedUser().getId()));
		return "changeInfo";

	}

	@RequestMapping(value = "/logged/change/{id}", method = RequestMethod.POST)
	public String changeinfo(Model model, @PathVariable String id, String uname, String province, int age,
			String uemail, String description) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User usuario = userRepository.findById(id);
		usuario.setUname(uname);
		usuario.setProvince(province);
		usuario.setAge(age);
		usuario.setDescription(description);
		userRepository.save(usuario);

		return "SuccesfulChangeInfo";

	}

	@RequestMapping("/logged/{id}/changePhoto")
	public String photoModifier(Model model, @PathVariable String id) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());

		return "changePhoto";

	}

	@RequestMapping(value = "/logged/{id}/profilePhoto", method = RequestMethod.POST)
	public String changePhoto(Model model, @PathVariable String id, @RequestParam("file") MultipartFile file){
		model.addAttribute("idConectado",userComponent.getLoggedUser().getId());
		User user=userRepository.findById(userComponent.getLoggedUser().getId());
		
		/*String FILES_FOLDER = "src\\main\\resources\\static\\planImages";

		String fileName = "profile"+  user.getId()  +  ".jpg";
		
		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

			} catch (Exception e) {

				return "profileHTML-logged";
			}
		}*/
		
		boolean changed = userService.getImg().changePhoto(id, file);
		if(changed){
			user.setProfilePhotoTitle(userService.getImg().getFileName());
			userRepository.save(user);
			model.addAttribute("user",user);
			return "SuccessfulChangePhoto";
		}else{
			return "profileHTML-logged";
		}
	}

	@RequestMapping("/logged/changeS/{id}")
	public String changeSponsor(Model model) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		model.addAttribute("user", userRepository.findById(userComponent.getLoggedUser().getId()));
		return "changeInfoSponsor";

	}

	@RequestMapping(value = "/logged/changeS/{id}", method = RequestMethod.POST)
	public String changeinfoSponsor(Model model, @PathVariable String id, String uname, String province, String uemail,
			String description) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User usuario = userRepository.findById(id);
		usuario.setUname(uname);
		usuario.setProvince(province);
		usuario.setDescription(description);
		userRepository.save(usuario);
		return "SuccesfulChangeInfo";
	}

	@RequestMapping("/logged/modifyPlan/{id}")
	public String modifyPlan(Model model, @PathVariable long id) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		Plan plan = planRepository.findOne(id);
		if ((userComponent.getLoggedUser().getId()).equals(plan.getAuthor().getId())) {
			model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
			model.addAttribute("plan", plan);
			return "modifyPlan";
		} else {
			model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
			return "deniedChangePlanLogged";
		}
	}

	@RequestMapping(value = "/logged/modifiedPlan/{id}", method = RequestMethod.POST)
	public String modifiedPlan(Model model, Plan plan, @RequestParam("file") MultipartFile file) {
		model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
		User user = userRepository.findById(userComponent.getLoggedUser().getId());
		Plan planModify = planRepository.findOne(plan.getId());

		String FILES_FOLDER = "src\\main\\resources\\static\\planImages";
		Random rnd = new Random();
		int cod = rnd.nextInt(1000000);
		String fileName = cod + user.getId() + user.getPlans().size() + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

			} catch (Exception e) {
				model.addAttribute("idConectado", userComponent.getLoggedUser().getId());
				return "contact-logged";
			}
		}
		planModify.setId(plan.getId());
		planModify.setCategory(plan.getCategory());
		planModify.setTitle(plan.getTitle());
		planModify.setPlace(plan.getPlace());
		planModify.setAddress(plan.getAddress());
		planModify.setDate(plan.getDate());
		planModify.setDescription(plan.getDescription());
		planModify.setImagePlanTitle(fileName);
		planModify.setAuthor(userComponent.getLoggedUser());
		planRepository.save(planModify);
		model.addAttribute("id", planModify.getAuthor().getId());
		model.addAttribute("planes", planModify);
		model.addAttribute("idPlan", planModify.getId());
		model.addAttribute("title", planModify.getTitle());
		return "SuccessfulChangePlan";
	}

}
