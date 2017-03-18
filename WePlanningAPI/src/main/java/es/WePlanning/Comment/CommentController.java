package es.WePlanning.Comment;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.PlanRepository;
import es.WePlanning.User.User;
import es.WePlanning.User.UserComponent;
import es.WePlanning.User.UserRepository;
@RestController
public class CommentController {

		private static final Logger log = LoggerFactory.getLogger(CommentController.class);
		
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

		public interface CommentView extends Comment.BasicAtt, Comment.UserAtt, User.BasicAtt{}
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/comments", method= RequestMethod.GET)
		public List<Comment> comments(Model model){
			return commentRepository.findAll();
		}
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/comments/author/{id}", method= RequestMethod.GET)
		public List<Comment> commentsAuthor(Model model, @PathVariable String id){
			return commentRepository.findByAuthor(id);
		}
		@JsonView(CommentView.class)
		@RequestMapping(value="/commentDelete/{id}", method= RequestMethod.DELETE)
		public void  commentDelete(Model model, @PathVariable long id){
			 commentRepository.delete(id);
		}
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/commentPut/{id}", method= RequestMethod.PUT)
		public Comment commentPut(Model model, @PathVariable long id, @RequestBody Comment commentModified){
			commentModified.setId(id);
			Comment comment = commentRepository.saveAndFlush(commentModified);
			return commentRepository.findById(comment.getId());
		}
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/commentPost", method= RequestMethod.POST)
		public Comment commentPost(Model model, @RequestBody Comment newComment){
			newComment.setId(0);
			Comment comment = commentRepository.saveAndFlush(newComment);
			return commentRepository.findById(comment.getId());
		}
		

	}

