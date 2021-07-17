package com.example.whileinterpreter.ast.aexp;

import com.example.whileinterpreter.state.State;

public interface ArithmeticExpression {
	public long eval(State state);
}
