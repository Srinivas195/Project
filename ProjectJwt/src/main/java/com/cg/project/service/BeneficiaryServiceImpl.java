package com.cg.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.project.dao.BeneficiaryRepo;
import com.cg.project.dao.CustomerRepo;
import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Customer;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	private BeneficiaryRepo beneficiaryRepository;

	private CustomerRepo customerRepository;

	@Autowired
	public BeneficiaryServiceImpl(BeneficiaryRepo theBeneficiaryRepository, CustomerRepo theCustomerRepository) {
		this.beneficiaryRepository = theBeneficiaryRepository;
		this.customerRepository = theCustomerRepository;
	}

	
	
	@Override
	public List<Beneficiary> findAllBeneficiaries(Beneficiary beneficiary) {

//		Customer customer = new Customer();
//
//		customer.setBeneficiaryList(beneficiaryRepository.findAll());
//		customerRepository.save(customer);

		return beneficiaryRepository.findAll();
	}

	@Override
	public Beneficiary save(Beneficiary beneficiary) {

		return beneficiaryRepository.save(beneficiary);
	}

	@Override
	public Beneficiary findById(int id) {

		Optional<Beneficiary> result = beneficiaryRepository.findById(id);

		Beneficiary beneficiary = null;
		if (result.isPresent()) {
			beneficiary = result.get();
		}

		return beneficiary;
	}

	@Override
	public void deleteById(int id) {
		beneficiaryRepository.deleteById(id);
	}

	@Override
	public Page<Beneficiary> getBeneficiaries(int pageNo, int itemsPerPage) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return beneficiaryRepository.findAll(pageable);
	}

	@Override
	public Beneficiary findByEmail(String email) {

		return beneficiaryRepository.findbyEmail(email);
	}

	

	@Override
	public Beneficiary findByPhone(String mobNo) {

		return beneficiaryRepository.findbyMobile(mobNo);
	}

	@Override
	public Beneficiary findByAccno(String accNo) {

		return beneficiaryRepository.findbyAccNo(accNo);
	}
	
	
	@Override
	public Page<Beneficiary> getBeneficiaries(int pageNo, int itemsPerPage, String fieldName) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return beneficiaryRepository.findAll(pageable);
	}
	
	@Override
	public String addBeneficiary(int id, Beneficiary beneficiary) {

		String message = "";
		Customer customer = customerRepository.findById(id).get();

		if (customer == null) {
			message = "Customer not found";
			return message;
		} else {
			beneficiary.setCustomer(customer);

			beneficiaryRepository.save(beneficiary);
			message = "Beneficiary added Successfully";
		}
		return message;
	}



	@Override
	public List<Beneficiary> myBeneficiary(int id) {
		return beneficiaryRepository.findAll();
	}



}
