package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;
import com.github.kostiaperehuda.whileinterpreter.state.State;

import java.util.Objects;

public record LessThanOrEqual(ArithmeticExpression left, ArithmeticExpression right) implements BooleanExpression {

    public LessThanOrEqual {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    @Override
    public boolean eval(State state) {
        return left.eval(state) <= right.eval(state);
    }

}
