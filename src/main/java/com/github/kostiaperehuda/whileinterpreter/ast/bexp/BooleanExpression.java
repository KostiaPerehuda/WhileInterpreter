package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public interface BooleanExpression {
    public boolean eval(State state);
}
