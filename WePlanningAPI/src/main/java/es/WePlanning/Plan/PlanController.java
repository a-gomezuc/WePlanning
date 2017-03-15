package es.WePlanning.Plan;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;

@RestController
public class PlanController {
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	
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

	public interface PlanView extends Plan.BasicAtt, Plan.CommentAtt, Plan.UserAtt, User.BasicAtt, Comment.BasicAttr{}
	
	@JsonView(PlanView.class)
	@RequestMapping(value="/plans", method= RequestMethod.GET)
	public List<Plan> plans(Model model){
		return planRepository.findAll();
	}

}
