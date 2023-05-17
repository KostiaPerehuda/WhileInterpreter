package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

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
