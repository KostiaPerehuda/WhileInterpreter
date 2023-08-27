package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record LessThanOrEqual(ArithmeticExpression left, ArithmeticExpression right) implements BooleanExpression {

    public LessThanOrEqual {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
