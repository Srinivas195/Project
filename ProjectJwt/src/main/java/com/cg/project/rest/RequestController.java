package com.cg.project.rest;

import java.util.Date;
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

import com.cg.project.dao.Postrepo;
import com.cg.project.entity.Postnews;
import com.cg.project.entity.Request;
import com.cg.project.exception.AccountNumberNotFoundException;
import com.cg.project.exception.RequestNotFound;
import com.cg.project.response.Response;
import com.cg.project.service.CustomerService;
import com.cg.project.service.RequestService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {
	
     private RequestService requestService;
	
     private CustomerService customerService;
     
     private Postrepo postrepo;
     
	@Autowired
	public RequestController(RequestService RequestService , CustomerService theCustomerService, Postrepo therepo ) {
		this.requestService=RequestService;
		this.customerService=theCustomerService;
		this.postrepo=therepo;
	}
	
	@GetMapping("/requests")
	public List<Request> findAll(){
		return requestService.findAll();
	}
	

	
	@GetMapping("/requests/{requestId}")

	public Response<Request> getRequest(@PathVariable int requestId) {
		Request request = requestService.findById(requestId);

		if (request != null) {
			return new Response<>(false,"Request details found", request);
		}else {
			return new Response<>(true,"Request details not found", request);
		}

	}
	
	@PostMapping("/request")
	public Response<Request> addCustomer(@RequestBody Request request) {
		
		request.setRequestId(0);
		Request requests=requestService.save(request);
		if(requests != null) {
			
			return new Response<>(false,"Request Sent Successfully", requests);
		}else {
			throw new RequestNotFound("Request failed");
		}
		
	}
	
	
	@PostMapping("/postnews")
	public Response<Postnews> addpost(@Valid @RequestBody Postnews request) {
		
		request.setDetails("Post new on" +  " " + new Date() + "" + "," + request.getDetails());
		
		request.setPostId(0);
		Postnews requests=requestService.savepost(request);
		if(requests != null) {
			
			return new Response<>(false,"Post Sent Successfully", requests);
		}else {
			throw new RequestNotFound("Post failed");
		}
		
	}
	
	
	@GetMapping("/getpost")
	public Response<List<Postnews>> findAll1(Postnews postnews){
		List<Postnews> ben1 = requestService.findAll1(postnews);
		if (ben1 != null) {
			return new Response(false, "success", ben1);
		} else {
			return new Response(true, "fail", null);
		}
	}
	

	
	
	
	@PostMapping("/addRequest/{id}")
	public Response<Request> addrequest(@PathVariable int id, @Valid @RequestBody Request request) {
		
		request.setRequestId(0);

		String requests = requestService.addReq(id, request);

		if (requests != null) {
			return new Response<>(false, requests, request);
		}

		return null;
	}
	
	@GetMapping("/request/{id}")
	public Response<Request> findRequestById(@PathVariable int id) {

		Request beneficiary = requestService.findById(id);

		if (beneficiary != null) {
			return new Response<Request>(false, "Beneficiary found", beneficiary);
		} else {
			throw new AccountNumberNotFoundException("Beneficiary not found");
		}
	}
	
	
	
	
	@PutMapping("/requests")
	public Request updateRequest(@RequestBody Request request) {
		requestService.save(request);
		return request;
	}
	
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}")
	public Page<Request> getAllRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage){
		return requestService.getRequests(pageNumber, itemsPerPage);
		
	}
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}/{fieldName}")
	public Page<Request> getSortRequests(@PathVariable int pageNumber, @PathVariable int itemsPerPage, @PathVariable String fieldName){
		return requestService.getSortRequests(pageNumber, itemsPerPage, fieldName);
		
	}
	
	@PutMapping("/applicationapprove/{appId}")
	public Response<Request> findAllApproved(@PathVariable Integer appId) {
		Request result = requestService.setApproved(appId);
		if(result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "successfully updated", result);
		}
//		return applicantService.setApproved(appId);
	}
	@PutMapping("/applicationreject/{appId}")
	public Response<Request> findAllRejected(@PathVariable Integer appId) {
		Request result =  requestService.setRejected(appId);
		if(result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "successfully updated", result);
		}
	}
	
	
	@GetMapping("/myrequest/{id}")
	public List<Request> myTransaction(@PathVariable int id) {

		return customerService.myRequest(id);
	}
	
//	@PutMapping("/customer/{customerId}")
//	public Response<Customer> updateProduct(@RequestBody Customer theCustomer) {
//
//		Customer customer2 = service.save(theCustomer);
//		if (customer2 != null) {
//			return new Response<>(false, "Customer updated successfully", customer2);
//		} else {
//			return new Response<>(true, "Customer failed", null);
//		}
//	}

	
	

}

