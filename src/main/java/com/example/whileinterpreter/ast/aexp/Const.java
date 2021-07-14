package com.example.whileinterpreter.ast.aexp;

public class Const implements ArithmeticExpression {

	private long number;
	
	public Const(long number) {
		this.number = number;
	}
	
	@Override
	public long eval() {
		return number;
	}

}
