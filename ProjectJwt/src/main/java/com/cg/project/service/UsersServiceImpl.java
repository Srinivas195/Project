package com.cg.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.project.dao.CustomerRepo;
import com.cg.project.dao.UsersRepository;
import com.cg.project.entity.Customer;
import com.cg.project.exception.EmailNotFoundException;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersRepository usersRepository;

	@Autowired
	public UsersServiceImpl(UsersRepository theUsersRepository) {
		usersRepository = theUsersRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Customer> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Customer findById(int id) {
		Optional<Customer> result = usersRepository.findById(id);
		Customer theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find the user  : " + id);
		}
		return theEmployee;
	}

	@Override
	public void save(Customer theUsersInfo) {
		theUsersInfo.setPassword(passwordEncoder.encode(theUsersInfo.getPassword()));
		usersRepository.save(theUsersInfo);
	}

	@Override
	public void deleteById(int id) {
		usersRepository.deleteById(id);
	}

	@Override
	public Customer findByemail(String email) {

		Customer customer = usersRepository.findByemail(email);
		
		return customer;

	}
}

//	@Override
//	public CustomerDetails findByEmail(String emailId) {
//		
//		CustomerDetails customer = customerDetailsRepository.findbyEmail(emailId);
//		if(customer == null) {
//			
//			throw new EmailNotFoundException("Didnt find the email Id :"+ emailId);
//			
//		}
//		return customer;
//
//	}

//	@Override
//	public Customer findByemail(String email) {
//		Optional<Customer> result = usersRepository.findByemail(email);
//		Customer theEmployee = null;
//		if(result.isPresent()) {
//			theEmployee = result.get();
//		} else {
//			throw new RuntimeException("Did not find the user  : "+email);
//		}
//		return theEmployee;
////		return usersRepository.findByEmail(email) ;
//	}

//	@Override
//	public Page<Customer> getRequestForm(int pageNumber, int itemsPerPage) {
//		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
//		return usersRepository.findAll(pageable);
//	}
//	@Override
//	public Page<Customer> getSortRequestForm(int pageNumber, int itemsPerPage, String fieldName) {
//		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage,Sort.by(fieldName));
//		return usersRepository.findAll(pageable);
//	}
//
//	@Override
//	public UsersInfo findById(int id) {
//		return ;
//	}
