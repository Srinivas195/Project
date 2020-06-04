package com.cg.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Customer;

@Service

public interface BeneficiaryService {

public List<Beneficiary> findAllBeneficiaries(Beneficiary beneficiary);
	
	public Beneficiary save(Beneficiary beneficiary);
	
	public Beneficiary findById(int id);
	
	public void deleteById(int id);
	
	public Page<Beneficiary> getBeneficiaries(int pageNo, int itemsPerPage);
	
	public Page<Beneficiary> getBeneficiaries(int pageNo, int itemsPerPage, String fieldName);

	public String addBeneficiary(int id, Beneficiary beneficiary);

	public List<Beneficiary> myBeneficiary(int id);

	public Beneficiary findByPhone(String string);

	public Beneficiary findByAccno(String accNo);
	
	public Beneficiary findByEmail(String email);

	
	
	
	
}
