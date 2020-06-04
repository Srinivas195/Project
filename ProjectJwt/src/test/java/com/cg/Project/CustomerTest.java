package com.cg.Project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.project.entity.Customer;
import com.cg.project.entity.TransactionDetails;
import com.cg.project.service.CustomerService;

@SpringBootTest

public class CustomerTest {

	@Autowired
	private CustomerService customerServiceService;

	Customer customer;

	Customer cus = null;

	@BeforeEach
	void addBeneficiary() {

		customer = new Customer();

		customer.setFirstName("Rama");
		customer.setLastName("sita");
		customer.setAccountNo("97865643453");
		customer.setPhoneNo("9898976543");
		customer.setEmailId("ramu@gmail.com");
		customer.setAadharNo("98767656545");
		customer.setAddress("Cbpur");
		customer.setGender("Male");
		customer.setDob("29-02-1950");

		customer.setOpeningBalance("20000");

		customer.setPin("2526");
		customer.setUsername("ram_1234");
		customer.setRole("ROLE_USER");

		customer.setPassword("Qwerty@123");
		customer.setBranch("Bengaluru");
		customer.setBeneficiaryList(null);
		customer.setTranscationDetailsList(null);
		customer.setRequestList(null);

//		bene = beneficiaryService.save(beneficiary);

	}

//	@Test
//	void addTest() {
//		assertNotNull(cus);
//	}

//	@Test
//	void findAllTest() {
//		List<Customer> beneficiaries = customerServiceService.findAllCustomers(customer);
//		assertNotNull(beneficiaries);
//	}

	@Test
	void TestSearch() {
		Customer customer1 = customerServiceService.findByEmail(customer.getEmailId());
		assertNotNull(customer1);
	}

	@Test
	void getall() {
		List<Customer>customersList = customerServiceService.findAllCustomers(customer);
		assertNotNull(customersList);
	}

	@Test
	void getMyTransaction() {
		int id = 1;
		List<TransactionDetails> tranList = customerServiceService.myTransaction(id);
		assertNotNull(tranList);
	}
	
}
