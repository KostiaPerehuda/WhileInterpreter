package com.example.whileinterpreter.ast.aexp;

public class Times extends BinaryArithmeticExpression {

	public Times(ArithmeticExpression exp1, ArithmeticExpression exp2) {
		super(exp1, exp2);
	}

	@Override
	public long eval() {
		return getExp1().eval() * getExp2().eval();
	}

}
