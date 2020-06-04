package com.cg.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.project.dao.CustomerRepo;
import com.cg.project.dao.Postrepo;
import com.cg.project.dao.RequestRepo;
import com.cg.project.entity.Customer;
import com.cg.project.entity.Postnews;
import com.cg.project.entity.Request;

@Service
public class RequestServiceImpl implements RequestService {

	private RequestRepo requestRepository;
	
	private Postrepo postrepo;
	private CustomerRepo customerRepository;

	@Autowired
	public RequestServiceImpl (RequestRepo RequestRepository , CustomerRepo therequest,Postrepo thepost) {
		this.requestRepository =RequestRepository;
		this.customerRepository =therequest;
		this.postrepo =thepost;

		
	}

	@Override
	public List<Request> findAll() {
		return requestRepository.findAll();
	}
	
	@Override
	public List<Postnews> findAll1(Postnews postnews) {
		return postrepo.findAll();
	}

	
	@Override
	public Request findById(int requestId) {
     Optional<Request> result = requestRepository.findById(requestId);
		
		Request request=null;
		if(result.isPresent()) {
			request=result.get();
		}
		return request;
	}
	
	@Override
	public Request save(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public Postnews savepost(Postnews request) {
		return postrepo.save(request);
	}
	
	
	
	@Override
	public Request setApproved(int id) {
		Optional<Request> result = requestRepository.findById(id);
		Request applicant = null;
		applicant = result.get();
		applicant.setStatus("Updated");
		requestRepository.save(applicant);
		return applicant;
	}

	@Override
	public Request setRejected(int id) {
		Optional<Request> result = requestRepository.findById(id);
		Request applicant = null;
		applicant = result.get();
		applicant.setStatus("Rejected");
		requestRepository.save(applicant);
		return applicant;
	}


	@Override
	public String addReq(int id, Request request) {

		String message = "";
		Customer customer = customerRepository.findById(id).get();

		if (customer == null) {
			message = "Customer not found";
			return message;
		} else {
			request.setCustomer(customer);

			requestRepository.save(request);
			message = "Request sent Successfully";
		}
		return message;
	}



	@Override
	public Page<Request> getRequests(int pageNo, int itemsPerPage) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return requestRepository.findAll(pageable);

	}

	@Override
	public Page<Request> getSortRequests(int pageNo, int itemsPerPage, String fieldName) {

		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return requestRepository.findAll(pageable);
	}
	
	
}
