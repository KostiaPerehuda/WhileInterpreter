package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

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
