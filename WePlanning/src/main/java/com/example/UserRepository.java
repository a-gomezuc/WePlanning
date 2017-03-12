package com.example;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(String id);
	
	List<User> findByUnameIgnoreCase(String uname);
	User findByIdIgnoreCase(String id);
	List<User> findByProvinceIgnoreCase(String province);
	@Query("SELECT u FROM User u WHERE u IN :l")
	Page<User> findUsers(@Param("l") List<User> l, Pageable page);
}
