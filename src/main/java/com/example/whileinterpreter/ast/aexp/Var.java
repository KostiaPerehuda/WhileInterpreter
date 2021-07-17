package com.example.whileinterpreter.ast.aexp;

import com.example.whileinterpreter.state.State;

public class Var implements ArithmeticExpression {
	
	private String name;
	
	public Var(String name) {
		this.name = name;
	}

	@Override
	public long eval(State state) {
		return state.get(name);
	}

}
