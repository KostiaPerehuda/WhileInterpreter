package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

public abstract class BinaryArithmeticExpression implements ArithmeticExpression {

	private ArithmeticExpression exp1;
	private ArithmeticExpression exp2;
	
	protected BinaryArithmeticExpression(ArithmeticExpression exp1, ArithmeticExpression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public ArithmeticExpression getExp1() {
		return exp1;
	}
	
	public ArithmeticExpression getExp2() {
		return exp2;
	}

}
