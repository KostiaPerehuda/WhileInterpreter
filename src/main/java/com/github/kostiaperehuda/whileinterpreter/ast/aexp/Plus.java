package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.util.Objects;

public record Plus(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Plus {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
