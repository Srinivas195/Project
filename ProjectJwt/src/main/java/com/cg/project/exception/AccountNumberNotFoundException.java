package com.cg.project.exception;

@SuppressWarnings("serial")
public class AccountNumberNotFoundException extends RuntimeException {
	
	public AccountNumberNotFoundException(String message) {
		super(message);
	}
}
