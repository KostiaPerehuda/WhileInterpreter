package com.github.kostiaperehuda.whileinterpreter.ast;

import java.util.Objects;

public record Equals(ArithmeticExpression left, ArithmeticExpression right) implements BooleanExpression {

    public Equals {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
