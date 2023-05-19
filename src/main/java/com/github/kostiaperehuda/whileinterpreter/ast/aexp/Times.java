package com.github.kostiaperehuda.whileinterpreter.ast.aexp;

import java.util.Objects;

public record Times(ArithmeticExpression left, ArithmeticExpression right) implements ArithmeticExpression {

    public Times {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
