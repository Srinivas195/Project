package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Customer;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Integer> {

	@Query("from Beneficiary where email=?1")
	Beneficiary findbyEmail(String email);
	
	@Query("from Beneficiary where phone_no=?1")
	Beneficiary findbyMobile(String mobNo);
	

	
	@Query("from Beneficiary where account_no=?1")
	Beneficiary findbyAccNo(String accNo);
	
	
}
