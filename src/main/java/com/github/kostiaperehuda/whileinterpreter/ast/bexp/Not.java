package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Not(BooleanExpression operand) implements BooleanExpression {

    public Not {
        Objects.requireNonNull(operand);
    }

    @Override
    public boolean eval(State state) {
        return !operand.eval(state);
    }

}
