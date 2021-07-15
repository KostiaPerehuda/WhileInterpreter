package com.example.whileinterpreter.ast.bexp;

public class And implements BooleanExpression {

	private BooleanExpression exp1;
	private BooleanExpression exp2;
	
	public And(BooleanExpression exp1, BooleanExpression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public boolean eval() {
		return exp1.eval() && exp2.eval();
	}

}
