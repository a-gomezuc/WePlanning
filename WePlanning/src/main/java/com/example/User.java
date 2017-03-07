package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long identifier;
	
	@Column(unique=true)
	private String id;

	private String uname;
	private String surname;
	private String province;
	private int age;
	private String uemail;
	private String passwordHash;

	@OneToMany(mappedBy = "author")
	private List<Plan> plans;

	@ManyToMany
	private List<User> friends;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {

	}

	public User(String id, String uname, String surname, String province, int age, String uemail, String upass, String... roles) {
		super();
		this.id = id;
		this.uname = uname;
		this.surname = surname;
		this.province = province;
		this.age = age;
		this.uemail = uemail;
		this.passwordHash = new BCryptPasswordEncoder().encode(upass);
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.friends = new ArrayList<>();
		this.plans = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<User> getFriends() {
		return friends;
	}
	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", surname=" + surname + ", province=" + province + ", age="
				+ age + ", uemail=" + uemail + ", upass=" + passwordHash + ", friends=" + friends + "]";
	}

}
