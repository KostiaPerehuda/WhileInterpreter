package com.example.whileinterpreter.ast.bexp;

public enum Bool implements BooleanExpression {
	
	TRUE(true), FALSE(false);
	
	private boolean value;
	
	private Bool(boolean value) {
		this.value = value;
	}

	@Override
	public boolean eval() {
		return value;
	}	

}
