package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.util.Objects;

public record Multiply(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Multiply {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
