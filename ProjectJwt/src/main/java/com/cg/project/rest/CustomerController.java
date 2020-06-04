package com.cg.project.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.project.entity.Atm;
import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Credit;
//import com.cg.project.dto.BeneficiaryList;
import com.cg.project.entity.Customer;
import com.cg.project.entity.TransactionDetails;
import com.cg.project.entity.Transfer;
import com.cg.project.exception.CustomerNotFoundException;
import com.cg.project.exception.TransactionFailedException;
import com.cg.project.response.Response;
import com.cg.project.service.BeneficiaryService;
import com.cg.project.service.CustomerService;

import lombok.val;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {
	private CustomerService customerService;

	private BeneficiaryService beneficiaryService;

	@Autowired
	public CustomerController(CustomerService thecustomerService) {
		this.customerService = thecustomerService;
	}



	@GetMapping("/getTransactions")
	public Response<List<TransactionDetails>> findAll(TransactionDetails transactionDetails) {

		List<TransactionDetails> ben1 = customerService.findAllTransactions(transactionDetails);
		if (ben1 != null) {
			return new Response(false, "success", ben1);
		} else {
			return new Response(true, "fail", null);
		}
	}
	
	
	@GetMapping("/transactions/{pageNo}/{itemsPerPage}")
	public Page<TransactionDetails> getTransactions(@PathVariable int pageNo, @PathVariable int itemsPerPage) {

		return customerService.getTransactions(pageNo, itemsPerPage);
	}

	@GetMapping("/transactions/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<TransactionDetails> getSortTransactions(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {

		return customerService.getSortTransactions(pageNo, itemsPerPage, fieldName);
	}
	
	

	@GetMapping("/get-customers")
	public Response<List<Customer>> findAll(Customer customer) {

		List<Customer> ben1 = customerService.findAllCustomers(customer);
		if (ben1 != null) {
			return new Response(false, "success", ben1);
		} else {
			return new Response(true, "fail", null);
		}
	}

	@GetMapping("/customer/{id}")
	public Response<Customer> findCustomerById(@PathVariable int customerId) {

		Customer customer = customerService.findCustomerById(customerId);

		if (customer != null) {
			return new Response<Customer>(false, "Customer found", customer);
		} else {
			throw new CustomerNotFoundException("Customer id not found");
		}
	}

	@PostMapping("/add-customer")
	public Response<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
		
		Customer res = customerService.findByEmail(customer.getEmailId());
		Customer res1 = customerService.findByPhone(customer.getPhoneNo());
		Customer res2 = customerService.findByAadhar(customer.getAadharNo());
		Customer res3 = customerService.findByAccno(customer.getAccountNo());

		if( res !=null) {
			return new Response<Customer>(true,"This Email already Exist",null);
			
		}else if( res1 !=null) {
			return new Response<Customer>(true,"This phone already  Exist",null);

		}else if( res2 !=null){
			return new Response<Customer>(true,"This ad already Exist",null);

		}else if( res3 !=null){
			return new Response<Customer>(true,"This acc already Exist",null);

		}
		
		
		customer.setCustomerId(0);
		Customer customer2 = customerService.save(customer);

		if (customer2 != null) {
			return new Response<Customer>(false, "Customer added successfully", customer2);
		} else {
			throw new CustomerNotFoundException("not added");
		}
	}

	@PutMapping("/update-customer")
	public Response<Customer> updateCustomer(@Valid @RequestBody Customer customer) {

		Customer customer2 = customerService.update(customer);

		if (customer2 != null) {
			return new Response<Customer>(false, "Customer details updated successfully", customer2);
		} else {
			throw new CustomerNotFoundException("not updated");
		}
	}

	@PostMapping("/atm/{id}")
	public Response<Atm> atm(@PathVariable int id,@Valid @RequestBody Atm atm) {

		String atm2 = customerService.atmSimulator(id, atm);

		if (atm2 != null) {
			return new Response<Atm>(false, atm2, null);
		}
		return null;
	}

	@PostMapping("/transfer/{id}")
	public Response<Transfer> transferFunds(@PathVariable int id, @Valid @RequestBody Transfer transferFunds) {

		String transFunds = customerService.transferFunds(id, transferFunds);

		if (transFunds != null) {
			return new Response<Transfer>(false, transFunds, null);
		}
		return null;
	}

	@GetMapping("/myTransaction/{id}")
	public List<TransactionDetails> myTransaction(@PathVariable int id) {

		return customerService.myTransaction(id);
	}

	@GetMapping("/myBeneficiary/{id}")
	public List<Beneficiary> myBeneficiary(@PathVariable int id) {

		return customerService.myBeneficiary(id);
	}

	@GetMapping("/customers/{pageNo}/{itemsPerPage}")
	public Page<Customer> getCustomer(@PathVariable int pageNo, @PathVariable int itemsPerPage) {

		return customerService.getCustomers(pageNo, itemsPerPage);
	}

	@GetMapping("/customers/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<Customer> getSortCustomers(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {

		return customerService.getSortCustomers(pageNo, itemsPerPage, fieldName);
	}

	@PostMapping("/credit/{id}")
	public Response<Credit> transferFunds(@PathVariable int id, @Valid @RequestBody Credit credit) {

		String creditAmount = customerService.creditAmount(id, credit);

		if (creditAmount == null) {
			throw new TransactionFailedException("Transaction failed");
		} else {
			return new Response<>(false, creditAmount, null);
		}
	}

}
