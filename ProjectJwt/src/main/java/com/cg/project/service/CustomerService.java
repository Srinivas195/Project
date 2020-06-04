package com.cg.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cg.project.entity.Atm;
import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Credit;
import com.cg.project.entity.Customer;
import com.cg.project.entity.Request;
import com.cg.project.entity.TransactionDetails;
import com.cg.project.entity.Transfer;

public interface CustomerService {

	public List<TransactionDetails> findAllTransactions(TransactionDetails transactionDetails);
	
	public List<Customer> findAllCustomers(Customer customer);

	public Customer findCustomerById(int id);

	public Customer save(Customer customer);
	
	public Customer update (Customer customer);

	public Customer findByEmail(String email);

	public Customer findByAadhar(String aadhar);

	public Customer findByPhone(String string);

	public Customer findByAccno(String accNo);
	
	public List<Beneficiary> myBeneficiary(int id);
	
	public List<Request> myRequest(int id);
	
	public String atmSimulator(int id, Atm atm);
	
	public String transferFunds(int id, Transfer transferFunds);
	
	public List<TransactionDetails> myTransaction(int id);

	public Page<Customer> getCustomers(int pageNo, int itemsPerPage);

	public Page<Customer> getSortCustomers(int pageNo, int itemsPerPage, String fieldName);

	public String creditAmount(int id, Credit credit);
	
	public Page<TransactionDetails> getTransactions(int pageNo, int itemsPerPage);
	
	public Page<TransactionDetails> getSortTransactions(int pageNo, int itemsPerPage, String fieldName);
	
}
