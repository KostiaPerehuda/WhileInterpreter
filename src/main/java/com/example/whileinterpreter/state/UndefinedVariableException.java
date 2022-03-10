package com.example.whileinterpreter.state;

import java.io.Serial;

public class UndefinedVariableException extends RuntimeException {

	@Serial
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
