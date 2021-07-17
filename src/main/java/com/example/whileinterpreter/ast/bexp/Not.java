package com.example.whileinterpreter.ast.bexp;

import com.example.whileinterpreter.state.State;

public class Not implements BooleanExpression {

	private BooleanExpression exp;
	
	public Not(BooleanExpression exp) {
		this.exp = exp;
	}
		
	@Override
	public boolean eval(State state) {
		return !exp.eval(state);
	}

}
