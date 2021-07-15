package com.example.whileinterpreter.ast.bexp;

import com.example.whileinterpreter.ast.aexp.ArithmeticExpression;

public class Equals implements BooleanExpression {
	
	private ArithmeticExpression exp1;
	private ArithmeticExpression exp2;
	
	public Equals(ArithmeticExpression exp1, ArithmeticExpression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public boolean eval() {
		return exp1.eval() == exp2.eval();
	}

}
