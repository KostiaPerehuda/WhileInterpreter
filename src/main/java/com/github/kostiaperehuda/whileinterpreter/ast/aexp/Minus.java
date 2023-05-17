package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record Minus(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Minus {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public long eval(State state) {
        return left.eval(state) - right.eval(state);
    }

}
