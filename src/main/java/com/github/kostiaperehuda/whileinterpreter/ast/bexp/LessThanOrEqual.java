package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;

import java.util.Objects;

public record LessThanOrEqual(ArithmeticExpression left, ArithmeticExpression right) implements BooleanExpression {

    public LessThanOrEqual {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
