package com.cg.project.exception;

@SuppressWarnings("serial")
public class EmailNotFoundException extends RuntimeException{
	
	public EmailNotFoundException(String message) {
		super(message);
	}


}
