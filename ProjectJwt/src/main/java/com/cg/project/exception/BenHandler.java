package com.cg.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.project.entity.Beneficiary;
import com.cg.project.response.Response;

public class BenHandler {
	

	@ExceptionHandler
	public ResponseEntity<Response<Beneficiary>> handleException(BeneficiaryNotFoundException ex) {

		Response<Beneficiary> response = new Response<>(true, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}


}
