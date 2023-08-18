package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.util.Objects;

public record Minus(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Minus {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
