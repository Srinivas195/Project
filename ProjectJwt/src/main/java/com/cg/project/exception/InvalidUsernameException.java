package com.cg.project.exception;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {
	
	public InvalidUsernameException() {
		super("Name cannot contain numbers or special characters");
	}

}
