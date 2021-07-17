package com.example.whileinterpreter.ast.aexp;

import com.example.whileinterpreter.state.State;

public class Const implements ArithmeticExpression {

	private long number;
	
	public Const(long number) {
		this.number = number;
	}
	
	@Override
	public long eval(State state) {
		return number;
	}

}
