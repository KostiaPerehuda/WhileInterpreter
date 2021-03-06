package com.example.whileinterpreter.ast.aexp;

import com.example.whileinterpreter.state.State;

public class Times extends BinaryArithmeticExpression {

	public Times(ArithmeticExpression exp1, ArithmeticExpression exp2) {
		super(exp1, exp2);
	}

	@Override
	public long eval(State state) {
		return getExp1().eval(state) * getExp2().eval(state);
	}

}
