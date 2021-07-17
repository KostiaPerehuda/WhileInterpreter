package com.example.whileinterpreter.ast.bexp;

import com.example.whileinterpreter.state.State;

public enum Bool implements BooleanExpression {
	
	TRUE(true), FALSE(false);
	
	private boolean value;
	
	private Bool(boolean value) {
		this.value = value;
	}

	@Override
	public boolean eval(State state) {
		return value;
	}	

}
