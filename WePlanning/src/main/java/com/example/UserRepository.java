package com.example;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(String id);
	
	List<User> findByUnameIgnoreCase(String uname);
}
