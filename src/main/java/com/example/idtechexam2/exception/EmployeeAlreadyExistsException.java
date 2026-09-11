package com.example.idtechexam2.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}
}
