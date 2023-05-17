package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record And(BooleanExpression left, BooleanExpression right) implements BooleanExpression {

    public And {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public boolean eval(State state) {
        return left.eval(state) && right.eval(state);
    }
}
