package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String title;
	private String category;
	private User author;
	private String place;
	private String address;
	private long prize;
	private Date date;
	private String description;
	
	@ManyToOne
	private List<User> asistents= new ArrayList<>();
	
	public Plan() {

	}

	public Plan(String title, String category, User author, String place, String address, long prize,
			Date date, String description, ArrayList<User> asistents) {
		this.title = title;
		this.category = category;
		this.author = author;
		this.place = place;
		this.address = address;
		this.prize = prize;
		this.date = date;
		this.description = description;
		this.asistents=asistents;	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
