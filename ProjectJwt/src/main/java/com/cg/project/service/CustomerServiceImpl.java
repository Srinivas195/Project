package com.cg.project.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.cg.project.dao.TransactiondetailsRepo;
import com.cg.project.entity.Atm;
import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Credit;
import com.cg.project.entity.Customer;
import com.cg.project.entity.Request;
import com.cg.project.entity.TransactionDetails;
import com.cg.project.entity.Transfer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepo customerRepository;

	private BeneficiaryRepo beneficiaryRepository;
	
	private TransactiondetailsRepo transRepo;

//	private TransactiondetailsRepostory transactiondetailsRepostory;

	@Autowired
	public CustomerServiceImpl(CustomerRepo theCustomerRepository,TransactiondetailsRepo thetransRepo ) {
		this.transRepo=thetransRepo;
		this.customerRepository = theCustomerRepository;
//		this.transactiondetailsRepostory = theTransactiondetailsRepostory;
	}

	@Override
	public List<Customer> findAllCustomers(Customer customer) {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(int id) {
		Optional<Customer> result = customerRepository.findById(id);

		Customer customer = null;
		if (result.isPresent()) {
			customer = result.get();
		}

		return customer;
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Page<Customer> getCustomers(int pageNo, int itemsPerPage) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return customerRepository.findAll(pageable);

	}

	@Override
	public Page<Customer> getSortCustomers(int pageNo, int itemsPerPage, String fieldName) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return customerRepository.findAll(pageable);
	}

	
	@Override
	public Page<TransactionDetails> getTransactions(int pageNo, int itemsPerPage) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return transRepo.findAll(pageable);

	}

	@Override
	public Page<TransactionDetails> getSortTransactions(int pageNo, int itemsPerPage, String fieldName) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return transRepo.findAll(pageable);
	}
	
	@Override
	public Customer findByEmail(String email) {

		return customerRepository.findbyEmail(email);
	}

	@Override
	public Customer findByAadhar(String aadhar) {

		return customerRepository.findbyAadhar(aadhar);
	}

	@Override
	public Customer findByPhone(String mobNo) {

		return customerRepository.findbyMobile(mobNo);
	}

	@Override
	public Customer findByAccno(String accNo) {

		return customerRepository.findbyAccNo(accNo);
	}

	@Override
	public String atmSimulator(int id, Atm atm) {

		String message = "";

		Customer customer = customerRepository.findById(id).get();

		if (customer != null) {

			double balance = Double.parseDouble(customer.getOpeningBalance());
//			double balance1 = customer.getOpeningBalance();
			double withdraw = Double.parseDouble(atm.getAmount());
//			double add = atm.getAmount();

			if (balance > withdraw) {

				if (atm.getPin().equals(customer.getPin())) {

					List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();
					balance = balance - withdraw;
//					balance1 = balance1 + add;

					customer.setOpeningBalance(balance + "");

					TransactionDetails transferDetails = new TransactionDetails();
					transferDetails.setDebit(withdraw + "");
					transferDetails.setCredit(0.00 + "");
					transferDetails.setBalance(balance + "");
					transferDetails.setDate(new Date());
					transferDetails.setRemarks("Withdrawn By ATM");
					transferDetails.setId("Transaction Done by Customer Id: " + customer.getCustomerId());
					transferDetails.setCustomer(customer);

					transaction.add(transferDetails);

					customer.setTranscationDetailsList(transaction);

					customerRepository.save(customer);

					message = "Transcation Successfull";
				} else {
					message = "Wrong pin";
				}
			} else {
				message = "Don't have sufficient balance";
			}
		} else {
			message = "Id not found";
		}

		return message;
	}


	
	
	@Override
	public String transferFunds(int id, Transfer transferFunds) {
		String message = "";

		Customer customer = customerRepository.findById(id).get();

		if (customer != null) {

			boolean result = false;

			for (Beneficiary beneficiary : customer.getBeneficiaryList()) {

				if (transferFunds.getAccNo().equals(beneficiary.getAccountNo())) {
					result = true;
				} else {
					message = "Invalid Account number";
				}
			}

			if (result) {
				double balance = Double.parseDouble(customer.getOpeningBalance());
				double transAmount = Double.parseDouble(transferFunds.getAmount());

				if (balance > transAmount) {

					if (transferFunds.getPin().equals(customer.getPin())) {

						List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();
						balance = balance - transAmount;

						customer.setOpeningBalance(balance + "");

						TransactionDetails transferDetails = new TransactionDetails();
						transferDetails.setDebit(transAmount + "");
						transferDetails.setCredit(0.00 + "");
						transferDetails.setBalance(balance + "");
						transferDetails.setDate(new Date());
						transferDetails.setId("Transaction Done by Customer Id: " + customer.getCustomerId());
						transferDetails
								.setRemarks("Transfer to Beneficiary, ACC.NO:" + transferFunds.getAccNo());
						transferDetails.setCustomer(customer);

						transaction.add(transferDetails);

						customer.setTranscationDetailsList(transaction);

						customerRepository.save(customer);
						
						message = "Transaction Successful";

					} else {
						message = "Incorrect Pin";
					}

				} else {
					message = "Dont have sufficient balance";
				}
			}
		}

		return message;

	}

	@Override
	public List<TransactionDetails> myTransaction(int id) {

		Customer customer = customerRepository.findById(id).get();

//		return transactiondetailsRepostory.findAll().stream()
//				.filter(customer1 -> customer1.getCustomer().getCustId() == id).collect(Collectors.toList());
		if (customer != null) {
			return customer.getTranscationDetailsList();
		}
		return null;
	}

	@Override
	public List<Beneficiary> myBeneficiary(int id) {

		Customer customer = customerRepository.findById(id).get();

		if (customer != null) {
			return customer.getBeneficiaryList();
		}

		return null;
	}
	
	
	
	@Override
	public List<Request> myRequest(int id) {

		Customer customer = customerRepository.findById(id).get();

		if (customer != null) {
			return customer.getRequestList();
		}

		return null;
	}

	 @Override
	public String creditAmount(int id, Credit credit) {

		String message = "";

		Customer customer = customerRepository.findById(id).get();

		if (customer != null) {
			double amount1 = Double.parseDouble(customer.getOpeningBalance());

			double camt = Double.parseDouble(credit.getAmount());

			double add = amount1 + camt;

			List<TransactionDetails> transaction = new ArrayList<TransactionDetails>();

			
			
			TransactionDetails transferDetails = new TransactionDetails();
			transferDetails.setDebit(0.00 + "");
			transferDetails.setCredit(credit.getAmount() + "");
			transferDetails.setBalance(add + "");
			transferDetails.setDate(new Date());
			transferDetails.setId("Transaction Done by Customer Id: " + customer.getCustomerId());
			transferDetails.setRemarks("Transfer success, ACC.NO:");
			transferDetails.setCustomer(customer);

			transaction.add(transferDetails);

			customer.setTranscationDetailsList(transaction);
			
			customer.setOpeningBalance(add + "");

			customerRepository.save(customer);

			message = "Money Credited Successfully";
		}

		return message;
	}

	@Override
	public List<TransactionDetails> findAllTransactions(TransactionDetails transactionDetails) {
		return transRepo.findAll();
	}

	@Override
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
}
