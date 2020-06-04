package com.cg.Project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.project.entity.Beneficiary;
import com.cg.project.service.BeneficiaryService;
import com.cg.project.service.CustomerService;

@SpringBootTest
 class BeneficiaryTest {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@Autowired CustomerService customerService;
	Beneficiary beneficiary;
	
	Beneficiary bene =null;
	
	@BeforeEach
	void addBeneficiary() {
		
		beneficiary = new Beneficiary();
		
		beneficiary.setFirstName("Rama");
		beneficiary.setLastName("sita");
		beneficiary.setAccountNo("97865643453");
		beneficiary.setPhoneNo("9898976543");
		beneficiary.setEmail("abc@gmail.com");
		
		bene = beneficiaryService.save(beneficiary);
		
	}
	
	@Test
	void addTest() {
		assertNotNull(bene);
	}
		
	@Test
	void findAllTest() {
		List<Beneficiary> beneficiaries = beneficiaryService.findAllBeneficiaries(beneficiary);
		assertNotNull(beneficiaries);
	}
	
	@AfterEach() 
		void deleteTest() {
			beneficiary = beneficiaryService.findById(this.bene.getBeneficiaryId());
			beneficiaryService.deleteById(beneficiary.getBeneficiaryId());

			
		}
	
	
	
	@Test
	void testAfterDelete() {
		assertNotNull(beneficiary);
		
	}
	
	@Test
	void myBeneficiary() {
		int id =1;
		List<Beneficiary> benList = customerService.myBeneficiary(id);
		assertNotNull(benList);
	}
	
	
	
	
	
	
	
	}
	
		
		
	
