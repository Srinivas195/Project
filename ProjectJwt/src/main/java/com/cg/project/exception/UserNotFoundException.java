package com.cg.project.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User does not exist in the database");
	}
}
