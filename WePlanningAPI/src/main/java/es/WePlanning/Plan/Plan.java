package es.WePlanning.Plan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Comment.Comment;
import es.WePlanning.User.User;

@Entity
public class Plan {
	
	public interface BasicAtt{}
	public interface UserAtt{}
	public interface CommentAtt{}

	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicAtt.class)
	private String title;
	
	@JsonView(BasicAtt.class)
	private String category;
	
	
	@JsonView(UserAtt.class)
	@ManyToOne
	private User author;
	
	@JsonView(BasicAtt.class)
	private String place;
	
	@JsonView(BasicAtt.class)
	private String address;
	
	@JsonView(BasicAtt.class)
	private long prize;
	
	@JsonView(BasicAtt.class)
	private String date;
	
	@JsonView(BasicAtt.class)
	private String description;
	
	@JsonView(BasicAtt.class)
	private String imagePlanTitle;
	
	@JsonView(Comment.class)
	@ManyToMany //(fetch = FetchType.EAGER)
	private List<Comment> comments;
	
	@JsonView(UserAtt.class)
	@ManyToMany //fetch = FetchType.EAGER)
	private List<User> asistents;
	
	
	public Plan() {

	}

	public Plan(String title, String category,String place, String address, long prize,
			String date, String description) {
		this.title = title;
		this.category = category;
		this.place = place;
		this.address = address;
		this.prize = prize;
		this.date = date;
		this.description = description;
		this.comments= new ArrayList<Comment>();
		this.asistents=new ArrayList<User>();
		
	}
	public String getImagePlanTitle() {
		return imagePlanTitle;
	}

	public void setImagePlanTitle(String imagePlanTitle) {
		this.imagePlanTitle = imagePlanTitle;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPrize() {
		return prize;
	}

	public void setPrize(long prize) {
		this.prize = prize;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getAsistents() {
		return asistents;
	}

	public void setAsistents(List<User> asistents) {
		this.asistents = asistents;
	}
	
}
