package com.example.whileinterpreter.state;

public class UndefinedVariableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UndefinedVariableException() {
		super();
	}
	
	public UndefinedVariableException(String message) {
		super(message);
	}
	
	public UndefinedVariableException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UndefinedVariableException(Throwable cause) {
		super(cause);
	}
	
}
