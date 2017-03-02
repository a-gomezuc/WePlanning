package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class User {
	@Id
	private String id;
	
	private String uname;
	private String surname;
	private String province;
	private int age;
	private String uemail;
	private String upass;
	
	@OneToMany(mappedBy="author")
	private List<Plan> plans;
	
	@ManyToMany
	private List<User> friends;


	
	
	public User(){
		
	}


	public User(String id, String uname, String surname, String province, int age, String uemail, String upass) {
		super();
		this.id=id;
		this.uname = uname;
		this.surname = surname;
		this.province = province;
		this.age = age;
		this.uemail = uemail;
		this.upass = upass;
		this.friends=new ArrayList<>();
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


	public String getUpass() {
		return upass;
	}


	public void setUpass(String upass) {
		this.upass = upass;
	}


	public List<User> getFriends() {
		return friends;
	}


	public void setFriendsWhoAddedMe(List<User> friendsWhoAddedMe) {
		this.friends = friendsWhoAddedMe;
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
				+ age + ", uemail=" + uemail + ", upass=" + upass + ", friends=" + friends + "]";
	}

}
