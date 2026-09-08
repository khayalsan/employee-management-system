package com.example.idtechexam2;

import com.example.idtechexam2.exception.EmployeeAlreadyExistsException;
import com.example.idtechexam2.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		return new ErrorResponse("EMPLOYEE_ALREADY_EXISTS", ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return new ErrorResponse("EMPLOYEE_NOT_FOUND", ex.getMessage());
	}

}
