package com.cg.project.service;
import java.util.List;

import com.cg.project.entity.Customer;

public interface UsersService {
	
	public List<Customer> findAll();
	public Customer findById(int id);
//	public UsersInfo findById(int id);
	public void save(Customer theRequest);
	public void deleteById(int id);
	
	public Customer findByemail(String email);
	
//	public Customer findByemail(String emailId);
	
//	public Page<UsersInfo> getRequestForm(int pageNumber,int itemsPerPage);
//	public Page<UsersInfo> getSortRequestForm(int pageNumber,int itemsPerPage, String fieldName);
}