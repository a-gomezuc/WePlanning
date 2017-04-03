package es.WePlanning.User;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User>findAll(){
		return userRepository.findAll();
	}
	
	
	public User findByIdIgnoreCase(String id){
		return userRepository.findByIdIgnoreCase(id);
	}
	public List<User>searchUser (String usearch, String filter){
		if ((!usearch.equals("")) && (filter.equals("ident"))) {
			ArrayList<User> resultados = new ArrayList<>();
			User userId = userRepository.findByIdIgnoreCase(usearch);
			if (userId != null)
				resultados.add(userId);
			return resultados;

		} else if ((usearch.equals("")) && (filter.equals("name"))) {
			return userRepository.findByUnameIgnoreCase(usearch);

		} else if ((usearch.equals("")) && (filter.equals("province"))) {
			return userRepository.findByProvinceIgnoreCase(usearch);
		} else
			return userRepository.findAll();
	}
	public List<User>findByUnameIgnoreCase(String uname){
		return userRepository.findByUnameIgnoreCase(uname);
	}
	
	public List<User>findByProvinceIgnoreCase(String province){
		return userRepository.findByProvinceIgnoreCase(province);
	}
	
	public Page<User> findUsers(List<User> list, int page){
		return userRepository.findUsers(list, new PageRequest(page, 10));
	}
	
	public void save (User user){
		userRepository.save(user);
	}
	
	public void delete (User user){
		userRepository.delete(user);
	}
}
