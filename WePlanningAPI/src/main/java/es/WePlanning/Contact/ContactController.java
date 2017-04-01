package es.WePlanning.Contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;


import es.WePlanning.Contact.Contact;

public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	

	public interface ContactView extends Contact.BasicAtt{
	}

	@JsonView(ContactView.class)
	@RequestMapping(value = "/api/admin/contacts", method = RequestMethod.GET)
	public List<Contact> contacts() {
		return contactRepository.findAll();
	}
	
	@JsonView(ContactView.class)
	@RequestMapping(value = "/api/addContact", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		contact.setIdentifier(0);
		contactRepository.save(contact);
		return new ResponseEntity<>(contact, HttpStatus.OK);

	}
	
	@JsonView(ContactView.class)
	@RequestMapping(value = "/api/admin/removeContact/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable long id) {					
		Contact contact = contactRepository.findByIdentifier(id);
		if(contact!=null){
			contactRepository.delete(contact);
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
