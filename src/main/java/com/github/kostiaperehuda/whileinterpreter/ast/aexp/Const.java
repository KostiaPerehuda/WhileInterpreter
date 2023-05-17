package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

public record Const(long number) implements ArithmeticExpression {

    @Override
    public long eval(State state) {
        return number;
    }

}
