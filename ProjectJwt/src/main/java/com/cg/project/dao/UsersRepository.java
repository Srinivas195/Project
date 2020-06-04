package com.cg.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.project.entity.Customer;

@Repository
public interface UsersRepository extends JpaRepository<Customer, Integer> {

//	@Query("from users where user_id=?1 and password=?2")
//	UsersInfo login(String email, String password);
//	
//	@Query("from users where user_id=?1")
//	boolean search(String email);

	@Query("from Customer where email_id = ?1")

	Customer findByemail(String email);

}
