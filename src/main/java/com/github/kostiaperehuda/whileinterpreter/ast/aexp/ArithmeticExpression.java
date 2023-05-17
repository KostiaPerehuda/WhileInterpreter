package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public interface ArithmeticExpression {
	public long eval(State state);
}
