package com.example;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	@Query("SELECT c FROM Comment c WHERE c IN :u")
	Page<Comment> findComments(@Param("u")List<Comment> u,Pageable page);
}

