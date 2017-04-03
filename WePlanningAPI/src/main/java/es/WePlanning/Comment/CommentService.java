package es.WePlanning.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment>findAll(){
		return commentRepository.findAll();
	}
	
	public Comment findOne(long id){
		return commentRepository.findOne(id);
	}
	
	public List<Comment> findByAuthorId(String id){
		return commentRepository.findByAuthorId(id);
	}
	
	public void save(Comment comment){
	 commentRepository.save(comment);
	}
	
	public Page<Comment> findComments(List<Comment> u,Pageable page){
		return commentRepository.findComments(u,page);
	}
	
	
	
	
}
