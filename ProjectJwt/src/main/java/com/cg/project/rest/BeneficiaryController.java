package com.cg.project.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.project.entity.Beneficiary;
import com.cg.project.entity.Customer;
import com.cg.project.exception.AccountNumberNotFoundException;
import com.cg.project.exception.MessageException;
import com.cg.project.response.Response;
import com.cg.project.service.BeneficiaryService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class BeneficiaryController {

	private BeneficiaryService beneficiaryService;

	@Autowired
	public BeneficiaryController(BeneficiaryService theBeneficiaryService) {
		this.beneficiaryService = theBeneficiaryService;
	}

	

	@GetMapping("/get-beneficiaries")
	public Response<List<Beneficiary>> findAll(Beneficiary beneficiary) {
		
		List<Beneficiary> ben1 = beneficiaryService.findAllBeneficiaries(beneficiary);
		if (ben1 != null) {
			return new Response(false, "success",ben1 );
		}
			else {
				return new Response(true,"fail",null);
			}
	}

	@GetMapping("/beneficiary/{id}")
	public Response<Beneficiary> findBeneficiaryById(@PathVariable int id) {

		Beneficiary beneficiary = beneficiaryService.findById(id);

		if (beneficiary != null) {
			return new Response<Beneficiary>(false, "Beneficiary found", beneficiary);
		} else {
			throw new AccountNumberNotFoundException("Beneficiary not found");
		}
	}

	

	
	
	@PutMapping("/benupdate")
	public Response<Beneficiary> updateRequestForm(@RequestBody Beneficiary theRequestForm) {
		beneficiaryService.save(theRequestForm);
		if (theRequestForm != null) {

			return new Response<>(false, "successfully saved", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	
	

	@DeleteMapping("/delete-beneficiary/{id}")
	public Response<Beneficiary> deleteBeneficiary(@PathVariable int id) {

		Beneficiary beneficiary = beneficiaryService.findById(id);

		if (beneficiary != null) {
			beneficiaryService.deleteById(id);
			return new Response<Beneficiary>(false, "Beneficiary deleted successfully", beneficiary);
		} else {
			throw new MessageException("Not deleted");
		}
	}

	

	@PostMapping("/addBeneficiary/{id}")
	public Response<Beneficiary> addBeneficiary(@PathVariable int id, @Valid @RequestBody Beneficiary beneficiary) {
		
		
		Beneficiary res = beneficiaryService.findByEmail(beneficiary.getEmail());
		Beneficiary res1 = beneficiaryService.findByPhone(beneficiary.getPhoneNo());
		Beneficiary res3 = beneficiaryService.findByAccno(beneficiary.getAccountNo());

		if( res !=null) {
			return new Response<Beneficiary>(true,"This Email already Exist",null);
			
		}else if( res1 !=null) {
			return new Response<Beneficiary>(true,"This Phone Number already Exist",null);

		}else if( res3 !=null){
			return new Response<Beneficiary>(true,"This Account already Exist",null);

		}
		
		
		
		
		
		beneficiary.setBeneficiaryId(0);

		String addBeneficiary = beneficiaryService.addBeneficiary(id, beneficiary);

		if (addBeneficiary != null) {
			return new Response<>(false, addBeneficiary, beneficiary);
		}

		return null;
	}
	
	
	
	@GetMapping("/beneficiaries/{pageNo}/{itemsPerPage}")
	public Page<Beneficiary> getBeneficiary(@PathVariable int pageNo, @PathVariable int itemsPerPage) {

		return beneficiaryService.getBeneficiaries(pageNo, itemsPerPage);
	}

	@GetMapping("/beneficiaries/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<Beneficiary> getBeneficiary(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {

		return beneficiaryService.getBeneficiaries(pageNo, itemsPerPage, fieldName);
	}

}
