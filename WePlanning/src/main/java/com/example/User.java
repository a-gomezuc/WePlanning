package com.example;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {
	@Id
	private String id;
	private String uname;
	private String surname;
	private String province;
	private int age;
	private Email uemail;
	private String upass;
	
	@ManyToMany
	List<User> friends;
	
	
	public User(){
		
	}


	public User(String id, String uname, String surname, String province, int age, Email uemail, String upass,
			List<User> friends) {
		super();
		this.id = id;
		this.uname = uname;
		this.surname = surname;
		this.province = province;
		this.age = age;
		this.uemail = uemail;
		this.upass = upass;
		this.friends = friends;
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


	public Email getUemail() {
		return uemail;
	}


	public void setUemail(Email uemail) {
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


	public void setFriends(List<User> friends) {
		this.friends = friends;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", surname=" + surname + ", province=" + province + ", age="
				+ age + ", uemail=" + uemail + ", upass=" + upass + ", friends=" + friends + "]";
	}

}
