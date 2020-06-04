package com.cg.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.project.entity.Customer;
import com.cg.project.response.Response;
import com.cg.project.response.TokenResponse;

public class GenericExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Response<Customer>> handleException(CustomerNotFoundException ex) {

		Response<Customer> response = new Response<>(true, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}



	@ExceptionHandler
	public ResponseEntity<TokenResponse<Customer>> handleException(EmailNotFoundException exc) {
		TokenResponse<Customer> response = new TokenResponse<Customer>(true, exc.getMessage(), null, null);

		return new ResponseEntity<TokenResponse<Customer>>(response, HttpStatus.OK);
	}

}
