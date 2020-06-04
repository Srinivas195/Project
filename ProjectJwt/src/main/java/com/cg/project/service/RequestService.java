package com.cg.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.cg.project.entity.Postnews;
import com.cg.project.entity.Request;

public interface RequestService {

	 List<Request> findAll();
    
    public Page<Request> getRequests(int pageNumber, int itemsPerPage);
    
    public Page<Request> getSortRequests(int pageNumber, int itemsPerPage, String fieldName);
    
    public Request findById(int requestId);
    
    public Request save(Request request);

    public String addReq(int id, Request request);
	

	Request setApproved(int id);

	Request setRejected(int id);
	
	public List<Postnews> findAll1(Postnews postnews);
	
	public Postnews savepost(Postnews request);
	
	
	
}

