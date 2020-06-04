package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.project.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("from Customer where email_id=?1")
	Customer findbyEmail(String email);
	
	@Query("from Customer where phone_no=?1")
	Customer findbyMobile(String mobNo);
	
	@Query("from Customer where aadhar_no=?1")
	Customer findbyAadhar(String aadhar);
	
	@Query("from Customer where account_no=?1")
	Customer findbyAccNo(String accNo);
	
	@Query("from Customer where email_id = ?1")
	Customer findByemail(String email);
	
}
