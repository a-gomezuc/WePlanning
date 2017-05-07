package es.WePlanning.Comment;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.ApiService;
import es.WePlanning.Comment.Comment;
import es.WePlanning.Comment.CommentRepository;
import es.WePlanning.Contact.ContactRepository;
import es.WePlanning.Plan.Plan;
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
		private UserComponent userComponent;
		@Autowired
		private ContactRepository contactRepository;
		@Autowired
		private CommentService commentService;

		public interface CommentView extends Comment.BasicAtt, Comment.UserAtt, User.BasicAtt{}
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/api/comments", method= RequestMethod.GET)
		public List<Comment> comments(){
			return commentService.findAll();
		}
		@JsonView(CommentView.class)
		@RequestMapping(value="/api/comments/{id}", method= RequestMethod.GET)
		public ResponseEntity<Comment> comment(@PathVariable long id){
			Comment comment = commentService.findOne(id);
			if (comment != null) {
				return new ResponseEntity<>(comment, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		@JsonView(CommentView.class)
		@RequestMapping(value="/api/comments/author/{id}", method= RequestMethod.GET)
			public ResponseEntity<List<Comment>> commentByAuthor(@PathVariable String id){				
				List <Comment> comments = commentService.findByAuthorId(id);
				if (comments != null) {
					return new ResponseEntity<>(comments, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
		@JsonView(CommentView.class)
		@RequestMapping(value="/api/comments/{id}", method= RequestMethod.PUT)
			public ResponseEntity<Comment> commentModify(@PathVariable long id, @RequestBody Comment commentModified){				
			User user =userRepository.findById(userComponent.getLoggedUser().getId());	
			Comment comment = commentService.findOne(id);
			if(comment!=null){
			if(comment.getAuthor().getId().equals(user.getId())){
				commentModified.setId(id);
				commentModified.setAuthor(user);
				commentService.save(commentModified);
				return new ResponseEntity<>(comment, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
				
			}
														
		/*@JsonView(CommentView.class)
		@RequestMapping(value="api/comments/{id}", method= RequestMethod.DELETE)
		public ResponseEntity<Comment> commentDelete(@PathVariable long id){	
			User user =userRepository.findById(userComponent.getLoggedUser().getId());
			Comment comment = commentRepository.findOne(id);
			if((comment.getAuthor().getId().equals(user.getId()))&&(comment!=null)){
				commentRepository.delete(id);
				return new ResponseEntity<>(null,HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			 
		}*/
		
	
		
	
		

	}

