package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Times(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Times {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public long eval(State state) {
        return left.eval(state) * right.eval(state);
    }

}
