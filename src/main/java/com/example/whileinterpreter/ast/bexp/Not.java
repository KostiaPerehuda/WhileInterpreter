package com.example.whileinterpreter.ast.bexp;

public class Not implements BooleanExpression {

	private BooleanExpression exp;
	
	public Not(BooleanExpression exp) {
		this.exp = exp;
	}
		
	@Override
	public boolean eval() {
		return !exp.eval();
	}

}
