package es.WePlanning.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	Contact findByIdentifier(long id);

}