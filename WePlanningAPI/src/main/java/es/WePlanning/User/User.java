package es.WePlanning.User;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.Plan.Plan;

@Entity
public class User {
	
	public interface BasicAtt{}
	public interface PlansAtt{}
	public interface FriendsAtt{}
	public interface RolesAtt{}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long identifier;
	
	@JsonView(BasicAtt.class)
	@Column(unique=true)
	private String id;
	
	@JsonView(BasicAtt.class)
	private boolean sponsor;
	
	@JsonView(BasicAtt.class)
	private String uname;
	
	@JsonView(BasicAtt.class)
	private String surname;
	
	@JsonView(BasicAtt.class)
	private String province;
	
	@JsonView(BasicAtt.class)
	private int age;
	
	@JsonView(BasicAtt.class)
	private String uemail;
	
	@JsonIgnore
	private String passwordHash;
	
	@JsonView(BasicAtt.class)
	private String description;
	
	@JsonView(BasicAtt.class)
	private String profilePhotoTitle;
	
	@JsonView(PlansAtt.class)
	@OneToMany(mappedBy = "author")
	private List<Plan> plans;
	
	@JsonView(FriendsAtt.class)
	@ManyToMany (fetch = FetchType.LAZY)
	private List<User> friends;
	
	@JsonView(RolesAtt.class)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {

	}

	public User(boolean sponsor,String id, String uname, String surname, String province, int age, String uemail, String upass, String... roles) {
		super();
		this.sponsor=sponsor;
		this.id = id;
		this.uname = uname;
		this.surname = surname;
		this.province = province;
		this.age = age;
		this.uemail = uemail;
		this.passwordHash = new BCryptPasswordEncoder().encode(upass);
		this.setDescription("Soy un usuario de WePlanning, esta es mi descripción.");
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.friends = new ArrayList<>();
		this.plans = new ArrayList<>();
		this.profilePhotoTitle="profiledefault.jpg";
	}
	
	public String getProfilePhotoTitle() {
		return profilePhotoTitle;
	}

	public void setProfilePhotoTitle(String profilePhotoTitle) {
		this.profilePhotoTitle = profilePhotoTitle;
	}
	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public boolean isSponsor() {
		return sponsor;
	}

	public void setSponsor(boolean sponsor) {
		this.sponsor = sponsor;
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
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", surname=" + surname + ", province=" + province + ", age="
				+ age + ", uemail=" + uemail + ", upass=" + passwordHash + ", friends=" + friends + "]";
	}

}
