
package es.WePlanning.Contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long identifier;
	
	private String C_FirstName;
	private String C_LastName;
	private String C_Company;
	private String C_BusPhone;
	private String C_EmailAddress;
	private String description;
	

	public Contact() {

	}

	public Contact (String id,String C_FirstName,String C_LastName, String C_Company,String C_BusPhone, String C_EmailAddress,String description ) {
		super();
		this.C_FirstName = C_FirstName;
		this.C_LastName=C_LastName;
		this.C_Company=C_Company;
		this.C_BusPhone=C_BusPhone;
		this.C_EmailAddress = C_EmailAddress;
		this.description=description;
	}

	
	@Override
	public String toString() {
		return "Contact [name=" + C_FirstName + ", C_LastName=" + C_LastName + ", C_Company=" + C_Company + ", C_BusPhone= " + C_BusPhone + ",uC_EmailAddress=" + C_EmailAddress + ", descripcion=" + description + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getC_EmailAddress() {
		return C_EmailAddress;
	}

	public void setC_EmailAddress(String C_EmailAddress) {
		this.C_EmailAddress = C_EmailAddress;
	}

	

	public String getC_LastName() {
		return C_LastName;
	}

	public void setC_LastName(String C_LastName) {
		this.C_LastName = C_LastName;
	}

	public String getC_FirstName() {
		return C_FirstName;
	}

	public void setC_FirstName(String C_FirstName) {
		this.C_FirstName = C_FirstName;
	}

	public String getC_Company() {
		return C_Company;
	}

	public void setCompan(String C_Company) {
		this.C_Company = C_Company;
	}

	public String getC_BusPhone() {
		return C_BusPhone;
	}

	public void setC_BusPhone(String c_BusPhone) {
		C_BusPhone = c_BusPhone;
	}
}
