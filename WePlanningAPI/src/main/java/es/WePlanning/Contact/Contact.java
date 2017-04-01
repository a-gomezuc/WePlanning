
package es.WePlanning.Contact;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonView;

import es.WePlanning.User.User.BasicAtt;




@Entity
public class Contact {
	public interface BasicAtt{}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long identifier;
	
	@JsonView(BasicAtt.class)
	private String firstName;
	@JsonView(BasicAtt.class)
	private String lastName;
	@JsonView(BasicAtt.class)
	private String company;
	@JsonView(BasicAtt.class)
	private String phone;
	@JsonView(BasicAtt.class)
	private String emailAddress;
	@JsonView(BasicAtt.class)
	private String message;
	
	

	public Contact() {

	}

	public Contact (String id,String C_FirstName,String C_LastName, String C_Company,String C_BusPhone, String C_EmailAddress,String description ) {
		super();
		this.firstName = C_FirstName;
		this.lastName=C_LastName;
		this.company=C_Company;
		this.phone=C_BusPhone;
		this.emailAddress = C_EmailAddress;
		this.message=description;
	}

	
	@Override
	public String toString() {
		return "Contact [name=" + firstName + ", C_LastName=" + lastName + ", C_Company=" + company + ", C_BusPhone= " + phone + ",uC_EmailAddress=" + emailAddress + ", descripcion=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getC_EmailAddress() {
		return emailAddress;
	}

	public void setC_EmailAddress(String C_EmailAddress) {
		this.emailAddress = C_EmailAddress;
	}

	

	public String getC_LastName() {
		return lastName;
	}

	public void setC_LastName(String C_LastName) {
		this.lastName = C_LastName;
	}

	public String getC_FirstName() {
		return firstName;
	}

	public void setC_FirstName(String C_FirstName) {
		this.firstName = C_FirstName;
	}

	public String getC_Company() {
		return company;
	}

	public void setCompan(String C_Company) {
		this.company = C_Company;
	}

	public String getPhone() {
		return phone;
	}

	public void setphone(String c_BusPhone) {
		phone = c_BusPhone;
	}

	public Object getIdentifier() {
		return identifier;
	}
}
