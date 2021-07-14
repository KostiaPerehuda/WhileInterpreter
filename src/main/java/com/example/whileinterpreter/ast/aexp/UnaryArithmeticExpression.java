package com.example.whileinterpreter.ast.aexp;

public abstract class UnaryArithmeticExpression implements ArithmeticExpression {

	private ArithmeticExpression exp;
	
	public UnaryArithmeticExpression(ArithmeticExpression exp) {
		this.exp = exp;
	}
	
	public ArithmeticExpression getExp() {
		return exp;
	}

}
