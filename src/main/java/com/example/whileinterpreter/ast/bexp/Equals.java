package com.example.whileinterpreter.ast.bexp;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.example.whileinterpreter.state.State;

public class Equals implements BooleanExpression {
	
	private ArithmeticExpression exp1;
	private ArithmeticExpression exp2;
	
	public Equals(ArithmeticExpression exp1, ArithmeticExpression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public boolean eval(State state) {
		return exp1.eval(state) == exp2.eval(state);
	}

}
