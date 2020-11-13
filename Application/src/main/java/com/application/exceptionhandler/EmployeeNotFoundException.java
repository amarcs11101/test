package com.application.exceptionhandler;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String exception) {
		super(exception);
	}
}
