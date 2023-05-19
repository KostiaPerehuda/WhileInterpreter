package com.github.kostiaperehuda.whileinterpreter.ast.bexp;

import com.github.kostiaperehuda.whileinterpreter.ast.aexp.ArithmeticExpression;

import java.util.Objects;

public record Equals(ArithmeticExpression left, ArithmeticExpression right) implements BooleanExpression {

    public Equals {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

}
