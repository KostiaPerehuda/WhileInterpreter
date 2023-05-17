package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public interface ArithmeticExpression {
    long eval(State state);
}
