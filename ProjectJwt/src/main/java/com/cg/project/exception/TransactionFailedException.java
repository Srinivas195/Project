package com.cg.project.exception;

public class TransactionFailedException extends RuntimeException {
	public TransactionFailedException(String message) {
		super(message);
	}
}
