package com.example.whileinterpreter.ast.bexp;

import com.example.whileinterpreter.state.State;

public interface BooleanExpression {
	public boolean eval(State state);
}
