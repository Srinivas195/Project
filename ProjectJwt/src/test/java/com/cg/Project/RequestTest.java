package com.cg.Project;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.project.entity.Request;
import com.cg.project.service.CustomerService;
import com.cg.project.service.RequestService;

@SpringBootTest
public class RequestTest {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private CustomerService CustomerService;
	
	@Test
	void getRequestPositivetest() {
		List<Request> request = requestService.findAll();
		assertNotNull(request);
	}
	
	@Test
	void getRequestNegativeTest() {
		List<Request> request = requestService.findAll();
		assertNotEquals(null,request);
	}

	@Test
	void getMyRequestsPositiveTest() {
		int id = 1;
		List<Request> request = CustomerService.myRequest(id);
		assertNotNull(request);
	}

	@Test
	void getMyRequestsNegativeTest() {
		int id = 1;
		List<Request> request = CustomerService.myRequest(id);
		assertNotEquals(null, request);
	}
}