package es.WePlanning.Comment;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.User.User;

@Entity
public class Comment {
	
	public interface BasicAttr {}
	
	@JsonView(BasicAttr.class)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicAttr.class)
	@OneToOne
	private User author;
	
	@JsonView(BasicAttr.class)
	private String date;
	
	@JsonView(BasicAttr.class)
	private String content;
	
	public Comment(){
	}
	
	public Comment(String date, String content) {
		super();
		this.date = date;
		this.content = content;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", date=" + date + ", content=" + content + "]";
	}
	


}
