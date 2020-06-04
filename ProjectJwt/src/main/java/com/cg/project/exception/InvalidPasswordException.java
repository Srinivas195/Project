package com.cg.project.exception;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		super("Password Mismatch");
	}
	
	public InvalidPasswordException(String string) {
		super(string);
	}

}
